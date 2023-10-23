
package Clases;

import java.util.concurrent.Callable;


class SumTask implements Callable<Float> {
    private float[] subarray;

        public SumTask(float[] subarray) {
            this.subarray = subarray;
        }

        @Override
        public Float call() {
            float sum = 0;
            for (float num : subarray) {
                sum += num;
            }
            return sum;
        }
}

