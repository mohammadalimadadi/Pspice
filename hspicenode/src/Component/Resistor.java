package Component;

import Node.Node;

public class Resistor extends Branch {
    //maybe next line is effective in next project phases.
    protected static int rescount=0;
    //value of resistance
    private double resistance;
    // value of parameter(G) because it is better for Matrix solution.
    private double conductance;
    //constructor
    protected Resistor(String name, Node node1, Node node2, double resistance) {
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
    public double getCurrent() {
        return current;
    }

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        current_p+=((node1.getVoltage()-node2.getVoltage_p())-(node1.getVoltage_p()-node2.getVoltage_p())+dv)/resistance;
    }

    @Override
    public double getCurrent_p() {
        return current_p;
    }

    @Override
    public void setCurrent_n() {

    }

    @Override
    public double getCurrent_n() {
        return 0;
    }

    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltage ;
    }

    @Override
    public void setCurrent(double dt, double dv, double di) {
        current+=((node1.getVoltage()-node2.getVoltage_p())-(node1.getVoltage_p()-node2.getVoltage_p()))/resistance;
    }

}