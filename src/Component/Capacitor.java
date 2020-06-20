package Component;
import Node.Node;
public class Capacitor extends Branch {
    protected static int capcount=1;
    private String name;
    private double capacity;
    private double elastance;
    private double voltagederive;

    //constructor
    protected Capacitor(String name,Node node1,Node node2,double capacity) {
        super(name,node1,node2);
        this.capacity = capacity;
        this.elastance=1/capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getElastance() {
        return elastance;
    }

    public void setVoltagederive() {
        voltagederive =node1.getVoltagederive()-node2.getVoltagederive();
    }

    public double getVoltagederive() {
        return voltagederive;
    }

    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }
    @Override
    public double getVoltage(){
        return voltage ;
    }
    @Override
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltagederive(node1.getnumber(),time);
        CircuitAnalysis.setNodeVoltagederive(node2.getnumber(),time);
        current= voltagederive*capacity;
    }

    @Override
    public double getCurrent() {
        return current;
    }
}
