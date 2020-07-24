
package Node;
import Component.*;

import java.util.ArrayList;

public class Node {
    private double voltage;
    private double voltage_p;
    private double voltagederive;
    private double voltageintegral;
    private double current;
    private double current_p;
    private int number;
    private boolean added;
    private boolean visited;
    private boolean visitcyc;
    private int union;
    protected ArrayList<Branch> attachments;
    protected ArrayList<Node> neighbor;
    double time;

    public Node(int number){
        this.number=number;
    }
    protected Node(int number, ArrayList<Branch> attachments){
        this.number=number;
        this.attachments=attachments;
        this.added=false;
        this.visited=false;
    }

    protected Node() {
    }

    public ArrayList<Node> getNeighbor() {
        return neighbor;
    }

    public ArrayList<Branch> getAttachments() {
        return attachments;
    }

    public void setAdded(boolean a)
    {
        added=a;
    }
    public boolean getAdded()
    {
        return added;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean getVisited()
    {
        return visited;
    }

    public void setVisitcyc(boolean visitcyc) {
        this.visitcyc = visitcyc;
    }
    public boolean getVisitcyc(){
        return visitcyc;
    }

    public void setUnion(int a)
    {
        union=a;
    }
    public int getUnion()
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

    public void setVoltage_p(double voltage_p) {
        this.voltage_p = voltage_p;
    }

    public double getVoltage_p() {
        return voltage_p;
    }

    public void setVoltagederive(double voltagederive) {
        this.voltagederive=voltagederive;
    }

    public double getVoltagederive() {
        return voltagederive;
    }

   /* public void setCurrentleaving(double currentleaving){
        this.currentleaving=currentleaving;
    }
    public double getCurrentleaving() {
        return currentleaving;
    }*/

    public void setVoltageintegral(double voltageintegral) {
        this.voltageintegral=voltageintegral;
    }


    public double getVoltageintegral() {
        return voltageintegral;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent_p(double current_p) {
        this.current_p = current_p;
    }

    public double getCurrent_p() {
        return current_p;
    }
}