
package Clases;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

public class ExecutorTask {
    private float[] numeros;
    private ExecutorService executorService;

    public ExecutorTask(float[] numeros, ExecutorService executorService) {
        this.numeros = numeros;
        this.executorService = executorService;
    }

    public Future<Float> calcularSumaEnParalelo() {
        return executorService.submit(new Callable<Float>() {
            @Override
            public Float call() {
                float suma = 0;
                for (float numero : numeros) {
                    suma += numero;
                }
                return suma;
            }
        });
    }
}

