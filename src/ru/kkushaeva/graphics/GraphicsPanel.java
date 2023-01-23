package ru.kkushaeva.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GraphicsPanel extends JPanel {
    private ArrayList<Painter> p = new ArrayList<>();

    public GraphicsPanel(Painter p){
        this.p.add(p);
    }

    public GraphicsPanel(Collection<Painter> pts){
        p.addAll(pts);
    }

    public void addPainter(Painter p){
        if (!this.p.contains(p)) this.p.add(p);
        repaint();
    }

    public void addPainterToTheEnd(Painter p){
        this.p.add(p);
        repaint();
    }

    public void removePainter(Painter p){
        this.p.remove(p);
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g); //super - обращение к методу базового класса
        for (var ptr: this.p){
            ptr.paint(g, getWidth(), getHeight());
        }
    }
}
