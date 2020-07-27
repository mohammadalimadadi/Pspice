import Circuit.Circuit;
import Component.Branch;
import Node.Node;
import netlist.Netlist;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //  String directory;
        //Scanner input = new Scanner(System.in);
        //directory=input.nextLine();
        File file = new File("C:\\Users\\Asus\\Desktop\\test.txt");
        Netlist net = new Netlist();
        Circuit cir = net.readingFile(file);


      /*  for (int i = 0; i <= cir.settingUnions().size() - 1; i++) {
            for (int j = 0; j <= cir.settingUnions().get(i).size() - 1; j++)
                System.out.print(cir.settingUnions().get(i).get(j).getnumber() + " ");
            System.out.println();*/
            // ArrayList<LinkedList<Node>> n=cir.settingUnions();
        ArrayList<LinkedList<Node>> unions=cir.settingUnions();
          for (int i=0;i<=cir.getBranch().length-1;i++){
              cir.getBranch()[i].setCurrent(0);
              cir.getBranch()[i].setCurrent_p(0);
              cir.getBranch()[i].setCurrent_n(0);
          }
        for (int i=0;i<=cir.getNode().length-1;i++){
            cir.getNode()[i].setCurrent(0);
            cir.getNode()[i].setCurrent_p(0);
            cir.getNode()[i].setCurrent_n(0);
            cir.getNode()[i].setVoltage(0);
            cir.getNode()[i].setVoltage_p(0);
            cir.getNode()[i].setVoltage_n(0);
        }

        double current=0;
        int count=0;
        for(int i=0;i<=1000;i++){
            cir.updatingBranches();
            cir.updatingNodes();
          //if(i%100==0)
                cir.printingResult();
       /*     for(Node node: cir.getNode()){
              for(Branch branch: node.getAttachments()){
                  if(node.equals(branch.getNode1()))
                  current+=branch.getCurrent();
                  else
                  if(node.equals(branch.getNode2()))
                      current-=branch.getCurrent();

              }
             if(current<=Math.abs(0.0001))
                 count++;
            //  System.out.println("Node+" + node.getnumber()+" " +current);
              current=0;
          }
          if(count==cir.getNode().length)
              break;*/
        }

    }//end of main
}//end of Main
