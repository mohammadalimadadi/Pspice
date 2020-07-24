package Graphic;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import Component.*;

import Circuit.Circuit;
import Node.*;
import netList.netList;

public class schematic extends JPanel{
    private Node[] nodes;
    private ArrayList<Branch> branches;

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    int[][] plan;
    private String file;

    public int[][] getPlan() {
        return plan;
    }

    public void setPlan(int[][] plan) {
        this.plan = plan;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);

        JLabel[] jLabels=new JLabel[200];

        for (int i=0;i<200;i++)
            jLabels[i]=new JLabel();

        jLabels[0].setBounds(0, 0, 50, 50);
        jLabels[0].setIcon(new javax.swing.ImageIcon(getClass().getResource("D:\\My books\\oop\\Project\\icons\\resistor.PNG"))); // NOI18N








    }



}


class showSchematic
{
    public static void main(String[] args)
    {
        netList net=new netList();
        Circuit circuit=net.readingFile("D:\\My books\\project-1.txt");
        showSchematic showSchematic=new showSchematic();
        showSchematic.show(circuit.node,circuit.branch);
    }
    public void show(Node[] nodes, ArrayList<Branch> branches)
    {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        schematic bold=new schematic();
        bold.setBranches(branches);
        bold.setNodes(nodes);
        bold.setBackground(Color.white);
        frame.add(bold);

        frame.setLocation(500,100);
        frame.setSize(600,500);
        frame.setVisible(true);
    }
}
