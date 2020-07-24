package Component;

import Node.Node;

public abstract class Branch {
    protected  String name;
    protected Node node1;
   protected  Node node2;
    protected double time;
    protected   double voltage;
    protected    double current;
    protected    double current_p;
    protected   double current_n;
    Branch (String name,Node node1,Node node2){
        this.name=name;
        this.node1=node1;
        this.node2=node2;
    }
    public abstract void setVoltage();
    public  abstract double getVoltage();

    public abstract void setCurrent(double dt,double dv,double di);
    public abstract double getCurrent();
    public abstract void setCurrent_p(double dt,double dv,double di);
    public abstract double getCurrent_p();
    public abstract void setCurrent_n();
    public abstract double getCurrent_n();

    public String getName() {
        return name;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

}