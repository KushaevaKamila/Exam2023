package ru.kkushaeva.math;

public class Pair {
    double x, y;

    public Pair(double x, double y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Pair obj){
        if (obj.getClass() != getClass()) return false;
        var other = (Pair)obj;
        return (x == other.x) && (y == other.y);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
