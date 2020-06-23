package Component;

public class Inductor extends Branch {
    protected static int indcount=1;
    private double inductance;
    private double inductanceinv;
    private double voltageintegral;
    //constructor
    protected Inductor(String name,Node node1,Node node2,double inductance) {
        super(name,node1,node2);
        this.inductance = inductance;
        this.inductanceinv=1/inductance;
    }

    public double getInductance() {
        return inductance;
    }

    public double getInductanceinv() {
        return inductanceinv;
    }

    public void setVoltageintegral() {
        voltageintegral =node1.getVoltageintegral()-node2.getVoltageintegral();
    }

    public double getVoltageintegral() {
        return voltageintegral;
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
        CircuitAnalysis.setNodeVoltageintegral(node1.getnumber(),time);
        CircuitAnalysis.setNodeVoltagederive(node2.getnumber(),time);
        current= voltageintegral*inductanceinv;
    }
    @Override
    public double getCurrent() {
        return current;
    }
}