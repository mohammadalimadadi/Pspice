package Graphic;


import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class GpCircuit implements ActionListener {
    JTextArea jtextArea;
    JMenuItem cut,copy,paste,selectAll;
    JMenuItem load,close,save;
    JFrame jframe;
    String filepath;



    public static void main(String[] args)
    {
        GpCircuit instance = new GpCircuit();
        instance.run();


    }

    public void run()
    {
        jframe=new JFrame("Circuit HSpice");
        jframe.setSize(1000,600);
        jframe.setLayout(null);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);

        /*JProgressBar jb=new JProgressBar(0,2000);
        jb.setBounds(410,250,160,30);
        jb.setValue(0);
        jb.setStringPainted(true);
        jframe.add(jb);
        int i=0;
        while(i<=2000) {
            jb.setValue(i);
            i+=20;
            try
            {
                Thread.sleep(25);
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
        }
        jb.setVisible(false);*/


        jtextArea = new JTextArea();
        jtextArea.setBounds(5,50,400,500);
        jframe.add(jtextArea);

        JLabel l=new JLabel();
        l.setText("Circuit");
        l.setBounds(10,0,200,40);
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

            }
        });
        b1.setBounds(450,10,100,40);
        b2.setBounds(640,10,100,40);
        b3.setBounds(820,10,100,40);
        jframe.add(b1);
        jframe.add(b2);
        jframe.add(b3);










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

