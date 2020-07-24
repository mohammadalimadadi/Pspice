package Graphic;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.*;

public class Diagram extends JPanel{
    private int[] values;
    private int time;
    private int step;
    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D g2d=(Graphics2D) g;
       Random random=new Random();

       setStep(600/values.length);
       //base of diagram

       g2d.setStroke(new BasicStroke(2.0f));
       g2d.drawLine(20,20,20,440);
       g2d.drawLine(20,220,550,220);

       g2d.drawLine(20,20,10,30);
       g2d.drawLine(20,20,30,30);
       g2d.drawLine(550,220,540,230);
       g2d.drawLine(550,220,540,210);

       g2d.drawLine(10,40,30,40);
       g2d.drawLine(10,130,30,130);

       g2d.drawLine(10,310,30,310);
       g2d.drawLine(10,400,30,400);

       g2d.drawLine(530,210,530,230);
       g2d.drawLine(275,210,275,230);

       float[] dashes={10};
       g2d.setPaint(new Color(random.nextInt(256),
               random.nextInt(256),
               random.nextInt(256)));

       g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND
       ,BasicStroke.JOIN_ROUND,10,dashes,15));
       for(int i=0;i<13;i++) {
           if(i!=6)
                g2d.draw(new Line2D.Double(20,40+i*30,530,40+i*30));
       }
       for(int i=1;i<18;i++) {
               g2d.draw(new Line2D.Double(20+i*30,20,20+i*30,410));
       }
       g2d.setPaint(Color.black);



       g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));

       g.drawString(name,10,10);
       g.drawString("Time",550,225);

       double MAX,MIN;
       MAX=getMax();
       MIN=getMin();

       if(MAX>-1*MIN) {
           g.drawString(Double.toString(MAX), 35, 45);
           g.drawString(Double.toString(MAX / 2.0), 35, 135);
           g.drawString(Double.toString(-1*MAX / 2.0), 35, 405);
           g.drawString(Double.toString(-1*MAX), 35, 315);
           }
       if(MAX<-1*MIN) {
           g.drawString(Double.toString(-1*MIN), 35, 45);
           g.drawString(Double.toString(-1*MIN / 2.0), 35, 135);
           g.drawString(Double.toString(MIN), 35, 405);
           g.drawString(Double.toString(MIN/2.0), 35, 315);
       }
       g.drawString(Double.toString(time),520,245);
       g.drawString(Double.toString(time/2.0),270,245);

       g2d.setColor(new Color(random.nextInt(256),
               random.nextInt(256),
               random.nextInt(256)));

       g2d.setStroke(new BasicStroke(3.0f));

       int[] revalues=new int[getValues().length];

       if((MAX>0 && MIN==0)||(MAX>-1*MIN))
           for (int i=0;i<getValues().length;i++)
           {
           revalues[i]= (int) (getValues()[i]*180/MAX);
           }
       if((MAX==0 && MIN<0)||(MAX<-1*MIN))
           for (int i=0;i<getValues().length;i++)
           {
               revalues[i]= (int) (-1*getValues()[i]*180/MIN);
           }
       //start drawing
       g2d.translate(20,220);
       int[] times=new int[step];
       for (int i=0;i<values.length;i++) {
           times[i]=i*step;
       }
       for (int i=0;i<values.length;i++) {
           revalues[i]*=-1;
       }
       //draw Poly line
       g.drawPolyline(times,revalues,revalues.length);
   }

    public double getMin() {
        double MIN=0;
        for (int i=0;i<getValues().length;i++)
        {
            if(MIN>getValues()[i])
                MIN=getValues()[i];
        }
        return MIN;
    }

    public double getMax() {
        double MAX=0;
        for (int i=0;i<getValues().length;i++)
        {
            if(MAX<getValues()[i])
                MAX=getValues()[i];
        }
        return MAX;
    }
}//end of class

class showDiagram
{
    public static void main(String[] args)
    {
        showDiagram diagram=new showDiagram();
        int[] value={1000,20,30,-50,-6,-70,7};
        diagram.show("Voltage",value,5);
    }
    public void show(String name,int[] values,int time)
    {
        JFrame frame=new JFrame("Diagram of: "+name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,100);

        Diagram bold=new Diagram();
        bold.setValues(values);
        bold.setTime(time);
        bold.setName(name);
        bold.setBackground(Color.white);

        frame.add(bold);
        frame.setSize(600,500);
        frame.setVisible(true);
    }
}
