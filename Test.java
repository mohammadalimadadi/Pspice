import java.awt.*;
import java.util.ArrayList;
public class BasisNode extends Node{
    protected ArrayList<Component> attachments;
    protected BasisNode(int number, ArrayList<Component> attachments){
        super(number,attachments);
      //  number=0;
    }
    protected BasisNode(){
        super();
    }
    protected int getnumber(){
        return 0;
    }
    public void setVoltage(){
        this.voltage=0;
    }
    public double getVoltage(){
        return voltage;
    }
}
abstract public class Node{
    double voltage;
    double voltagederive;
    double voltageintegral;
    double currentleaving;
     int number;
    protected ArrayList<Component> attachments;
    double time;
    protected Node(int number, ArrayList<Component> attachments){
        this.number=number;
        this.attachments=attachments;
    }

    protected Node() {

    }

    protected abstract int getnumber();
    public abstract void setVoltagederive();
    public abstract double getVoltagederive();
    public abstract void setCurrentleaving();
    public abstract double getCurrentleaving() ;
    public abstract void setVoltageintegral() ;
    public abstract double getVoltageintegral();
    public abstract void setVoltage();
    public  abstract double getVoltage();
}
public class SimpleNode extends Node{
    protected SimpleNode( int number, ArrayList<Component > attachments){
            super(number, attachments);
        }

    public SimpleNode() {
        super();
    }
}




public class Resistor extends Component {
    //maybe next line is effective in next project phases.
    protected static int rescount=0;
    //value of resistance
    private double resistance;
    // value of parameter(G) because it is better for Matrix solution.
    private double conductance;
//constructor
    protected Resistor(String name,double resistance,Node node1,Node node2,int time) {
        super(name,node1,node2,time);
        this.resistance = resistance;
        this.conductance=1/resistance;
    }
    //set and get for some parameters
    public double getResistance() {
        return resistance;
    }

    public double getConductance() {
        return conductance;
    }
    //This function is called by methods in another classes and set current for this component.
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltage(node1.number,time);
        CircuitAnalysis.setNodeVoltage(node2.number,time);
        current= voltage*conductance;
    }
    public double getCurrent() {
        return current;
    }
    public void setVoltage(){
        voltage=node1.voltage-node2.voltage;
    }
    public double getvoltage(){
        return (node1.voltage-node2.voltage) ;
    }
}
public class Capacitor {
    protected static int capcount=1;
   private String name;
    private double capacity;
    private double elastance;
    private double current;
    private double voltage;
    private double voltagederive;
   private Node node1;
   private  Node node2;
    private double time;
    //constructor
    protected Capacitor(String name,double capacity,Node node1,Node node2,double time) {
        this.name = name;
        this.capacity = capacity;
        this.elastance=1/capacity;
        this.node1 = node1;
        this.node2 = node2;
        this.time=time;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getElastance() {
        return elastance;
    }

    public void setVoltagederive() {
        voltagederive =node1.voltagederive-node2.voltagederive;
    }

    public double getVoltagederive() {
        return voltagederive;
    }

    public void setVoltage(){
        voltage=node1.voltage-node2.voltage;
    }
    public double getVoltage(){
        return voltage ;
    }
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltagederive(node1.number,time);
        CircuitAnalysis.setNodeVoltagederive(node2.number,time);
        current= voltagederive*capacity;
    }

    public double getCurrent() {
        return current;
    }
}


public class Inductor {
    protected static int indcount=1;
    String name;
    private double inductance;
    private double inductanceinv;
    private double current;
    private double voltage;
    private double voltageintegral;
   private Node node1;
    Node node2;
    private double time;
    //constructor
    protected Inductor(String name,double inductance,Node node1,Node node2,double time) {
        this.name = name;
        this.inductance = inductance;
        this.inductanceinv=1/inductance;
        this.node1 = node1;
        this.node2 = node2;
        this.time=time;
    }

    public double getInductance() {
        return inductance;
    }

    public double getInductanceinv() {
        return inductanceinv;
    }

    public void setVoltageintegral() {
        voltageintegral =node1.voltageintegral-node2.voltageintegral;
    }

    public double getVoltageintegral() {
        return voltageintegral;
    }

    public void setVoltage(){
        voltage=node1.voltage-node2.voltage;
    }
    public double getVoltage(){
        return voltage ;
    }
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltageintegral(node1.number,time);
        CircuitAnalysis.setNodeVoltagederive(node2.number,time);
        current= voltageintegral*inductanceinv;
    }

    public double getCurrent() {
        return current;
    }
}
public class Diode{
    String name;
    private double current;
    private double voltage;
    Node node1;
    Node node2;
    private double time;
    //constructor
    protected Diode(String name,Node node1,Node node2,double time) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.time=time;
    }
    public void setVoltage(){
        this.voltage=node1.voltage-node2.voltage;
    }
    public double getVoltage(){
        return (node1.voltage-node2.voltage) ;
    }
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltage(node1.number,time);
        CircuitAnalysis.setNodeVoltage(node2.number,time);
       setVoltage();
       if(voltage>0)
           System.out.println("Impossible");
       if(voltage==0)
           current=node1.currentleaving;
       else
           current=0;
    }
    public double getCurrent() {
        return current;}
}


public class VoltageSource{
    String name;
    private double voltage;
    private double current;
    Node node1;
    Node node2;
    private double time;
    protected VoltageSource(String name,Node node1,Node node2,double voltage,double time) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.voltage=voltage;
    this.time=time;}
    public double getVoltage() {
        return voltage;}
    public void setCurrent() {
        current=node1.currentleaving;
    }

    public double getCurrent() {
        return current;}
}
public class VSINSource{
    String name;
    private double voltage;
    private double current;
    private double va;
    private double freq;
    private double phase;
    Node node1;
    Node node2;
    private double time;
    protected VSINSource(String name,Node node1,Node node2,double va,double freq,double phase,double time) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.va=va;
        this.freq=freq;
        this.phase=phase;
        this.time=time;}
    public double getVoltage(double time) {
        return va*Math.sin(2*Math.PI*freq*time+phase);}
    public void setCurrent() {
        current=node1.currentleaving;
    }
    public double getCurrent() {
        return current;}
}



public class CurrentSource{
    String name;
    private double voltage;
    private double current;
    Node node1;
    Node node2;
    private double time;
    protected CurrentSource(String name,Node node1,Node node2,double current,double time) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.current=current;
    }
    public void setVoltage(){
        CircuitAnalysis.setNodeVoltage(node1.number,time);
        CircuitAnalysis.setNodeVoltage(node2.number,time);
        this.voltage=node1.voltage-node2.voltage;
    }
    public double getCurrent() {
        return current;
    }
}


public class CurrentSINSource{
    String name;
    private double voltage;
    private double current;
    Node node1;
    Node node2;
    private double ia;
    private double freq;
    private double phase;
    private double time;
    protected CurrentSINSource(String name,Node node1,Node node2,double current,double ia,double freq,double phase,double time) {
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.current=current;
        this.ia=ia;
        this.freq=freq;
        this.phase=phase;
        this.time=time;
    }
    public void setVoltage(){
        CircuitAnalysis.setNodeVoltage(node1.number,time);
        CircuitAnalysis.setNodeVoltage(node2.number,time);
        this.voltage=node1.voltage-node2.voltage;
    }
    public double getCurrent(double time) {
        return ia*Math.sin(2*Math.PI*freq*time+phase);
    }
}


public abstract class Component{
  protected  String name;
    Node node1;
    Node node2;
   protected double time;
    protected  double voltage;
    protected  double current;

    Component(String name,Node node1,Node node2,double time){
        this.name=name;
        this.node1=node1;
        this.node2=node2;
        this.time=time;
    }
    public abstract void setVoltage();
    public  abstract double getVoltage();
    public abstract void setCurrent();
    public abstract double getCurrent();
}
https://lpsa.swarthmore.edu/BackGround/ImpulseFunc/ImpFunc.html
    public class Test {
    public static void main(String[] args){

    }
}
