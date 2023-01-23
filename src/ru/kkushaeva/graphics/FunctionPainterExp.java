package ru.kkushaeva.graphics;

import ru.kkushaeva.Converter;
import ru.kkushaeva.math.Function;

import java.awt.*;

public class FunctionPainterExp implements Painter{
    private Converter cnv;
    private Function f;
    private Color color;
    private boolean check;
    public FunctionPainterExp(Converter cnv, Function f, Color color, boolean check) {
        this.cnv = cnv;
        this.f = f;
        this.color = color;
        this.check = check;
    }

    @Override
    public void paint(Graphics g, int width, int height) {
        if (check){
            g.setColor(color);
            for (int xScr = 0; xScr < width - 1; xScr++){
                double xCrt1 = cnv.xScr2Crt(xScr);
                double yCrt1 = (double) f.invoke(xCrt1);
                double xCrt2 = cnv.xScr2Crt(xScr + 1);
                double yCrt2 = (double) f.invoke(xCrt2);
                g.drawLine(cnv.xCrt2Scr(xCrt1), cnv.yCrt2Scr(yCrt1), cnv.xCrt2Scr(xCrt2), cnv.yCrt2Scr(yCrt2));
            }
        }
    }

    public void setF(Function f){
        this.f = f;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
