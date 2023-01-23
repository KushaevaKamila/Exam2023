package ru.kkushaeva.math;

//класс для работы с функциями, заданными явно

import static java.lang.Double.NaN;

public class FunctionExp implements Function{
    @Override
    public Object invoke(Object x) {
        if (Math.abs((double)x) > 1e-7) return (double)x+1./Math.pow((double)x, 3);
        else return NaN;
    }
}
