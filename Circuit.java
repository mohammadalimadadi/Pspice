public class Circuit extends Object{
    //fields
    Node node[];
    Branch branch[];
    //getters
    VoltageSource[] getVoltageSources() {}
    CurrentSource[]	getCurrentSources(){}
    Resistor[] getResistors(){}
    Capacitor[]	getCapacitors(){}
    Inductor[]	getInductors(){}
    Diode[]	getDiodes(){};
    //first methods
    Circuit (File file){}
    Circuit(Circuit circuit){}
    Circuit ( int noNodes, int noBranches){}
    //analyzing Circuit
    Circuit[] superposition(){}
    void add(Branch branch, Node start, Node end){}
    void remove(int branchIndex){}
    void connectNodes(Node node[]){}
    void replace(int branchIndex, Branch branch){}
}
