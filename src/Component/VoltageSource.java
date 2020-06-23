package Component;

public class VoltageSource extends Branch {
    private double voltage;
    private double vo;
    private double va;
    private double freq;
    private double phase;
    protected VoltageSource(String name,Node node1,Node node2,double vo,double va,double freq,double phase) {
        super(name,node1,node2);
        this.vo=vo;
        this.va=va;
        this.freq=freq;
        this.phase=phase;
    }

    @Override
    public void setVoltage(){
        this.voltage= va*Math.sin(2*Math.PI*freq*time+phase)+vo;
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
    @Override
    public void setCurrent() {
        current = node1.currentleaving;
    }

    @Override
    public double getCurrent() {
        return current;}
}