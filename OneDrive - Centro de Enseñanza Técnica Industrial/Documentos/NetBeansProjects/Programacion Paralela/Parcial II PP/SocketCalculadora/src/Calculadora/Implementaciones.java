
package Calculadora;


public class Implementaciones implements Operacion {

    @Override
    public double suma(double a, double b) {
        return a + b;  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double resta(double c, double d) {
        return c - d;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double div(double e, double f) {
        return e / f;
    }

    @Override
    public double multiplicacion(double g, double h) {
        return g * h;
    }

    @Override
    public double modulo(double i, double j) {
        return i % j;
    }

    @Override
    public double raiz(double e, double f) {
        return Math.pow(e, 1.0 / f);
    }

    @Override
    public double pow(double k, double l) {
        return Math.pow(k, l);
    }

}

