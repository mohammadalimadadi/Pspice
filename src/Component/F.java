package Component;

import Node.Node;

public class F extends Branch {
    private Branch branch;
    private double coefficient;
    public F(String name, Node node1, Node node2, Branch branch , double coefficient) {
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
    public void setCurrent(double dt, double dv, double di) {
        this.current = coefficient*(branch.getCurrent());
    }

    @Override
    public double getCurrent() {
        return current;}

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        this.current_p = coefficient*(branch.getCurrent());
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