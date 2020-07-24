package Component;

import Node.Node;

public class H extends Branch {
    private Branch branch;
    private double coefficient;
    public H(String name, Node node1, Node node2, Branch branch , double coefficient) {
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

    @Override
    public void setCurrent_n() {

    }

    @Override
    public double getCurrent_n() {
        return 0;
    }
}