package multithread.calculation;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1,
                                      BigInteger base2, BigInteger power2) throws InterruptedException {
        PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        BigInteger result = t1.getResult().add(t2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0; i = i.add(BigInteger.ONE)) {
                this.result = this.result.multiply(this.base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}
