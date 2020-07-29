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
    public void setCurrent(double dt, double dv, double di) {
        for(Branch branch: node1.getAttachments()){
            if(node1.equals(branch.getNode1()))
                current-=branch.getCurrent();
            else
                current+=branch.getCurrent();
        }
    }

    @Override
    public double getCurrent() {
        return current;}

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        for(Branch branch: node1.getAttachments()){
            if(node1.equals(branch.getNode1()))
                current_p-=branch.getCurrent_p();
            else
                current_p+=branch.getCurrent_p();
        }
    }

    @Override
    public double getCurrent_p() {
        return current_p;
    }


}