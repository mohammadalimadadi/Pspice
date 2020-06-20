package netList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class netList {
    static Node detectNode(String S, Circuit circuit) {
        if (Integer.parseInt(s) >= 0) {
            Node node = new Node(s);
            circuit.node[Integer.parseInt(s)] = node;
            return node;
        }
        return null;
    }

    static double detectnumber(String s) {
        if(s.charAt(s.length())>='0'&&s.charAt(s.length())<='9')
            return Double.parseDouble(s);
        double n = Double.parseDouble(s.substring(0, s.length() - 1));
        if (s.charAt(s.length()) == 'p')
            n *= Math.pow(10, -12);
        else if (s.charAt(s.length()) == 'n')
            n *= Math.pow(10, -9);
        else if (s.charAt(s.length()) == 'u')
            n *= Math.pow(10, -6);
        else if (s.charAt(s.length()) == 'm')
            n *= Math.pow(10, -3);
        else if (s.charAt(s.length()) == 'M')
            n *= Math.pow(10, 3);
        else if (s.charAt(s.length()) == 'G')
            n *= Math.pow(10, 6);
        return n;
    }

    public Circuit readingFile(File file) {
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                Circuit circuit = new Circuit();
                int flag = 1;
                String line;
                line = input.nextLine();
                while (!line.equals(".END")) {
                    String[] subLine = line.split("\\s+");
                    if (subLine[0].charAt(0) == 'R') {
                        if (subLine.length != 4)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, resistance;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            resistance = new String(subLine[3]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double resist = detectnumber(resistance);
                            Branch b = new Resistor(name, n1, n2, resist);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'L') {
                        if (subLine.length != 4)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, inductance;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            inductance = new String(subLine[3]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double induct = detectnumber(inductance);
                            Branch b = new Inductor(name, n1, n2, induct);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'C') {
                        if (subLine.length != 4)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, capacitance;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            capacitance = new String(subLine[3]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double capa = detectnumber(capacitance);
                            Branch b = new Capacitor(name, n1, n2, capa);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'I') {
                        if (subLine.length != 7)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, offSet, amplitude, frequence, phase;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            offSet = new String(subLine[3]);
                            amplitude = new String(subLine[4]);
                            frequence = new String(subLine[5]);
                            phase = new String(subLine[6]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            double amp = detectnumber(amplitude);
                            double fre = detectnumber(frequence);
                            double pha = detectnumber(phase);
                            Branch b = new CurrentSource(name, n1, n2, off, amp, fre, pha);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'V') {
                        if (subLine.length != 7)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, offSet, amplitude, frequence, phase;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            offSet = new String(subLine[3]);
                            amplitude = new String(subLine[4]);
                            frequence = new String(subLine[5]);
                            phase = new String(subLine[6]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            double off = detectnumber(offSet);
                            double amp = detectnumber(amplitude);
                            double fre = detectnumber(frequence);
                            double pha = detectnumber(phase);
                            Branch b = new VoltageSource(name, n1, n2, off, amp, fre, pha);
                            circuit.branch.add(b);
                        }

                    } else if (subLine[0].charAt(0) == 'G') {
                        if (subLine.length != 6)
                            flag = 0;
                        else {
                            Node n1, n2, n3, n4;
                            String name, node1, node2, node3, node4, zarib;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            node3 = new String(subLine[3]);
                            node4 = new String(subLine[4]);
                            zarib = new String(subLine[5]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            n3 = detectNode(node3, circuit);
                            n4 = detectNode(node4, circuit);
                            double z = detectnumber(zarib);
                            Branch b = new G(name, n1, n2, n3, n4, z);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'F') {
                        if (subLine.length != 5)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, bran, zarib;
                            Branch b;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            bran = new String(subLine[3]);
                            zarib = new String(subLine[4]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            for(int i=0;i<circuit.branch.size();i++)
                                if(circuit.branch.get(i).getName().equals(bran))
                                    b=circuit.branch.get(i);
                            double z = detectnumber(zarib);
                            Branch b = new F(name, n1, n2, b, z);
                            circuit.branch.add(b);
                        }
                    } else if (subLine[0].charAt(0) == 'E') {
                        if (subLine.length != 6)
                            flag = 0;
                        else {
                            Node n1, n2, n3, n4;
                            String name, node1, node2, node3, node4, zarib;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            node3 = new String(subLine[3]);
                            node4 = new String(subLine[4]);
                            zarib = new String(subLine[5]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            n3 = detectNode(node3, circuit);
                            n4 = detectNode(node4, circuit);
                            double z = detectnumber(zarib);
                            Branch b = new E(name, n1, n2, n3, n4, z);
                            circuit.branch.add(b);

                        }
                    } else if (subLine[0].charAt(0) == 'H') {
                        if (subLine.length != 5)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2, bran, zarib;
                            Branch b;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            bran = new String(subLine[3]);
                            zarib = new String(subLine[4]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            for(int i=0;i<circuit.branch.size();i++)
                                if(circuit.branch.get(i).getName().equals(bran))
                                    b=circuit.branch.get(i);
                            double z = detectnumber(zarib);
                            Branch b = new H(name, n1, n2, b, z);
                            circuit.branch.add(b);
                        }

                    } else if (subLine[0].charAt(0) == 'D') {
                        if (subLine.length != 4)
                            flag = 0;
                        else {
                            Node n1, n2;
                            String name, node1, node2;
                            int flag=1;
                            Branch b;
                            name = new String(subLine[0]);
                            node1 = new String(subLine[1]);
                            node2 = new String(subLine[2]);
                            n1 = detectNode(node1, circuit);
                            n2 = detectNode(node2, circuit);
                            Branch b = new Diode(name, n1, n2, flag);
                            circuit.branch.add(b);
                        }
                    } else
                    //for other purposes
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file.");
            System.exit(1);
        }
    }

}
