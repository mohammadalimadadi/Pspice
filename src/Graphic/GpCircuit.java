package Graphic;


import Circuit.Circuit;
import netList.netList;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

public class GpCircuit implements ActionListener {
    JTextArea jtextArea=new JTextArea();
    JMenuItem cut,copy,paste,selectAll;
    JMenuItem load,close,save;
    JFrame jframe;
    static String filepath;
    static int flag=0;
    JLabel b1,b2,b3;


    public static void main(String[] args)
    {
        GpCircuit instance = new GpCircuit();
        instance.run();


    }

    public void run()
    {
        jframe=new JFrame("Circuit HPSpice");
        jframe.setSize(990,670);
        jframe.setLayout(null);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setBackground(Color.GRAY);
        jframe.setVisible(true);

        try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e)
        {

        }
        if (flag==0)
        {
            b1=new JLabel();
            b1.setText("Welcome to HPSpice");
            b1.setFont(new Font("Serif",Font.BOLD,72));
            b1.setBounds(170,100,800,100);
            jframe.getContentPane().add(b1);
            b2=new JLabel();
            b2.setText("Designed and Developed by");
            b2.setFont(new Font("Serif",Font.BOLD,30));
            b2.setBounds(300,350,800,100);
            jframe.getContentPane().add(b2);
            b3=new JLabel();
            b3.setText("Mohammaderfan Ramesh & Mohammad Alimadadi");
            b3.setFont(new Font("serif",Font.BOLD,30));
            b3.setBounds(170,450,800,100);
            jframe.getContentPane().add(b3);
        }


        if (flag==0) {
            jframe.getContentPane().setBackground(new Color(220,220,225));
            flag++;

            JProgressBar jb = new JProgressBar(0, 2000);
            jb.setBounds(330, 260, 320, 60);
            jb.setValue(0);
            jb.setStringPainted(true);
            jframe.add(jb);
            int i = 0;
            while (i <= 2000) {
                jb.setValue(i);
                i += 10;
                try {
                    Thread.sleep(25);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
            jb.setVisible(false);
            b1.setVisible(false);
            b2.setVisible(false);
            b3.setVisible(false);



        }


        jtextArea.setBounds(5,60,400,540);
        jtextArea.setBorder(new RoundBorder(40));
        jtextArea.setFont(new Font("Monospaced",Font.BOLD,16));
        jframe.add(jtextArea);

        JLabel l=new JLabel();
        l.setText("Circuit");
        l.setBounds(10,10,200,40);
        l.setFont(new Font("Serif", Font.PLAIN, 40));
        jframe.add(l);

        JMenuBar mb;
        JMenu file,edit,help;
        load=new JMenuItem("load");
        close=new JMenuItem("close");
        save=new JMenuItem("save");
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        load.addActionListener(this);
        close.addActionListener(this);
        save.addActionListener(this);

        mb=new JMenuBar();
        file=new JMenu("File");
        edit=new JMenu("Edit");
        help=new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(load);
        file.add(close);
        file.add(save);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        jframe.add(mb);
        jframe.setJMenuBar(mb);

        JButton b1,b2,b3;
        b1=new JButton("Load");
        b2=new JButton("Run");
        b3=new JButton("Draw");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Load();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtextArea.getText();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GpCircuit instance = new GpCircuit();
                instance.jtextArea.setText(jtextArea.getText());
                jframe.setVisible(false);
                instance.run();
            }
        });

        netList net = new netList();
        Circuit circuit = net.readingFile(jtextArea.getText());
        schematic bold = new schematic();
        if (circuit!=null) {
            bold.setBranches(circuit.getBranches());
            bold.setNodes(circuit.getNode());
        }
        bold.setBounds(400, 50, 600, 600);
        jframe.getContentPane().add(bold);
        jframe.getContentPane().revalidate();
        jframe.getContentPane().repaint();



        b1.setBounds(450,10,100,40);
        b2.setBounds(640,10,100,40);
        b3.setBounds(820,10,100,40);
        jframe.add(b1);
        jframe.add(b2);
        jframe.add(b3);

        jframe.setLayout(null);
        jframe.setVisible(false);
        jframe.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut)
            jtextArea.cut();
        if(e.getSource()==paste)
            jtextArea.paste();
        if(e.getSource()==copy)
            jtextArea.copy();
        if(e.getSource()==selectAll)
            jtextArea.selectAll();
        if(e.getSource()==load)
            Load();
        if (e.getSource()==close)
            close();
        if (e.getSource()==save)
            save();

    }
    public void Load() {
        JFileChooser fc=new JFileChooser();
        int i=fc.showOpenDialog(jframe);
        if(i==JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            filepath=f.getPath();
            try{
                BufferedReader br=new BufferedReader(new FileReader(filepath));
                String s1="",s2="";
                while((s1=br.readLine())!=null){
                    s2+=s1+"\n";
                }
                jtextArea.setText(s2);
                br.close();
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
    }
    public void close()
    {
        jtextArea.setText("");
    }
    public void save() {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(jtextArea.getText());
            myWriter.close();
        }
        catch (Exception e) {}
    }
}//End of

class RoundBorder implements Border {

    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(6.0f));
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, getRadius(), getRadius()));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int value = getRadius() / 2;
        return new Insets(value, value, value, value);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

}

