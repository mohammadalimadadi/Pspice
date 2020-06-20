package Component;

import Node.Node;

public class H extends Branch {
    private Branch branch;
    private double coefficient;
    protected H(String name, Node node1, Node node2, Branch branch , double coefficient) {
        super(name,node1,node2);
        this.branch=branch;
        this.coefficient=coefficient;
    }
    @Override
    public void setVoltage(){
        this.voltage= coefficient*(branch.getCurrent());
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
    @Override
    public void setCurrent() {
        this.current =  node1.currentleaving;
    }

    @Override
    public double getCurrent() {
        return current;}
}
