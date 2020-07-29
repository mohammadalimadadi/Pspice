package Graphic;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import Component.*;

import Circuit.Circuit;
import Node.*;
import netList.netList;

public class schematic extends JPanel{
    //fields
    private Node[] nodes;
    private ArrayList<Branch> branches;
    int[][] plan;
    private String file;
    protected int[][] countParallel=new int[30][30];
    final int parallel=25;

    final int height=10;

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

        paintBackGround(g2d);

        g2d.translate(40,500);

        g2d.setFont(new Font("Serif",Font.BOLD,10));

        for (int i=1;i<nodes.length&&nodes[i]!=null;i++) {
            for (int j = 0; j < nodes[i].getAttachments().size(); j++) {
                if (nodes[i].getAttachments().get(j).getNode1() == nodes[i]) {
                    if (nodes[i].getAttachments().get(j).getNode2().getnumber() == 0)
                    {
                        countParallel[i][0]++;
                        countParallel[0][i]++;
                        if (countParallel[i][0]==1) {
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6), 100 * ((i - 1) % 6), -100
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][0]==2)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6),
                                    100 * ((i - 1) % 6)+parallel,-100 * ((i - 1) / 6)));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6)-100,
                                    100 * ((i - 1) % 6)+parallel,-100 * ((i - 1) / 6)-100));
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)+parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        } else if (countParallel[i][0]==3)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6),
                                    100 * ((i - 1) % 6)-parallel,-100 * ((i - 1) / 6)));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6)-100,
                                    100 * ((i - 1) % 6)-parallel,-100 * ((i - 1) / 6)-100));
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6), 100 * ((i - 1) % 6)-parallel, -100
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                    }
                    else if(nodes[i].getAttachments().get(j).getNode2().getnumber() == i+1)
                    {
                        countParallel[i][i+1]++;
                        countParallel[i+1][i]++;
                        if(countParallel[i][i+1]==1) {
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i+1]==2)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100
                            ,100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100+parallel));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6)+100, -100 * ((i - 1) / 6) - 100
                                    ,100 * ((i - 1) % 6)+100,-100 * ((i - 1) / 6) - 100+parallel));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i+1]==3)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100
                                    ,100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100-parallel));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6)+100, -100 * ((i - 1) / 6) - 100
                                    ,100 * ((i - 1) % 6)+100,-100 * ((i - 1) / 6) - 100-parallel));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) + 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                    }
                    else if (nodes[i].getAttachments().get(j).getNode2().getnumber() == i-1)
                    {
                        countParallel[i][i-1]++;
                        countParallel[i-1][i]++;
                        if (countParallel[i][i-1]==1) {
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i-1]==2) {

                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100+parallel));

                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6)-100,-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6)-100,-100 * ((i - 1) / 6) - 100+parallel));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100+parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100+parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i-1]==3)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100-parallel));

                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6)-100,-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6)-100,-100 * ((i - 1) / 6) - 100-parallel));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100-parallel,
                                        100 * ((i - 1) % 6) - 100, -100 * ((i - 1) / 6) - 100-parallel
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                    }
                    else if (nodes[i].getAttachments().get(j).getNode2().getnumber() == i+6)
                    {
                        countParallel[i][i+6]++;
                        countParallel[i+6][i]++;
                        if (countParallel[i][i+6]==1) {
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i+6]==2)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6)+parallel,-100 * ((i - 1) / 6) - 100));

                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6)-200,
                                    100 * ((i - 1) % 6)+parallel,-100 * ((i - 1) / 6)-200));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)+parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                        else if (countParallel[i][i+6]==3)
                        {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6)-parallel,-100 * ((i - 1) / 6) - 100));

                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6),-100 * ((i - 1) / 6)-200,
                                    100 * ((i - 1) % 6)-parallel,-100 * ((i - 1) / 6)-200));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200,
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6)-parallel, -100 * ((i - 1) / 6) - 200
                                        , nodes[i].getAttachments().get(j).getName());
                            }

                        }
                    }
                    else if (nodes[i].getAttachments().get(j).getNode2().getnumber() == i-6)
                    {
                        countParallel[i][i - 6]++;
                        countParallel[i-6][i]++;
                        if (countParallel[i][i - 6] == 1) {
                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6),
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6), -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        } else if (countParallel[i][i - 6] == 2) {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6),
                                    100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6),
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) +parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        } else if (countParallel[i][i - 6] == 3) {
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6),
                                    100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)));
                            g2d.draw(new Line2D.Double(100 * ((i - 1) % 6), -100 * ((i - 1) / 6) - 100,
                                    100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100));

                            if (nodes[i].getAttachments().get(j) instanceof Capacitor) {
                                paintCapacitor(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Resistor) {
                                paintResistor(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Inductor) {
                                paintInductor(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof VoltageSource) {
                                paintVoltageSource(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof CurrentSource) {
                                paintCurrentSource(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof Diode) {
                                paintDiode(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6),
                                        nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof E) {
                                paintE(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof F) {
                                paintF(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof G) {
                                paintG(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            } else if (nodes[i].getAttachments().get(j) instanceof H) {
                                paintH(g2d, 100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6) - 100,
                                        100 * ((i - 1) % 6) -parallel, -100 * ((i - 1) / 6)
                                        , nodes[i].getAttachments().get(j).getName());
                            }
                        }
                    }
                }
            }
        }
        try {
            for (int i = 0; i < nodes[0].getAttachments().size(); i++) {
                if (nodes[0].getAttachments().get(i).getNode1().getnumber() == 0) {
                    for (int j = 1; j < 7; j++) {
                        if (nodes[0].getAttachments().get(i).getNode2().getnumber() == j) {
                            countParallel[0][j]++;
                            countParallel[j][0]++;
                            if (countParallel[0][j] == 1) {
                                if (nodes[0].getAttachments().get(i) instanceof Capacitor) {
                                    paintCapacitor(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Resistor) {
                                    paintResistor(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Inductor) {
                                    paintInductor(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof VoltageSource) {
                                    paintVoltageSource(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof CurrentSource) {
                                    paintCurrentSource(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Diode) {
                                    paintDiode(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6),
                                            nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof E) {
                                    paintE(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof F) {
                                    paintF(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof G) {
                                    paintG(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof H) {
                                    paintH(g2d, 100 * ((j - 1) % 6), -100, 100 * ((j - 1) % 6), -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                }
                            } else if (countParallel[0][j] == 2) {
                                g2d.draw(new Line2D.Double(100 * ((j - 1) % 6), -100,
                                        100 * ((j - 1) % 6) + parallel, -100));
                                g2d.draw(new Line2D.Double(100 * ((j - 1) % 6), -100 * ((j - 1) / 6),
                                        100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)));

                                if (nodes[0].getAttachments().get(i) instanceof Capacitor) {
                                    paintCapacitor(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Resistor) {
                                    paintResistor(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Inductor) {
                                    paintInductor(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof VoltageSource) {
                                    paintVoltageSource(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof CurrentSource) {
                                    paintCurrentSource(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Diode) {
                                    paintDiode(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6),
                                            nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof E) {
                                    paintE(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof F) {
                                    paintF(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof G) {
                                    paintG(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof H) {
                                    paintH(g2d, 100 * ((j - 1) % 6) + parallel, -100, 100 * ((j - 1) % 6) + parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                }

                            } else if (countParallel[0][j] == 3) {
                                g2d.draw(new Line2D.Double(100 * ((j - 1) % 6), -100,
                                        100 * ((j - 1) % 6) - parallel, -100));
                                g2d.draw(new Line2D.Double(100 * ((j - 1) % 6), -100 * ((j - 1) / 6),
                                        100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)));

                                if (nodes[0].getAttachments().get(i) instanceof Capacitor) {
                                    paintCapacitor(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Resistor) {
                                    paintResistor(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Inductor) {
                                    paintInductor(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof VoltageSource) {
                                    paintVoltageSource(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof CurrentSource) {
                                    paintCurrentSource(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof Diode) {
                                    paintDiode(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6),
                                            nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof E) {
                                    paintE(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof F) {
                                    paintF(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof G) {
                                    paintG(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                } else if (nodes[0].getAttachments().get(i) instanceof H) {
                                    paintH(g2d, 100 * ((j - 1) % 6) - parallel, -100, 100 * ((j - 1) % 6) - parallel, -100 * ((j - 1) / 6)
                                            , nodes[0].getAttachments().get(i).getName());
                                }
                            }
                        }
                    }


                }
            }
        } catch (Exception e) {

        }


        //Ground
        if(countParallel[0][1]>0&&countParallel[0][2]>0)
        {
            g2d.draw(new Line2D.Double(0,0,100,0));
        }
        if(countParallel[0][2]>0&&countParallel[0][3]>0)
        {
            g2d.draw(new Line2D.Double(100,0,200,0));
        }
        if(countParallel[0][3]>0&&countParallel[0][4]>0)
        {
            g2d.draw(new Line2D.Double(200,0,300,0));
        }
        if(countParallel[0][4]>0&&countParallel[0][5]>0)
        {
            g2d.draw(new Line2D.Double(300,0,400,0));
        }
        if(countParallel[0][5]>0&&countParallel[0][6]>0)
        {
            g2d.draw(new Line2D.Double(400,0,500,0));
        }
        g2d.setFont(new Font("Serif",Font.ITALIC+Font.BOLD,18));
        g2d.drawString("Ground",0,30);
    }

    public void paintBackGround(Graphics2D g2d) {
        g2d.setBackground(Color.GRAY);
        g2d.setColor(Color.WHITE);
        g2d.fillRect( 10, 10, 560, 540);

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(5.0f));
        g2d.draw(new RoundRectangle2D.Double(10, 10, 560, 538, 40, 40));

        g2d.setFont(new Font("serif",Font.PLAIN,14));
        g2d.drawString("All rights reserved",440,30);

    }

    public void paintDiode(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+8*m,y-height,x1+8*m,y+height));
                g2d.draw(new Line2D.Double(x1+16*m,y-height,x1+16*m,y+height));

                g2d.draw(new Line2D.Double(x1+8*m,y,x1+16*m,y+height));
                g2d.draw(new Line2D.Double(x1+8*m,y,x1+16*m,y-height));

            }
            else if (x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+8*m,y-height,x2+8*m,y+height));
                g2d.draw(new Line2D.Double(x2+16*m,y-height,x2+16*m,y+height));

                g2d.draw(new Line2D.Double(x2+8*m,y+height,x2+16*m,y));
                g2d.draw(new Line2D.Double(x2+8*m,y-height,x2+16*m,y));
            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x-height,y1+8*m,x+height,y1+8*m));
                g2d.draw(new Line2D.Double(x-height,y1+16*m,x+height,y1+16*m));

                g2d.draw(new Line2D.Double(x,y1+8*m,x+height,y1+16*m));
                g2d.draw(new Line2D.Double(x,y1+8*m,x-height,y1+16*m));
            }
            else if(y2<y1){
                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x+height,y2+8*m,x-height,y2+8*m));
                g2d.draw(new Line2D.Double(x+height,y2+16*m,x-height,y2+16*m));

                g2d.draw(new Line2D.Double(x,y2+16*m,x+height,y2+8*m));
                g2d.draw(new Line2D.Double(x,y2+16*m,x-height,y2+8*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintVoltageSource(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Arc2D.Double(x1+8*m,y-height,8*m,2*height,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+11*m,y));
                g2d.draw(new Line2D.Double(x1+13*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+10*m,y+height/2,x1+10*m,y-height/2));
            }
            else if (x1>x2)
            {
                g2d.draw(new Arc2D.Double(x2+8*m,y-height,8*m,2*height,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+13*m,y,x2+15*m,y));
                g2d.draw(new Line2D.Double(x2+9*m,y,x2+11*m,y));


                g2d.draw(new Line2D.Double(x2+14*m,y+height/2,x2+14*m,y-height/2));

            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Arc2D.Double(x-height,y1+8*m,2*height,8*m,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+11*m));
                g2d.draw(new Line2D.Double(x,y1+13*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x-height/2,y1+10*m,x+height/2,y1+10*m));
            }
            else if(y2<y1){
                g2d.draw(new Arc2D.Double(x-height,y2+8*m,2*height,8*m,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+11*m));
                g2d.draw(new Line2D.Double(x,y2+13*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x+height/2,y2+14*m,x-height/2,y2+14*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintCurrentSource(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Arc2D.Double(x1+8*m,y-height,8*m,2*height,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y-height/2));

            }
            else if (x1>x2)
            {
                g2d.draw(new Arc2D.Double(x2+8*m,y-height,8*m,2*height,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+9*m,y,x2+15*m,y));

                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y-height/2));
            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Arc2D.Double(x-height,y1+8*m,2*height,8*m,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x+height/2,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+9*m,x-height/2,y1+12*m));
            }
            else if(y2<y1){
                g2d.draw(new Arc2D.Double(x-height,y2+8*m,2*height,8*m,0,360,Arc2D.OPEN));

                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x,y2+15*m,x+height/2,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+15*m,x-height/2,y2+12*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintInductor(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if (x1<x2) {
                g2d.draw(new Line2D.Double(x1, y, 6 * m + x1, y));
                g2d.draw(new Line2D.Double(x2 - 6 * m, y, x2, y));

                g2d.draw(new Arc2D.Double(x1 + 6 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x1 + 10 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x1 + 14 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
            }
            if(x1>x2)
            {
                g2d.draw(new Line2D.Double(x2, y, 6 * m + x2, y));
                g2d.draw(new Line2D.Double(x1 - 6 * m, y, x1, y));

                g2d.draw(new Arc2D.Double(x2 + 6 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x2 + 10 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x2 + 14 * m, y - height, 4 * m, 2 * height, 180, -180, Arc2D.OPEN));
            }
        }

        if(x1==x2)
        {
            int x=x1;
            if(y1<y2) {
                g2d.draw(new Line2D.Double(x, y1, x, y1 + 6 * m));
                g2d.draw(new Line2D.Double(x, y2 - 6 * m, x, y2));

                g2d.draw(new Arc2D.Double(x - height, y1 + 6 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x - height, y1 + 10 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x - height, y1 + 14 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));
            }
            if(y1>y2)
            {
                g2d.draw(new Line2D.Double(x, y2, x, y2 + 6 * m));
                g2d.draw(new Line2D.Double(x, y1 - 6 * m, x, y1));

                g2d.draw(new Arc2D.Double(x - height, y2 + 6 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x - height, y2 + 10 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));
                g2d.draw(new Arc2D.Double(x - height, y2 + 14 * m, 2 * height, 4 * m, 90, -180, Arc2D.OPEN));

            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintCapacitor(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2){
            int y=y1;

            if(x1<x2) {
                g2d.draw(new Line2D.Double(x1, y, 10 * m + x1, y));
                g2d.draw(new Line2D.Double(x2 - 10 * m, y, x2, y));

                g2d.draw(new Line2D.Double(x1 + 10 * m, y + height, x1 + 10 * m, y - height));
                g2d.draw(new Line2D.Double(x2 - 10 * m, y + height, x2 - 10 * m, y - height));
            }
            if(x1>x2)
            {
                g2d.draw(new Line2D.Double(x2, y, 10 * m + x2, y));
                g2d.draw(new Line2D.Double(x1 - 10 * m, y, x1, y));

                g2d.draw(new Line2D.Double(x2 + 10 * m, y + height, x2 + 10 * m, y - height));
                g2d.draw(new Line2D.Double(x1 - 10 * m, y + height, x1 - 10 * m, y - height));
            }
        }

        if(x1==x2){
            int x=x1;

            if(y1<y2) {
                g2d.draw(new Line2D.Double(x, y1, x, y1 + 10 * m));
                g2d.draw(new Line2D.Double(x, y2 - 10 * m, x, y2));

                g2d.draw(new Line2D.Double(x - height, y1 + 10 * m, x + height, y1 + 10 * m));
                g2d.draw(new Line2D.Double(x - height, y2 - 10 * m, x + height, y2 - 10 * m));
            }
            if(y1>y2)
            {
                g2d.draw(new Line2D.Double(x, y2, x, y2 + 10 * m));
                g2d.draw(new Line2D.Double(x, y1 - 10 * m, x, y1));

                g2d.draw(new Line2D.Double(x - height, y2 + 10 * m, x + height, y2 + 10 * m));
                g2d.draw(new Line2D.Double(x - height, y1 - 10 * m, x + height, y1 - 10 * m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintResistor(Graphics2D g2d,int x1,int y1,int x2,int y2,String S) {

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2) {
                g2d.draw(new Line2D.Double(x1,y,6*m+x1,y));
                g2d.draw(new Line2D.Double(x2-6*m,y,x2,y));
                g2d.draw(new Line2D.Double(x1+6*m,y,x1+7*m,y+height));
                g2d.draw(new Line2D.Double(x1+7*m,y+height,x1+9*m,y-height));
                g2d.draw(new Line2D.Double(x1+9*m,y-height,x1+11*m,y+height));
                g2d.draw(new Line2D.Double(x1+11*m,y+height,x1+13*m,y-height));
                g2d.draw(new Line2D.Double(x1+13*m,y-height,x1+15*m,y+height));
                g2d.draw(new Line2D.Double(x1+15*m,y+height,x1+17*m,y-height));
                g2d.draw(new Line2D.Double(x1+17*m,y-height,x1+18*m,y));
            }

            if(x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,6*m+x2,y));
                g2d.draw(new Line2D.Double(x1-6*m,y,x1,y));
                g2d.draw(new Line2D.Double(x2+6*m,y,x2+7*m,y+height));
                g2d.draw(new Line2D.Double(x2+7*m,y+height,x2+9*m,y-height));
                g2d.draw(new Line2D.Double(x2+9*m,y-height,x2+11*m,y+height));
                g2d.draw(new Line2D.Double(x2+11*m,y+height,x2+13*m,y-height));
                g2d.draw(new Line2D.Double(x2+13*m,y-height,x2+15*m,y+height));
                g2d.draw(new Line2D.Double(x2+15*m,y+height,x2+17*m,y-height));
                g2d.draw(new Line2D.Double(x2+17*m,y-height,x2+18*m,y));
            }
        }

        if(x1==x2)
        {
            int x=x1;
            if(y1<y2) {
                g2d.draw(new Line2D.Double(x, y1, x, y1 + 6 * m));
                g2d.draw(new Line2D.Double(x, y2 - 6 * m, x, y2));

                g2d.draw(new Line2D.Double(x, y1 + 6 * m, x + height, y1 + 7 * m));
                g2d.draw(new Line2D.Double(x + height, y1 + 7 * m, x - height, y1 + 9 * m));
                g2d.draw(new Line2D.Double(x - height, y1 + 9 * m, x + height, y1 + 11 * m));
                g2d.draw(new Line2D.Double(x + height, y1 + 11 * m, x - height, y1 + 13 * m));
                g2d.draw(new Line2D.Double(x - height, y1 + 13 * m, x + height, y1 + 15 * m));
                g2d.draw(new Line2D.Double(x + height, y1 + 15 * m, x - height, y1 + 17 * m));
                g2d.draw(new Line2D.Double(x - height, y1 + 17 * m, x, y1 + 18 * m));
            }
            if(y1>y2)
            {
                g2d.draw(new Line2D.Double(x, y2, x, y2 + 6 * m));
                g2d.draw(new Line2D.Double(x, y1 - 6 * m, x, y1));

                g2d.draw(new Line2D.Double(x, y2 + 6 * m, x + height, y2 + 7 * m));
                g2d.draw(new Line2D.Double(x + height, y2 + 7 * m, x - height, y2 + 9 * m));
                g2d.draw(new Line2D.Double(x - height, y2 + 9 * m, x + height, y2 + 11 * m));
                g2d.draw(new Line2D.Double(x + height, y2 + 11 * m, x - height, y2 + 13 * m));
                g2d.draw(new Line2D.Double(x - height, y2 + 13 * m, x + height, y2 + 15 * m));
                g2d.draw(new Line2D.Double(x + height, y2 + 15 * m, x - height, y2 + 17 * m));
                g2d.draw(new Line2D.Double(x - height, y2 + 17 * m, x, y2 + 18 * m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintE(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y+height));
                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y-height));
                g2d.draw(new Line2D.Double(x1+12*m,y+height,16*m+x1,y));
                g2d.draw(new Line2D.Double(x1+12*m,y-height,16*m+x1,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+11*m,y));
                g2d.draw(new Line2D.Double(x1+13*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+10*m,y+height/2,x1+10*m,y-height/2));
            }
            else if (x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y+height));
                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y-height));
                g2d.draw(new Line2D.Double(x2+12*m,y+height,16*m+x2,y));
                g2d.draw(new Line2D.Double(x2+12*m,y-height,16*m+x2,y));

                g2d.draw(new Line2D.Double(x2+13*m,y,x2+15*m,y));
                g2d.draw(new Line2D.Double(x2+9*m,y,x2+11*m,y));


                g2d.draw(new Line2D.Double(x2+14*m,y+height/2,x2+14*m,y-height/2));

            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+8*m,x+height,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+8*m,x-height,y1+12*m));
                g2d.draw(new Line2D.Double(x+height,y1+12*m,x,y1+16*m));
                g2d.draw(new Line2D.Double(x-height,y1+12*m,x,y1+16*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+11*m));
                g2d.draw(new Line2D.Double(x,y1+13*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x-height/2,y1+10*m,x+height/2,y1+10*m));
            }
            else if(y2<y1){
                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+8*m,x+height,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+8*m,x-height,y2+12*m));
                g2d.draw(new Line2D.Double(x+height,y2+12*m,x,y2+16*m));
                g2d.draw(new Line2D.Double(x-height,y2+12*m,x,y2+16*m));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+11*m));
                g2d.draw(new Line2D.Double(x,y2+13*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x+height/2,y2+14*m,x-height/2,y2+14*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintH(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){

        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y+height));
                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y-height));
                g2d.draw(new Line2D.Double(x1+12*m,y+height,16*m+x1,y));
                g2d.draw(new Line2D.Double(x1+12*m,y-height,16*m+x1,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+11*m,y));
                g2d.draw(new Line2D.Double(x1+13*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+10*m,y+height/2,x1+10*m,y-height/2));
            }
            else if (x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y+height));
                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y-height));
                g2d.draw(new Line2D.Double(x2+12*m,y+height,16*m+x2,y));
                g2d.draw(new Line2D.Double(x2+12*m,y-height,16*m+x2,y));

                g2d.draw(new Line2D.Double(x2+13*m,y,x2+15*m,y));
                g2d.draw(new Line2D.Double(x2+9*m,y,x2+11*m,y));


                g2d.draw(new Line2D.Double(x2+14*m,y+height/2,x2+14*m,y-height/2));

            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+8*m,x+height,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+8*m,x-height,y1+12*m));
                g2d.draw(new Line2D.Double(x+height,y1+12*m,x,y1+16*m));
                g2d.draw(new Line2D.Double(x-height,y1+12*m,x,y1+16*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+11*m));
                g2d.draw(new Line2D.Double(x,y1+13*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x-height/2,y1+10*m,x+height/2,y1+10*m));
            }
            else if(y2<y1){
                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+8*m,x+height,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+8*m,x-height,y2+12*m));
                g2d.draw(new Line2D.Double(x+height,y2+12*m,x,y2+16*m));
                g2d.draw(new Line2D.Double(x-height,y2+12*m,x,y2+16*m));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+11*m));
                g2d.draw(new Line2D.Double(x,y2+13*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x+height/2,y2+14*m,x-height/2,y2+14*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintF(Graphics2D g2d,int x1,int y1,int x2,int y2,String S){
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y+height));
                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y-height));
                g2d.draw(new Line2D.Double(x1+12*m,y+height,16*m+x1,y));
                g2d.draw(new Line2D.Double(x1+12*m,y-height,16*m+x1,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y-height/2));

            }
            else if (x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y+height));
                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y-height));
                g2d.draw(new Line2D.Double(x2+12*m,y+height,16*m+x2,y));
                g2d.draw(new Line2D.Double(x2+12*m,y-height,16*m+x2,y));

                g2d.draw(new Line2D.Double(x2+9*m,y,x2+15*m,y));

                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y-height/2));
            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+8*m,x+height,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+8*m,x-height,y1+12*m));
                g2d.draw(new Line2D.Double(x+height,y1+12*m,x,y1+16*m));
                g2d.draw(new Line2D.Double(x-height,y1+12*m,x,y1+16*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x+height/2,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+9*m,x-height/2,y1+12*m));
            }
            else if(y2<y1){
                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+8*m,x+height,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+8*m,x-height,y2+12*m));
                g2d.draw(new Line2D.Double(x+height,y2+12*m,x,y2+16*m));
                g2d.draw(new Line2D.Double(x-height,y2+12*m,x,y2+16*m));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x,y2+15*m,x+height/2,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+15*m,x-height/2,y2+12*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }

    public void paintG(Graphics2D g2d,int x1,int y1,int x2,int y2 ,String S){
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(1.5f));
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double m=r*1.0/24;

        if(y1==y2)
        {   int y=y1;

            if(x1<x2)
            {
                g2d.draw(new Line2D.Double(x1,y,8*m+x1,y));
                g2d.draw(new Line2D.Double(x2-8*m,y,x2,y));

                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y+height));
                g2d.draw(new Line2D.Double(x1+8*m,y,12*m+x1,y-height));
                g2d.draw(new Line2D.Double(x1+12*m,y+height,16*m+x1,y));
                g2d.draw(new Line2D.Double(x1+12*m,y-height,16*m+x1,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+15*m,y));

                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x1+9*m,y,x1+12*m,y-height/2));

            }
            else if (x1>x2)
            {
                g2d.draw(new Line2D.Double(x2,y,8*m+x2,y));
                g2d.draw(new Line2D.Double(x1-8*m,y,x1,y));

                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y+height));
                g2d.draw(new Line2D.Double(x2+8*m,y,12*m+x2,y-height));
                g2d.draw(new Line2D.Double(x2+12*m,y+height,16*m+x2,y));
                g2d.draw(new Line2D.Double(x2+12*m,y-height,16*m+x2,y));

                g2d.draw(new Line2D.Double(x2+9*m,y,x2+15*m,y));

                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y+height/2));
                g2d.draw(new Line2D.Double(x2+15*m,y,x2+12*m,y-height/2));
            }
        }

        if(x1==x2)
        {
            int x=x1;

            if(y2>y1){
                g2d.draw(new Line2D.Double(x,y1,x,y1+8*m));
                g2d.draw(new Line2D.Double(x,y2-8*m,x,y2));

                g2d.draw(new Line2D.Double(x,y1+8*m,x+height,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+8*m,x-height,y1+12*m));
                g2d.draw(new Line2D.Double(x+height,y1+12*m,x,y1+16*m));
                g2d.draw(new Line2D.Double(x-height,y1+12*m,x,y1+16*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x,y1+15*m));

                g2d.draw(new Line2D.Double(x,y1+9*m,x+height/2,y1+12*m));
                g2d.draw(new Line2D.Double(x,y1+9*m,x-height/2,y1+12*m));
            }
            else if(y2<y1){
                g2d.draw(new Line2D.Double(x,y2,x,y2+8*m));
                g2d.draw(new Line2D.Double(x,y1-8*m,x,y1));

                g2d.draw(new Line2D.Double(x,y2+8*m,x+height,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+8*m,x-height,y2+12*m));
                g2d.draw(new Line2D.Double(x+height,y2+12*m,x,y2+16*m));
                g2d.draw(new Line2D.Double(x-height,y2+12*m,x,y2+16*m));

                g2d.draw(new Line2D.Double(x,y2+9*m,x,y2+15*m));

                g2d.draw(new Line2D.Double(x,y2+15*m,x+height/2,y2+12*m));
                g2d.draw(new Line2D.Double(x,y2+15*m,x-height/2,y2+12*m));
            }
        }
        g2d.drawString(S,(x1+x2)/2+height*3/2,(y1+y2)/2+height*3/2);
    }
}


class showSchematic
{
    public static void main(String[] args)
    {

    }

    public void show(Node[] nodes, ArrayList<Branch> branches)
    {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //frame.add(bold);

        frame.setLocation(500,100);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}
