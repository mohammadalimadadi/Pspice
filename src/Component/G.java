package Component;

public class G extends Branch {
    private Node node3;
    private Node node4;
    private double coefficient;
    protected G(String name, Node node1, Node node2, Node node3,Node node4, double coefficient) {
        super(name,node1,node2);
        this.node3=node3;
        this.node4=node4;
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
        this.current =  coefficient*(node3.getVoltage()-node4.getVoltage());
    }

    @Override
    public double getCurrent() {
        return current;}
}