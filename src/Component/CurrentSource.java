package Component;

import Node.Node;

public class CurrentSource extends Branch {
    private double current;
    private double io;
    private double ia;
    private double freq;
    private double phase;
    public CurrentSource(String name, Node node1, Node node2, double io, double ia, double freq, double phase) {
        super(name,node1,node2);
        this.io=io;
        this.ia=ia;
        this.freq=freq;
        this.phase=phase;
    }

    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltage;
    }

    @Override
    public void setCurrent(double dt, double dv, double di) {
        current =  ia*Math.sin(2*Math.PI*freq*time+phase)+io;
    }


    @Override
    public double getCurrent() {
        return current;}

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        current_p =  ia*Math.sin(2*Math.PI*freq*time+phase)+io;
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