package ru.kkushaeva.graphics;

import ru.kkushaeva.Converter;
import ru.kkushaeva.math.Function;
import ru.kkushaeva.math.Pair;

import java.awt.*;

import static java.lang.Double.isNaN;

public class FunctionPainterImp implements Painter{
    private Converter cnv;
    private Function f;
    private Color color;
    private boolean check;
    private double tMin;
    private double tMax;
    public FunctionPainterImp(Converter cnv, Function f, Color color, boolean check, double tMin, double tMax){
        this.cnv = cnv;
        this.f = f;
        this.color = color;
        this.check = check;
        this.tMin = tMin;
        this.tMax = tMax;
    }
    @Override
    public void paint(Graphics g, int width, int height) {
        double dt = 0.0001;
        if (check) {
            g.setColor(color);
            for (double t = tMin; t < tMax; t += dt) {
                Pair a = (Pair)f.invoke(t);
                double xCrt1 = a.getX();
                double yCrt1 = a.getY();
                Pair b = (Pair)f.invoke(t+dt);
                double xCrt2 = b.getX();
                double yCrt2 = b.getY();
                if (!isNaN(xCrt1) && !isNaN(yCrt1)) g.drawLine(cnv.xCrt2Scr(xCrt1), cnv.yCrt2Scr(yCrt1), cnv.xCrt2Scr(xCrt2), cnv.yCrt2Scr(yCrt2));
            }
        }
    }
    public void setF(Function f){
        this.f = f;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void setTMin(double tMin){
        this.tMin = tMin;
    }
    public void setTMax(double tMax){
        this.tMax = tMax;
    }
}
