package Component;
import Node.Node;
public abstract class Branch {
    protected  String name;
    Node node1;
    Node node2;
    protected double time;
    protected  double voltage;
    protected  double current;
    Branch (String name,Node node1,Node node2){
        this.name=name;
        this.node1=node1;
        this.node2=node2;
    }
    public abstract void setVoltage();
    public  abstract double getVoltage();
    public abstract void setCurrent();
    public abstract double getCurrent();

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
