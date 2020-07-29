package matheMathics;

import Node.Node;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cycle {
    static int cyclenum=0;
    static ArrayList<LinkedList<Node>> cycles;
    public Cycle(){}
    public  static void CycleFinding(Node u, Node p, int[] color, int[] mark, Node[] par){
        if(color[u.getnumber()]==2)
            return;

        if(color[u.getnumber()]==1){
            cyclenum++;
            Node cur=p;
            mark[cur.getnumber()]=cyclenum;
            while (!(cur.equals(u))){
                cur=par[cur.getnumber()];
                mark[cur.getnumber()]=cyclenum;
            }
            return;
        }
        par[u.getnumber()]=p;

        color[u.getnumber()]=1;

        for (Node v: u.getNeighbor()){
            if(v.equals(par[u.getnumber()]))
                continue;
            CycleFinding(v,u,color,mark,par);
        }
        color[u.getnumber()]=2;

    }

    public  static ArrayList<LinkedList<Node>> MakingCycles(Node[] node, int[] mark){
        cycles=new ArrayList<LinkedList<Node>>();
        for(int i=0;i<=mark.length-1;i++){
            LinkedList<Node> list=new LinkedList<Node>();
            cycles.add(list);
        }
        for (int i=0;i<=node.length-1;i++) {
            if (mark[i] != 0)
                cycles.get(mark[i]).add(node[i]);
        }
        return cycles;
    }

}
