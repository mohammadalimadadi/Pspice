package Component;

import Node.Node;

public class VoltageSource extends Branch {
    private double voltage;
    private double vo;
    private double va;
    private double freq;
    private double phase;
    public VoltageSource(String name, Node node1, Node node2, double vo, double va, double freq, double phase) {
        super(name,node1,node2);
        this.vo=vo;
        this.va=va;
        this.freq=freq;
        this.phase=phase;
    }

    @Override
    public void setVoltage(){
        this.voltage= va*Math.sin(2*Math.PI*freq*time+phase)+vo;
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
    @Override
    public void setCurrent(double dt,double dv,double di) {
        current = node1.getCurrent();
    }
    public void setCurrent() {
        current = node1.getCurrent();
    }
    @Override
    public double getCurrent() {
        return current;}

    public  void setCurrent_p(double dt,double dv,double di){}
    public  double getCurrent_p(){
        return 0;
    }



    public  void setCurrent_n(){}
    public  double getCurrent_n(){
        return 0;
    }
}