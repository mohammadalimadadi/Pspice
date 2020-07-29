package Component;

import Node.Node;

public class Capacitor extends Branch {
    protected static int capcount=1;
    private double capacity;
    private double elastance;
    // private double voltagederive;

    //constructor
    public Capacitor(String name, Node node1, Node node2, double capacity) {
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

    /* public void setVoltagederive() {
         voltagederive =node1.getVoltagederive()-node2.getVoltagederive();
     }

     public double getVoltagederive() {
         return voltagederive;
     }
 */
    @Override
    public void setVoltage(){
        voltage=node1.getVoltage()-node2.getVoltage();
    }
    @Override
    public double getVoltage(){
        return voltage ;
    }
    @Override
    public void setCurrent(double dt,double dv,double di) {
     /*   CircuitAnalysis.setNodeVoltagederive(node1.getnumber(),time);
        CircuitAnalysis.setNodeVoltagederive(node2.getnumber(),time);
        current= voltagederive*capacity;*/
        current=(capacity*((node1.getVoltage()-node1.getVoltage_p())-(node2.getVoltage()-node2.getVoltage_p())))/dt;
    }

    @Override
    public double getCurrent() {
        return current;
    }

    @Override
    public void setCurrent_p(double dt,double dv,double di) {
        current_p=(capacity*((node1.getVoltage()-node1.getVoltage_p())-(node2.getVoltage()-node2.getVoltage_p())+dv))/dt;
    }

    @Override
    public double getCurrent_p() {
        return current_p;
    }


}