import java.io.*;
import java.util.ArrayList;
public class Netlist {
    protected ArrayList<String> lines;
    Node[] nodes=new Node[1000];
  File file;
  FileReader br;
  Netlist(File file){
      this.file=file;
      lines=new ArrayList<>();
  }
  public void Scanning() throws IOException {
      BufferedReader br=new BufferedReader(new FileReader(file));
      String s;
      while((s=br.readLine())!=null){
          lines.add(s);
      }
  }
  public void setNodes(){
      int i=0;
      for(String s:lines){
          if(s==".end")
              break;
          String[]s1=s.split(" ");
          if(Integer.parseInt(s1[1])>=0) {
              if ((Integer.parseInt(s1[1])) == 0) {
                  Node gnd = new BasisNode();
                  nodes[0] = gnd;
              } else {
                  Node node = new SimpleNode();
                  nodes[Integer.parseInt(s1[1])]=node;
              }
          }
          if(Integer.parseInt(s1[2])>=0) {
              if ((Integer.parseInt(s1[2])) == 0) {
                  Node gnd = new BasisNode();
                  nodes[0] = gnd;
              } else {
                  Node node = new SimpleNode();
                  nodes[Integer.parseInt(s1[2])]=node;
              }
          }
      }
  }
    public void setComponents(){
      int i=0;
      //for example the below code is written for resistors. It is the main idea.
      ArrayList<Resistor> res =new ArrayList<>();
         for(String s:lines){
             if(s==".end")
                 break;
             String[]s1=s.split(" ");
             if(s1[0].charAt(0)=='r'){
                 Component r = new Resistor(String.valueOf(i),Integer.parseInt(s1[3]),nodes[Integer.parseInt(s1[1])],nodes[Integer.parseInt(s1[1])],0);
                 res.add((Resistor) r);
                 nodes[Integer.parseInt(s1[1])].attachments.add(r);
                 nodes[Integer.parseInt(s1[1])].attachments.add(r);
                 i++;
             }
         }
    }
    public void SendOperation(){
        for(String s:lines){
            if(s==".end")
                break;
            String[]s1=s.split(" ");
          if (s1[0]==".op")
              CircuitAnalysis.operatingpoint();
            if (s1[0]==".TRAN")
                CircuitAnalysis.Transient(Double.parseDouble(s1[1]),Double.parseDouble(s1[2]),Double.parseDouble(s1[3]));
            if (s1[0]==".ac")
                CircuitAnalysis.ACAnalysis(Double.parseDouble(s1[2]),Double.parseDouble(s1[3]),Double.parseDouble(s1[1]));
            if (s1[0]==".dc")
                CircuitAnalysis.DCAnalysis(Component source,startvalue, stopvalue, increment);
        }
    }


}
