package ru.kkushaeva.graphics;

import ru.kkushaeva.Converter;

import java.awt.*;

public class CrtPainter implements Painter {
    int x, y;
    private Converter cnv;

    public CrtPainter(Converter cnv){
        this.cnv = cnv;
    }

    @Override
    public void paint(Graphics g, int width, int height) {
        g.setColor(Color.BLACK);

        x = cnv.xCrt2Scr(0);
        y = cnv.yCrt2Scr(0);

        g.drawLine(0, y, width, y);        g.drawLine(x, 0, x, height);

        int th = 2;

        if (cnv.yMax * cnv.yMin < 0)  {
            //рисуем штрихи на оси абсцисс
            for (double i = cnv.xMin; i < cnv.xMax; i = i + 0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if (Math.round(10 * i) % 10 == 0) {
                    th += 4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i) - th, y + 3 * th);
                }
                else if (Math.round(10 * i) % 5 == 0) th += 2;
                g.drawLine(cnv.xCrt2Scr(i), y - th, cnv.xCrt2Scr(i), y + th);
                th = 2;
            }
        }
        else {
            //рисуем штрихи сверху и снизу
            for (double i = cnv.xMin; i < cnv.xMax; i = i + 0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if (Math.round(10 * i) % 10 == 0) {
                    th += 4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i) - th, 3 * th);
                    g.drawString(Double.toString(Math.round(i)), cnv.xCrt2Scr(i) - th, height - 3 * th);
                }
                else if (Math.round(10 * i) % 5 == 0) th += 2;
                g.drawLine(cnv.xCrt2Scr(i), 0, cnv.xCrt2Scr(i), th);
                g.drawLine(cnv.xCrt2Scr(i), height - th - 1, cnv.xCrt2Scr(i), height);
                th = 2;
            }
        }

        if (cnv.xMax * cnv.xMin < 0) {
            //рисуем штрихи на оси ординат
            for (double i = cnv.yMin; i < cnv.yMax; i = i + 0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if (Math.round(10 * i) % 10 == 0) {
                    th += 4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), x - 5 * th, cnv.yCrt2Scr(i) + th);
                }
                else if (Math.round(10 * i) % 5 == 0) th += 2;
                g.drawLine(x - th, cnv.yCrt2Scr(i), x + th, cnv.yCrt2Scr(i));
                th = 2;
            }
        }
        else {
            //рисуем штрихи  по бокам
            for (double i = cnv.yMin; i < cnv.yMax; i = i + 0.1){
                if (Math.abs(i) < 1.e-8) { continue; }
                if (Math.round(10 * i) % 10 == 0) {
                    th += 4;
                    //вывести координату
                    g.drawString(Double.toString(Math.round(i)), th, cnv.yCrt2Scr(i) + th);
                    g.drawString(Double.toString(Math.round(i)),width - 4 * th, cnv.yCrt2Scr(i) + th);
                }
                else if (Math.round(10 * i) % 5 == 0) th += 2;
                g.drawLine(0, cnv.yCrt2Scr(i), th, cnv.yCrt2Scr(i));
                g.drawLine(width - th - 1, cnv.yCrt2Scr(i), width, cnv.yCrt2Scr(i));
                th = 2;
            }
        }

        g.drawString(
                "0.0",
                x + 22 * (int)(Math.signum(cnv.xMin)) * (int)(Math.signum(cnv.xMax)),
                y - 17 * (int)(Math.signum(cnv.yMin)) * (int)(Math.signum(cnv.yMax))
        );
    }
}
