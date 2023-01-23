package ru.kkushaeva.graphics;

import ru.kkushaeva.Converter;
import ru.kkushaeva.math.Function;

import java.awt.*;

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
