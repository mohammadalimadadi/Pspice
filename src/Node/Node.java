package Node;
import Component.*;

import java.util.ArrayList;

public class Node {
    double voltage;
    double voltagederive;
    double voltageintegral;
    double currentleaving;
    int number;
    boolean added = false;
    int union;
    protected ArrayList<Branch> attachments;
    protected ArrayList<Node> neighbor;
    double time;

    protected Node(int number){
        this.number=number;
    }
    protected Node(int number, ArrayList<Branch> attachments){
        this.number=number;
        this.attachments=attachments;
    }

    protected Node() {
    }

    public ArrayList<Node> getNeighbor() {
        return neighbor;
    }

    public ArrayList<Branch> getAttachments() {
        return attachments;
    }

    public boolean setAdded(boolean a)
    {
        added=a;
    }
    public boolean getAdded()
    {
        return added;
    }
    public boolean setUnion(int a)
    {
        union=a;
    }
    public boolean getUnion()
    {
        return union;
    }


   public void AddingBranch(Branch b){
    attachments.add(b);
   }

    public int getnumber() {
        return number;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }


    public double getVoltage() {
        return voltage;
    }


    public void setVoltagederive(double voltagederive) {
        this.voltagederive=voltagederive;
    }

    public double getVoltagederive() {
        return voltagederive;
    }

    public void setCurrentleaving(double currentleaving){
        this.currentleaving=currentleaving;
    }

    public double getCurrentleaving() {
        return currentleaving;
    }

    public void setVoltageintegral(double voltageintegral) {
        this.voltageintegral=voltageintegral;
    }


    public double getVoltageintegral() {
        return voltageintegral;
    }

}