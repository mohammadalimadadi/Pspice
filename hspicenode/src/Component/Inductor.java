package Component;

import Node.Node;

public class Inductor extends Branch {
    protected static int indcount=1;
    private double inductance;
    private double inductanceinv;
    private double voltageintegral;
    //constructor
    protected Inductor(String name, Node node1, Node node2, double inductance) {
        super(name,node1,node2);
        this.inductance = inductance;
        this.inductanceinv=1/inductance;
    }

    public double getInductance() {
        return inductance;
    }

    public double getInductanceinv() {
        return inductanceinv;
    }

    public void setVoltageintegral() {
        voltageintegral =node1.getVoltageintegral()-node2.getVoltageintegral();
    }

    public double getVoltageintegral() {
        return voltageintegral;
    }
    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }
    @Override
    public double getVoltage(){
        return voltage ;
    }

    @Override
    public void setCurrent(double dt, double dv, double di) {
        current+=(dt*((node1.getVoltage()-node2.getVoltage_p())-(node1.getVoltage_p()-node2.getVoltage_p())))/inductance;
    }

    @Override
    public double getCurrent() {
        return current;
    }

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        current_p+=(dt*((node1.getVoltage()-node2.getVoltage_p())-(node1.getVoltage_p()-node2.getVoltage_p())+dv))/inductance;
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
}