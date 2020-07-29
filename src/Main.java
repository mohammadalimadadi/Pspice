import Circuit.Circuit;
import Component.Branch;
import Node.Node;
import netList.netList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String s1,S="";
        try{
            File file = new File("C:\\Users\\emking\\Documents\\project-1.txt");
            BufferedReader br=new BufferedReader(new FileReader(file.getPath()));

            while((s1=br.readLine())!=null){
                S+=s1+"\n";
            }
            br.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        netList netList = new netList();
        Circuit cir = netList.readingFile(S);

        ArrayList<LinkedList<Node>> unions = cir.settingUnions();

        for (int i=0; i<cir.getNode().length;i++)
        {
            cir.getNode()[i].setVoltage_n(0);
            cir.getNode()[i].setVoltage_n(0);
            cir.getNode()[i].setVoltage_n(0);
        }
        
        for (int i = 0; i < cir.getBranch().length ; i++) {
            cir.getBranch()[i].setCurrent(0);
            cir.getBranch()[i].setCurrent_p(0);
            cir.getBranch()[i].setCurrent_n(0);
        }
        for (int i = 0; i <= cir.getNode().length - 1; i++) {
            cir.getNode()[i].setCurrent(0);
            cir.getNode()[i].setCurrent_p(0);
            cir.getNode()[i].setCurrent_n(0);
        }

        for (int i = 0; i <= 1000; i++) {
            cir.updatingBranches(i*cir.getTick());
            cir.updatingNodes();
            if (i % 100 == 0)
                cir.printingResult();
        }
    }//end of main
//end of Main
}