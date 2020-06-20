import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

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
    Circuit[] superposition() {
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
