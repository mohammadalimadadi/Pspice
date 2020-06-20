package Component;

import Node.Node;

public class F extends Branch {
   private Branch branch;
    private double coefficient;
    protected F(String name, Node node1,Node node2,Branch branch , double coefficient) {
        super(name,node1,node2);
        this.branch=branch;
        this.coefficient=coefficient;
    }


    @Override
    public void setVoltage(){
        this.voltage= node1.getVoltage()-node2.getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
    @Override
    public void setCurrent() {
        this.current = coefficient*(branch.getCurrent());
    }

    @Override
    public double getCurrent() {
        return current;}
}
