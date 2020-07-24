import Component.Branch;
import Component.VoltageSource;
import Node.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public abstract class testing {

    ArrayList< Queue<Node>> q=new ArrayList<>();
    for(int i=0;i<=100;i++){
        Node[i].added=false;
        Node[i].union=i;
    }

    for(int i=0: i<=100:i++){

        Queue<Node> queue=new LinkedList<>();
        q.add(queue);
        for(Node n : node[i].neighbor){
            q.get(i).add(n);
            n.added=true;
        }
        for(Node node: q.get(i).element().neighbor){
            for(Branch br :node.getAttachments() ){
                if(br instanceof VoltageSource)
                    node.union=node[i].union;
            }
            if(node.added==false){
                node.added=true;
                q.get(i).add(node);
            }
        }

      }
    ArrayList<LinkedList<Node>> unions=new ArrayList<>();
    for(int i=0;i<=100;i++){
        for(j=0;j<=100;j++)
            if(node[j].union==i)
                unions.get(j).add(node[j]);

    }


}


