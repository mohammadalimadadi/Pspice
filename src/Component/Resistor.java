package Component;

public class Resistor extends Branch {
    //maybe next line is effective in next project phases.
    protected static int rescount=0;
    //value of resistance
    private double resistance;
    // value of parameter(G) because it is better for Matrix solution.
    private double conductance;
    //constructor
    protected Resistor(String name,Node node1,Node node2,double resistance) {
        super(name,node1,node2);
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
    @Override
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltage(node1.getnumber(),time);
        CircuitAnalysis.setNodeVoltage(node2.getnumber(),time);
        current= voltage*conductance;
    }
    @Override
    public double getCurrent() {
        return current;
    }
    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltage ;
    }

}