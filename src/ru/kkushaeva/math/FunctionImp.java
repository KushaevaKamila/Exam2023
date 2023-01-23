package ru.kkushaeva.math;

//класс для работы с функциями, заданными параметрически

public class FunctionImp implements Function{
    @Override
    public Object invoke(Object x) {
        double t = (double)x;
        return new Pair(Math.sin(4*t), Math.cos(t));
    }
}
