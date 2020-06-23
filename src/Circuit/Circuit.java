import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import Node.Node;
import Component.*;
import matheMathics.*;
import netList.netList;

public class Circuit extends Object {
    //fields
    Node node[]=new Node[1000];
    ArrayList<Branch> branch = new ArrayList<Branch>();

    //getters

    VoltageSource[] getVoltageSources() {
    }

    CurrentSource[] getCurrentSources() {
    }

    Resistor[] getResistors() {
    }

    Capacitor[] getCapacitors() {
    }

    Inductor[] getInductors() {
    }

    Diode[] getDiodes() {
    }
    //constructor
    protected Circuit() {
    }

    //first methods

    Circuit(Circuit circuit) {
    }

    Circuit(int noNodes, int noBranches) {
    }

    //analyzing Circuit
    void solve() {
        Node instance=new Node();
        PriorityQueue<Node> n=new PriorityQueue<Node>();
        for(Node node: node[0].getneighbor)
        {
            n.add(node);
            node.setAdded(true);
        }
         instance=n.peek();
        for(Branch b: instance.getAttachments())
        {
            if(b instanceof VoltageSource ||b instanceof E|| b instanceof G)
            {

                if(instance.getnumber()==b.getNode1().getnumber())
                {
                  b.getNode1().setAdded(false);
                  b.
                }






            }

        }







    }

    void add(Branch branch, Node start, Node end) {
    }

    void remove(int branchIndex) {
    }

    void connectNodes(Node node[]) {
    }

    void replace(int branchIndex, Branch branch) {
    }
}
