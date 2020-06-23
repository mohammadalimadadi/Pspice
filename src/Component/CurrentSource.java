package Component;

public class CurrentSource extends Branch {
    private double current;
    private double io;
    private double ia;
    private double freq;
    private double phase;
    protected CurrentSource(String name,Node node1,Node node2,double io,double ia,double freq,double phase) {
        super(name,node1,node2);
        this.io=io;
        this.ia=ia;
        this.freq=freq;
        this.phase=phase;
    }

    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }

    @Override
    public double getVoltage() {
        return voltage;
    }
    @Override
    public void setCurrent() {
        current =  ia*Math.sin(2*Math.PI*freq*time+phase)+io;
    }

    @Override
    public double getCurrent() {
        return current;}
}