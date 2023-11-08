
package Servidor;


public class ServerState {
    private double acumulado = 0;

    public double getAcumulado() {
        return acumulado;
    }

    public void agregarNumeroOperador(double numero, String operador) {
        // Realiza la operación correspondiente y actualiza el acumulado
        switch (operador) {
            case "+":
                acumulado += numero;
                break;
            case "-":
                acumulado -= numero;
                break;
            case "*":
                acumulado *= numero;
                break;
            case "/":
                if (numero != 0) {
                    acumulado /= numero;
                } else {
                    // Manejar la división por cero (puedes lanzar una excepción o devolver un mensaje de error)
                }
                break;
            case "%":
                acumulado %= numero;
                break;
            default:
                // Operador no válido (puedes lanzar una excepción o devolver un mensaje de error)
        }
    }

    public void reiniciarAcumulado() {
        acumulado = 0;
    }

    // Puedes agregar más métodos según sea necesario, por ejemplo, para lidiar con raíces, potencias, etc.
}
