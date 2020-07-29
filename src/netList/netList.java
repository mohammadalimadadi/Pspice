package netList;

import java.util.Scanner;
import Circuit.Circuit;
import Node.Node;
import Component.*;

public class netList {
    static Node detectNode(String s, Circuit circuit) {
        if (Integer.parseInt(s) >= 0 && circuit.getNode()[Integer.parseInt(s)]==null ) {
            Node node = new Node(Integer.parseInt(s));
            circuit.getNode()[Integer.parseInt(s)] = node;
            return node;
        }
        if(Integer.parseInt(s)<0)
            System.out.println("Your node is <0");
        return circuit.getNode()[Integer.parseInt(s)];
    }

    static double detectnumber(String s) {
        try {
            if((s.charAt(s.length()-1) >= '0') && (s.charAt(s.length()-1) <= '9'))
                return Double.parseDouble(s);

            double n = Double.parseDouble(s.substring(0, s.length() -1));

            if (s.charAt(s.length()-1) == 'p')
                n *= Math.pow(10, -12);
            else if (s.charAt(s.length()-1) == 'n')
                n *= Math.pow(10, -9);
            else if (s.charAt(s.length()-1) == 'u')
                n *= Math.pow(10, -6);
            else if (s.charAt(s.length()-1) == 'm')
                n *= Math.pow(10, -3);
            else if (s.charAt(s.length()-1) == 'M')
                n *= Math.pow(10, 3);
            else if (s.charAt(s.length()-1) == 'G')
                n *= Math.pow(10, 6);
            return n;
        }
        catch (Exception ignored){
            System.out.println("Your number is not readable!!!");
        }
        return 0;
    }

    public Circuit readingFile(String  S) {

        Circuit circuit = new Circuit();
        circuit.makingArray();
        Scanner input = new Scanner(S);
        while (input.hasNextLine()) {
            int flag = 1;
            String line;
            line = input.nextLine();
            if (!line.equals(".END") && line.charAt(0) != '*') {
                String[] subLine = line.split("\\s+");
                if (subLine[0].charAt(0) == 'R') {
                    if (subLine.length != 4)
                        flag = 0;
                    else {
                        String name = subLine[0];
                        String node1 = subLine[1];
                        String node2 = subLine[2];
                        String resistance = subLine[3];
                        Node n1 = detectNode(node1, circuit);
                        Node n2 = detectNode(node2, circuit);
                        double resist = detectnumber(resistance);
                        Branch b = new Resistor(name, n1, n2, resist);
                        circuit.getBranches().add(b);
                        assert n1 != null;
                        n1.AddingBranch(b);
                        assert n2 != null;
                        n2.AddingBranch(b);
                    }
                } else if (subLine[0].charAt(0) == 'L') {
                    if (subLine.length != 4)
                        flag = 0;
                    else {
                        Node n1, n2;
                        String name, node1, node2, inductance;
                        name = subLine[0];
                        node1 = subLine[1];
                        node2 = subLine[2];
                        inductance = subLine[3];
                        n1 = detectNode(node1, circuit);
                        n2 = detectNode(node2, circuit);
                        double induct = detectnumber(inductance);
                        Branch b = new Inductor(name, n1, n2, induct);
                        circuit.getBranches().add(b);
                        assert n1 != null;
                        n1.AddingBranch(b);
                        assert n2 != null;
                        n2.AddingBranch(b);
                    }
                } else if (subLine[0].charAt(0) == 'C') {
                    if (subLine.length != 4)
                        flag = 0;
                    else {
                        Node n1, n2;
                        String name, node1, node2, capacitance;
                        name = subLine[0];
                        node1 = subLine[1];
                        node2 = subLine[2];
                        capacitance = subLine[3];
                        n1 = detectNode(node1, circuit);
                        n2 = detectNode(node2, circuit);
                        double capa = detectnumber(capacitance);
                        Branch b = new Capacitor(name, n1, n2, capa);
                        circuit.getBranches().add(b);
                        assert n1 != null;
                        n1.AddingBranch(b);
                        assert n2 != null;
                        n2.AddingBranch(b);
                    }
                } else if (subLine[0].charAt(0) == 'I') {
                    if ((subLine.length != 4) && (subLine.length != 7))
                        flag = 0;
                    else {
                        if (subLine.length == 7) {
                            Node n2;
                            String name = subLine[0];
                            String node1 = subLine[1];
                            String node2 = subLine[2];
                            String offSet = subLine[3];
                            String amplitude = subLine[4];
                            String frequence = subLine[5];
                            String phase = subLine[6];
                            Node n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            double amp = detectnumber(amplitude);
                            double fre = detectnumber(frequence);
                            double pha = detectnumber(phase);
                            Branch b = new CurrentSource(name, n1, n2, off, amp, fre, pha);
                            circuit.getBranches().add(b);
                            assert n1 != null;
                            n1.AddingBranch(b);
                            assert n2 != null;
                            n2.AddingBranch(b);
                        } else if (subLine.length == 4) {
                            Node n2;
                            String name = subLine[0];
                            String node1 = subLine[1];
                            String node2 = subLine[2];
                            String offSet = subLine[3];
                            Node n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            Branch b = new CurrentSource(name, n1, n2, off, 0, 0, 0);
                            circuit.getBranches().add(b);
                            assert n1 != null;
                            n1.AddingBranch(b);
                            assert n2 != null;
                            n2.AddingBranch(b);
                        }
                    }
                } else if (subLine[0].charAt(0) == 'V') {
                    if ((subLine.length != 7) && (subLine.length != 4))
                        flag = 0;
                    else {
                        if (subLine.length == 7) {
                            String name = subLine[0];
                            String node1 = subLine[1];
                            String node2 = subLine[2];
                            String offSet = subLine[3];
                            String amplitude = subLine[4];
                            String frequence = subLine[5];
                            String phase = subLine[6];
                            Node n1 = detectNode(node1, circuit);
                            Node n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            double amp = detectnumber(amplitude);
                            double fre = detectnumber(frequence);
                            double pha = detectnumber(phase);
                            Branch b = new VoltageSource(name, n1, n2, off, amp, fre, pha);
                            circuit.getBranches().add(b);
                            assert n1 != null;
                            n1.AddingBranch(b);
                            assert n2 != null;
                            n2.AddingBranch(b);
                        } else if (subLine.length == 4) {
                            String name = subLine[0];
                            String node1 = subLine[1];
                            String node2 = subLine[2];
                            String offSet = subLine[3];
                            Node n1 = detectNode(node1, circuit);
                            Node n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            Branch b = new VoltageSource(name, n1, n2, off, 0, 0, 0);
                            circuit.getBranches().add(b);
                            assert n1 != null;
                            n1.AddingBranch(b);
                            assert n2 != null;
                            n2.AddingBranch(b);
                        }
                    }
                } else if (subLine[0].charAt(0) == 'G') {
                    if (subLine.length != 6)
                        flag = 0;
                    else {
                        Node n1, n2, n3, n4;
                        String name, node1, node2, node3, node4, zarib;
                        name = subLine[0];
                        node1 = subLine[1];
                        node2 = subLine[2];
                        node3 = subLine[3];
                        node4 = subLine[4];
                        zarib = subLine[5];
                        n1 = detectNode(node1, circuit);
                        n2 = detectNode(node2, circuit);
                        n3 = detectNode(node3, circuit);
                        n4 = detectNode(node4, circuit);
                        double z = detectnumber(zarib);
                        Branch b = new G(name, n1, n2, n3, n4, z);
                        circuit.getBranches().add(b);
                        assert n1 != null;
                        n1.AddingBranch(b);
                        assert n2 != null;
                        n2.AddingBranch(b);
                    }
                } else if (subLine[0].charAt(0) == 'F') {
                    if (subLine.length != 5)
                        flag = 0;
                    else {
                        Node n1, n2;
                        String name, node1, node2, bran, zarib;
                        Branch b = null;
                        name = subLine[0];
                        node1 = subLine[1];
                        node2 = subLine[2];
                        bran = subLine[3];
                        zarib = subLine[4];
                        n1 = detectNode(node1, circuit);
                        n2 = detectNode(node2, circuit);
                        for (int i = 0; i < circuit.getBranches().size(); i++)
                            if (circuit.getBranches().get(i).getName().equals(bran))
                                b = circuit.getBranches().get(i);
                        double z = detectnumber(zarib);
                        Branch s = new F(name, n1, n2, b, z);
                        circuit.getBranches().add(s);
                        assert n1 != null;
                        n1.AddingBranch(s);
                        assert n2 != null;
                        n2.AddingBranch(s);
                    }
                } else if (subLine[0].charAt(0) == 'E') {
                    if (subLine.length != 6)
                        flag = 0;
                    else {
                        String name = subLine[0];
                        String node1 = subLine[1];
                        String node2 = subLine[2];
                        String node3 = subLine[3];
                        String node4 = subLine[4];
                        String zarib = subLine[5];
                        Node n1 = detectNode(node1, circuit);
                        Node n2 = detectNode(node2, circuit);
                        Node n3 = detectNode(node3, circuit);
                        Node n4 = detectNode(node4, circuit);
                        double z = detectnumber(zarib);
                        Branch b = new E(name, n1, n2, n3, n4, z);
                        circuit.getBranches().add(b);
                        assert n1 != null;
                        n1.AddingBranch(b);
                        assert n2 != null;
                        n2.AddingBranch(b);
                    }
                } else if (subLine[0].charAt(0) == 'H') {
                    if (subLine.length != 5)
                        flag = 0;
                    else {
                        Branch b = null;
                        String name = subLine[0];
                        String node1 = subLine[1];
                        String node2 = subLine[2];
                        String bran = subLine[3];
                        String zarib = subLine[4];
                        Node n1 = detectNode(node1, circuit);
                        Node n2 = detectNode(node2, circuit);
                        for (int i = 0; i < circuit.getBranches().size(); i++)
                            if (circuit.getBranches().get(i).getName().equals(bran))
                                b = circuit.getBranches().get(i);
                        double z = detectnumber(zarib);
                        Branch s = new H(name, n1, n2, b, z);
                        circuit.getBranches().add(s);
                        if (n1 != null) {
                            n1.AddingBranch(s);
                        }
                        assert n2 != null;
                        n2.AddingBranch(s);
                    }

                } else if (subLine[0].charAt(0) == 'D') {
                    if (subLine.length != 4)
                        flag = 0;
                    else {
                        Node n1, n2;
                        String name, node1, node2;
                        flag = 1;
                        name = subLine[0];
                        node1 = subLine[1];
                        node2 = subLine[2];
                        n1 = detectNode(node1, circuit);
                        n2 = detectNode(node2, circuit);
                        Branch s = new Diode(name, n1, n2, flag);
                        circuit.getBranches().add(s);
                        assert n1 != null;
                        n1.AddingBranch(s);
                        assert n2 != null;
                        n2.AddingBranch(s);
                    }
                } else if (subLine[0].equals("dv")) {
                    if (subLine.length != 2)
                        flag = 0;
                    else {
                        flag = 1;
                        double dv = detectnumber(subLine[1]);
                        circuit.setDv(dv);
                    }
                } else if (subLine[0].equals("di")) {
                    if (subLine.length != 2)
                        flag = 0;
                    else {
                        flag = 1;
                        double di = detectnumber(subLine[1]);
                        circuit.setDi(di);
                    }
                } else if (subLine[0].equals("dt")) {
                    if (subLine.length != 2)
                        flag = 0;
                    else {
                        flag = 1;
                        double tick = detectnumber(subLine[1]);
                        circuit.setTick(tick);
                    }
                }
                //for other purposes
                if (flag == 0)
                    System.out.println("your data is not true");
            }
        }
        circuit.setNodeArray();

        //adding Nodes to node
        for(int i=0;i< circuit.getNode().length ;i++)
        {
            for (int j=0;j< circuit.getNode()[i].getAttachments().size() ;j++)
            {
                if (circuit.getNode()[i].getAttachments().get(j).getNode1().equals(circuit.getNode()[i]))
                    if (!circuit.getNode()[i].getNeighbor().contains(circuit.getNode()[i].getAttachments().get(j).getNode2()))
                        circuit.getNode()[i].getNeighbor().add(circuit.getNode()[i].getAttachments().get(j).getNode2());
                if (circuit.getNode()[i].getAttachments().get(j).getNode2().equals(circuit.getNode()[i]))
                    if (!circuit.getNode()[i].getNeighbor().contains(circuit.getNode()[i].getAttachments().get(j).getNode1()))
                        circuit.getNode()[i].getNeighbor().add(circuit.getNode()[i].getAttachments().get(j).getNode1());
            }
        }
        //Adding branches to branch
        circuit.setBranchArray();
        for (int i=0;  i<circuit.getBranches().size() ;i++)
        {
            circuit.getBranch()[i]=circuit.getBranches().get(i);
        }
        return circuit;
    }
}//End of class netList
