package Component;

public class Diode extends Branch {
    String name;
    private double current;
    private double voltage;
    Node node1;
    Node node2;
    private double time;
    private int flag;
    //constructor
    protected Diode(String name,Node node1,Node node2,int flag) {
        super(name,node1,node2);
        this.flag=flag;
    }
    public void setVoltage(){
        this.voltage=node1.getVoltage()-node2.getVoltage();
    }
    public double getVoltage(){
        return (node1.getVoltage()-node2.getVoltage()) ;
    }
    public void setCurrent() {
        CircuitAnalysis.setNodeVoltage(node1.getnumber(),time);
        CircuitAnalysis.setNodeVoltage(node2.getnumber(),time);
        setVoltage();
        if(voltage>0)
            System.out.println("Impossible");
        if(voltage==0)
            current=node1.currentleaving;
        else
            current=0;
    }
    public double getCurrent() {
        return current;}
}