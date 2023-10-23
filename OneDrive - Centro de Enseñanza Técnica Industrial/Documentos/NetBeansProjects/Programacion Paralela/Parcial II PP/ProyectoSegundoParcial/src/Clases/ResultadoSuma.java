
package Clases;


public class ResultadoSuma {
    private float suma;
    private long tiempoTotal;
    
    public ResultadoSuma(float suma, long tiempoTotal){
        this.suma = suma;
        this.tiempoTotal = tiempoTotal;
    }
    
    public float getSuma() {
        return suma;
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }
           
}
