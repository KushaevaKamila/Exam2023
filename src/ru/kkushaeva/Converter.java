package ru.kkushaeva;

public class Converter {

    public double xMin, xMax, yMin, yMax;
    int width, height;

    public Converter(double xMin, double xMax,
                     double yMin, double yMax,
                     int width, int height){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.width = width;
        this.height = height;
        /*this.width = width - 1;
        this.height = height - 1;*/
    }

    public double getxDen(){
        return width / (xMax - xMin);
    }

    public double getyDen(){
        return height / (yMax - yMin);
    }

    public int xCrt2Scr(double x){
        var v = (int)(getxDen() * (x - xMin));
        if (v < -width) v = -width;
        if (v > 2 * width) v = 2 * width;
        return v;
    }

    public int yCrt2Scr(double y){
        var v = (int)(getyDen() * (yMax - y));
        if (v < -height) v = -height;
        if (v > 2 * height) v = 2 * height;
        return v;
    }

    public double xScr2Crt(int x){
        return x / getxDen() + xMin;
    }

    public double yScr2Crt(int y){
        return yMax - y / getyDen();
    }

    public double xDistScr2Crt(int x1, int x2){
        return (Math.abs(x1 - x2)) / getxDen();
    }

    public int xDistCrt2Scr(double x1, double x2){
        return (int)(getxDen()*(Math.abs(x1 - x2)));
    }
    public int yDistCrt2Scr(double y1, double y2) {return (int)(getyDen()*(Math.abs(y1 - y2)));}

    public void setXEdges(Double xMin, Double xMax) {
        this.xMin = xMin;
        this.xMax = xMax;
    }

    public void setYEdges(Double yMin, Double yMax) {
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
