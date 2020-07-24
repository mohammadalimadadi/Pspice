package Component;

import Node.Node;

public class E extends Branch{
    private Node node3;
    private Node node4;
    private double coefficient;
    public E(String name, Node node1, Node node2, Node node3, Node node4, double coefficient) {
        super(name,node1,node2);
        this.node3=node3;
        this.node4=node4;
        this.coefficient=coefficient;
    }

    @Override
    public void setVoltage(){
        this.voltage= coefficient*(node3.getVoltage()-node4.getVoltage());
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