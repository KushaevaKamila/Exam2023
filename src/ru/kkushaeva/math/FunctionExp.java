package ru.kkushaeva.math;

//класс для работы с функциями, заданными явно

public class FunctionExp implements Function{
    @Override
    public Object invoke(Object x) {
        return (double)x+1/Math.pow((double)x, 3);
    }
}
