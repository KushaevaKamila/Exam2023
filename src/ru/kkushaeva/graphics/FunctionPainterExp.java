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

    }

    public void setColor(Color color) {
        this.color = color;
    }
}
