package Circuit;


import java.util.*;
import java.util.Queue;

import Node.Node;
import Component.*;
import matheMathics.*;

public class Circuit extends Object {
    //fields
  private   Node[] node;
    LinkedList<Node> [] unions;
  private   Branch[]branch ;
  private double tick;
  private double dv;
  private double di;
  int countNode=0;
  int countBranch=0;
    ArrayList<LinkedList<Node>> cycles;
    ArrayList<LinkedList<Branch>> brcyc;

    public Node[] getNode() {
        return node;
    }

    public Branch[] getBranch() {
        return branch;
    }

    //constructor
    protected Circuit() {
    }

    //first methods

    public Circuit(Circuit circuit) {
    }

   public Circuit(int noNodes, int noBranches,double tick,double dv) {
        node=new Node[noNodes];
        branch=new Branch[noBranches];
        this.tick=tick;
        this.dv=dv;
    }

    public void addingNode(Node node){
        this.node[countNode]=node;
        countNode++;
    }

    public void addingBranch(Branch branch){
        this.branch[countBranch]=branch;
        countBranch++;
    }

    public void updatingBranches(){
        for(int i=0;i<=branch.length-1;i++){
            branch[i].setCurrent(tick,dv,di);
        }
    }

    public void updatingNodes() {
        int flag = 0;
        int unicount = 0;
        int count=0;
        Node node_help;
        ArrayList<Branch> neighborbranchs;


            for (int i = 0; i <= unions.length - 1; i++) {
                //For indentifying union's type
                for (Node node : unions[i]) {
                    node.setVisited(false);
                    if (node.getnumber() == 0) {
                        flag = 1;
                        break;
                    }
                }
                if(flag==0) {
                    //2 below lines must be edited *************
                    for (Node node : unions[i]) {
                        node.setCurrent(0);
                        node.setCurrent_p(0);
                    }

                    neighborbranchs = new ArrayList<>();
                    for (Node node : unions[i]) {
                        for (Branch br : node.getAttachments()) {
                            if (!((br instanceof VoltageSource) || (br instanceof E) || (br instanceof H))) {
                                neighborbranchs.add(br);
                            }
                        }
                    }
                    for (Branch branch : neighborbranchs) {
                        for (Node node : unions[i]) {
                            if (node.equals(branch.getNode1())) {
                                node.setCurrent(node.getCurrent() + branch.getCurrent());
                                node.setCurrent_p(node.getCurrent_p() + branch.getCurrent_p());
                                if (unions[i].contains(branch.getNode2())) {
                                    branch.getNode2().setCurrent(node.getCurrent() + branch.getCurrent());
                                    branch.getNode2().setCurrent_p(node.getCurrent_p() + branch.getCurrent_p());
                                }
                            } else if (node.equals(branch.getNode2())) {
                                node.setCurrent(node.getCurrent() - branch.getCurrent());
                                node.setCurrent_p(node.getCurrent_p() - branch.getCurrent_p());
                                if (unions[i].contains(branch.getNode1())) {
                                    branch.getNode1().setCurrent(node.getCurrent() + branch.getCurrent());
                                    branch.getNode1().setCurrent_p(node.getCurrent_p() + branch.getCurrent_p());
                                }
                            }
                        }
                    }
                    unions[i].element().setVoltage_p(unions[i].element().getVoltage());
                    for (Node node : unions[i]) {
                        node.setCurrent((node.getCurrent() + node.getCurrent_p()) / 2);
                    }


                    unions[i].element().setVoltage(unions[i].element().getVoltage() - (this.dv * node[i].getCurrent()));

                    Queue<Node> queue=new LinkedList<Node>();
                    queue.add(unions[i].element());
                    while (queue.size()!=0){
                        node_help=queue.poll();
                        Iterator<Node> node1=node_help.getNeighbor().listIterator();
                        while (node1.hasNext()){
                            Node node=node1.next();
                            for(Branch branch: node.getAttachments()){
                                if((branch.getNode1().equals(node_help))||(branch.getNode2().equals(node_help))){
                                    if(node.equals(branch.getNode1())){
                                        node.setVoltage(node.getVoltage()+branch.getVoltage());
                                        node.setVoltage_p(node.getVoltage_p()+branch.getVoltage());
                                    }
                                    else    if(node.equals(branch.getNode2())){
                                        node.setVoltage(node.getVoltage()-branch.getVoltage());
                                        node.setVoltage_p(node.getVoltage_p()-branch.getVoltage());
                                    }

                                }
                            }
                            if(!node.getVisited()){
                                node.setVisited(true);
                                queue.add(node);
                            }
                        }
                    }


                }

              else  if(flag==1){
                  //Setting  node's Voltage of union by BFS method in Graph

                    for(Node node : unions[i]){
                        if(node.getnumber()==0){
                            node.setVisited(true);
                            node.setVoltage_p(0);
                            node.setVoltage(0);
                            unicount=count;
                        }
                        count++;
                    }
                    Queue<Node> queue=new LinkedList<Node>();
                    queue.add(unions[i].get(unicount));
                    while (queue.size()!=0){
                        node_help=queue.poll();
                      Iterator<Node> node1=node_help.getNeighbor().listIterator();
                      while (node1.hasNext()){
                          Node node=node1.next();
                          for(Branch branch: node.getAttachments()){
                              if((branch.getNode1().equals(node_help))||(branch.getNode2().equals(node_help))){
                                  if(node.equals(branch.getNode1())){
                                      node.setVoltage(node.getVoltage()+branch.getVoltage());
                                      node.setVoltage_p(node.getVoltage_p()+branch.getVoltage());
                                  }
                                  else    if(node.equals(branch.getNode2())){
                                      node.setVoltage(node.getVoltage()-branch.getVoltage());
                                      node.setVoltage_p(node.getVoltage_p()-branch.getVoltage());
                                  }

                              }
                          }
                          if(!node.getVisited()){
                              node.setVisited(true);
                              queue.add(node);
                          }

                      }
                    }

                }

                    flag=0;
            }


    }


    public ArrayList<LinkedList<Node>> settingUnions() {
        // ArrayList<Queue<Node>> q=new ArrayList<>();
        // setAdded is for clearing code

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < node.length-1; i++)
            node[i].setUnion(i);

        for (Node n : node[0].getNeighbor()) {
            queue.add(n);
            n.setAdded(true);
        }

        for(Branch branch: node[0].getAttachments()){
            if ((branch instanceof VoltageSource) || (branch instanceof E) || (branch instanceof H)){
                if (branch.getNode1().equals(node[0]))
                    branch.getNode2().setUnion(node[0].getUnion());
                else
                    branch.getNode1().setUnion(node[0].getUnion());
            }
        }

        while (queue.size()<node.length){

            for (Node node : queue) {
                for (Branch br : node.getAttachments())
                    if ((br instanceof VoltageSource) || (br instanceof E) || (br instanceof H)) {
                        if (br.getNode1().equals(node))
                            br.getNode2().setUnion(node.getUnion());
                        else
                            br.getNode1().setUnion(node.getUnion());
                    }
            }
            for (Node node : queue) {
                for(Node node1: node.getNeighbor()) {
                    //one operation is added
                    if(!queue.contains(node1)&&(node1.getAdded()==false))
                    queue.add(node1);
                    node1.setAdded(true);
                }
            }
        }

       ArrayList<LinkedList<Node>> unions=new ArrayList<>();
        int count=0;
        for(int i=0;i<=node.length-1;i++){
            for(int j=0;j<=node.length-1;j++)
                if(node[j].getUnion()==i) {
                    unions.get(count).add(node[j]);
                    count++;
                }
        }
      this.unions=(LinkedList<Node> []) new Object [unions.size()];
      this.unions=(LinkedList<Node> []) unions.toArray();
        return unions;
    }

 



    //analyzing Circuit
    void solve() {




    }

    public void findingErrors(){
        ArrayList<LinkedList<Branch>> subgraphs;
        ArrayList<LinkedList<Branch>> cutsets;
        LinkedList<Node> onesub;
        int count = 0;
        Node node_help;
        int num=0;
        double[] voltcyc;

        //finding Cycles ***************
        cycles=new ArrayList<LinkedList<Node>>();
        brcyc=new ArrayList<LinkedList<Branch>>();
        Cycle cycle=new Cycle();
        int[] color=new int[node.length];
        Node[] par=new Node[node.length];
        int[] mark=new int[node.length];
        Cycle.CycleFinding(node[0],node[0], color,mark,par);

        cycles=cycle.MakingCycles(node,mark);  // have a question ************
        voltcyc=new double[cycles.size()];
        for(LinkedList<Node> cyc : cycles){
            for(Node node: cyc) {
                if (!(node.equals(cyc.get(cyc.indexOf(cyc.size() - 1))))) {
                    for (Branch branch : node.getAttachments()) {
                        if ((branch.getNode1().equals(cyc.get(cyc.indexOf(node) + 1))) || (branch.getNode2().equals(cyc.get(cyc.indexOf(node) + 1)))) {
                            brcyc.get(cycles.indexOf(cyc)).add(branch);
                            if((branch instanceof VoltageSource)||(branch instanceof E)||(branch instanceof H)){
                                if(branch.getNode1().equals(cyc.get(cyc.indexOf(node) + 1)))
                                    voltcyc[cycles.indexOf(cyc)]-=branch.getVoltage();
                                else
                                if(branch.getNode2().equals(cyc.get(cyc.indexOf(node) + 1)))
                                    voltcyc[cycles.indexOf(cyc)]+=branch.getVoltage();
                            }
                        }
                    }
                }
            }
         for(int i=0;i<=branch.length-1;i++){
             if(((branch[i].getNode1().equals(cyc.get(cyc.size()-1)))&&(branch[i].getNode2().equals(cyc.get(0))))||((branch[i].getNode2().equals(cyc.get(cyc.size()-1)))&&(branch[i].getNode1().equals(cyc.get(0)))))
                 brcyc.get(cycles.indexOf(cyc)).add(branch[i]);
         }
        }



        //for finding Error -2 *************
      /*  for(int i=0;i<=unions.length-1;i++){
            neighborbranchs=new ArrayList<>();
            for(Node node: unions[i]){
                for(Branch br: node.getAttachments()){
                    if(!((br instanceof VoltageSource)||(br instanceof E)||(br instanceof H))){
                        neighborbranchs.add(br);
                    }
                }
            }
        }*/
      subgraphs=new ArrayList<>();
      cutsets=new ArrayList<>();
      for (int i=0;i<(1<<branch.length);i++){
          for (int j=0;j<branch.length;j++)
              if((i&(i<<j))>0)
                  subgraphs.get(i).add(branch[j]);

      }
      for(LinkedList<Branch> brlist : subgraphs){
          for(Branch br: brlist){
              br.getNode1().getAttachments().remove(br);
              br.getNode1().getNeighbor().remove(br.getNode2());
              br.getNode2().getAttachments().remove(br);
              br.getNode2().getNeighbor().remove(br.getNode1());
          }
          for(Branch br: brlist){
              for (Node node: node){
                  node.setVisited(false);
              }
              Queue<Node> queue=new LinkedList<Node>();
              queue.add(br.getNode1());
              br.getNode1().setVisited(true);
              while (queue.size()!=0){
                  node_help=queue.poll();
                  Iterator<Node> node1=node_help.getNeighbor().listIterator();
                  while (node1.hasNext()){
                      Node node=node1.next();
                if(node.equals(br.getNode2())){
                    num=1;
                    break;
                }
                      if(!node.getVisited()){
                          node.setVisited(true);
                          queue.add(node);
                      }
                  }
                  if(num==1)
                      break;
              }
          }
          for(Branch br: brlist){
              br.getNode1().getAttachments().add(br);
              br.getNode1().getNeighbor().add(br.getNode2());
              br.getNode2().getAttachments().add(br);
              br.getNode2().getNeighbor().add(br.getNode1());
          }
          if (num==1){
              num=0;
              continue;
          }
          else
              cutsets.add(brlist);
      }

      double current_src=0;
       int flag2=0;
        for(LinkedList<Branch> brlist : cutsets){
            for (Node node: node){
                node.setVisited(false);
            }
            for(Branch br: brlist)
                if(!((br instanceof CurrentSource)||(br instanceof  F)||(br instanceof G))){
                    flag2=1;
                    break;
                }
            if(flag2==0) {
                for (Branch br : brlist) {
                    br.getNode1().getAttachments().remove(br);
                    br.getNode1().getNeighbor().remove(br.getNode2());
                    br.getNode2().getAttachments().remove(br);
                    br.getNode2().getNeighbor().remove(br.getNode1());
                }
                Queue<Node> queue = new LinkedList<Node>();
                onesub = new LinkedList<Node>();
                queue.add(brlist.get(0).getNode1());
                onesub.add(brlist.get(0).getNode1());
                brlist.get(0).getNode1().setVisited(true);
                while (queue.size() != 0) {
                    node_help = queue.poll();
                    Iterator<Node> node1 = node_help.getNeighbor().listIterator();
                    while (node1.hasNext()) {
                        Node node = node1.next();

                        if (!node.getVisited()) {
                            node.setVisited(true);
                            queue.add(node);
                            onesub.add(node);
                        }
                    }

                }
                for (Node node : onesub) {
                    for (Branch branch : brlist) {
                        if ((node.equals(branch.getNode1())) || (node.equals(branch.getNode1()))) {
                            if (node.equals(branch.getNode1()))
                                current_src+=branch.getCurrent();
                            else
                                current_src-=branch.getCurrent();

                        }
                    }
                }
                for (Branch br : brlist) {
                    br.getNode1().getAttachments().add(br);
                    br.getNode1().getNeighbor().add(br.getNode2());
                    br.getNode2().getAttachments().add(br);
                    br.getNode2().getNeighbor().add(br.getNode1());
                }
            }
            else{
                flag2=0;
                continue;
            }
            if(Math.abs(current_src)>0.001){
                System.out.println("ErrorNum: -2");
                System.exit(-2);
            }
        }


        // finding the error -3
        int flag3=0;

        for(Branch branch: branch){
            if((branch instanceof VoltageSource)||(branch instanceof E)||(branch instanceof H)){
                for (Branch branch1: branch.getNode1().getAttachments()){
                    if(branch.getNode1().equals(branch1.getNode1()))
                        if(branch.getNode2().equals(branch1.getNode2())){
                        if(((branch1 instanceof VoltageSource)||(branch1 instanceof E)||(branch1 instanceof H))&&(branch1.getVoltage()!=branch.getVoltage())&&(!(branch.equals(branch1)))){
                            flag3=1;
                            break;
                        }

                    }

                    if(branch.getNode1().equals(branch1.getNode2()))
                        if(branch.getNode2().equals(branch1.getNode1())){
                            if(((branch1 instanceof VoltageSource)||(branch1 instanceof E)||(branch1 instanceof H))&&(branch1.getVoltage()!=branch.getVoltage())&&(!(branch.equals(branch1)))){
                                flag3=1;
                                break;
                            }
                        }
                }
            }
            if(flag3==1)
                break;
        }
        if(flag3==1){
            System.out.println("Error : -3");
            System.exit(-3);
        }
  flag3=0;
        //Now we describe it generally for every loop with VoltageSource
        for(LinkedList<Branch> by : brcyc){
            for (Branch branch : by){
                if((branch instanceof VoltageSource)||(branch instanceof E)||(branch instanceof H)){
                    count++;
                }
            }
            if(count==by.size()){
                if(voltcyc[brcyc.indexOf(by)]!=0){
                    flag3=1;
                    break;
                }
            }

        }
        if(flag3==1){
            System.out.println("Error : -3");
            System.exit(-3);
        }


        //finding error of -4
        int flag4=1;
        for(Node node: node){
            if(node.getnumber()==0){
                flag4=0;
                break;
            }
        }
        if(flag4==1){
            System.out.println("Error : -4");
            System.exit(-4);
        }
        flag4=1;
        for(Branch branch: branch){
            if((branch instanceof VoltageSource)||(branch instanceof E)||(branch instanceof H))
                if((branch.getNode1().getnumber()==0)&&(branch.getNode2().getnumber()==0)) {
                    flag4 = 0;
                    break;
                }
        }
        if(flag4==0){
            System.out.println("Error : -4");
            System.exit(-4);
        }

        // finding Error -5
        int flag5=0;
        int nodesnum=0;

        for(Node nod: node){
            nod.setVisitcyc(false);
        }


        for(Node node: node){
            if(node.getNeighbor().size()==1){
                flag5=1;
                break;
            }
        }
        if(flag5==1) {
            System.out.println("Error : -5");
            System.exit(-5);
        }

        for(LinkedList<Node> cyc: cycles){
            if(cyc.contains(node[0])){
                for(Node nod: node){
                    if(!(nod.equals(node[0]))){
                        if(cyc.contains(nod))
                            nod.setVisitcyc(true);
                    }
                }
            }
        }
        for(Node nod: node){
            if(nod.getVisitcyc()==true)
                nodesnum++;
        }

        if(nodesnum!=node.length-1){
            System.out.println("Error : -5");
            System.exit(-5);
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
