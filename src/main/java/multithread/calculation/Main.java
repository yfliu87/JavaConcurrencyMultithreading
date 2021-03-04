package multithread.calculation;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(
            new ComplexCalculation().calculateResult(
                BigInteger.valueOf(12), BigInteger.valueOf(23),
                BigInteger.valueOf(34), BigInteger.valueOf(45))
        );
    }
}
