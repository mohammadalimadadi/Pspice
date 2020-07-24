package Component;

import Node.Node;

public class Diode extends Branch {
    String name;
    private double current;
    private double voltage;
    Node node1;
    Node node2;
    private double time;
    private int flag;
    //constructor
    public Diode(String name, Node node1, Node node2, int flag) {
        super(name,node1,node2);
        this.flag=flag;
    }
    public void setVoltage(){
        this.voltage=node1.getVoltage()-node2.getVoltage();
    }
    public double getVoltage(){
        return (node1.getVoltage()-node2.getVoltage()) ;
    }

    @Override
    public void setCurrent(double dt, double dv, double di) {
        //  CircuitAnalysis.setNodeVoltage(node1.getnumber(),time);
        //CircuitAnalysis.setNodeVoltage(node2.getnumber(),time);
        setVoltage();
        if(voltage>0)
            System.out.println("Impossible");
        //all of element's initial is zero
        if(voltage==0){
            for(Branch branch: node1.getAttachments()){
                if(node1.equals(branch.getNode1()))
                    current-=branch.getCurrent();
                else
                    current+=branch.getCurrent();
            }
        }
        else
            current=0;
    }

    public double getCurrent() {
        return current;}

    @Override
    public void setCurrent_p(double dt, double dv, double di) {
        if((node1.getVoltage_p()-node2.getVoltage_p())>0)
            System.out.println("Impossible");
        //all of element's initial is zero
        if((node1.getVoltage_p()-node2.getVoltage_p())==0){
            for(Branch branch: node1.getAttachments()){
                if(node1.equals(branch.getNode1()))
                    current_p-=branch.getCurrent_p();
                else
                    current_p+=branch.getCurrent_p();
            }
        }
        else
            current_p=0;
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