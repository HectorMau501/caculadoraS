
package Clases;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.text.DecimalFormat;
import javax.swing.JTextArea;

import java.util.concurrent.Callable;

public class ExecutorTask implements Callable<Float> {
    private float[] numbers;

    public ExecutorTask(float[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Float call() throws Exception {
        float sum = 0;
        for (float number : numbers) {
            sum += number;
        }
        return sum;
    }
}

