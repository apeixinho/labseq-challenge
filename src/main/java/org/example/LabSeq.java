package org.example;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class LabSeq {

    static Map<Integer, BigInteger> memoizationMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        int n = 100;
        BigInteger result;

        memoizationMap.put(0, BigInteger.ZERO);
        memoizationMap.put(1, BigInteger.ONE);
        memoizationMap.put(2, BigInteger.ZERO);
        memoizationMap.put(3, BigInteger.ONE);

        long startTime;
        long estimatedTime;

        // Memoization
        startTime = System.nanoTime();
        result = labSeqMemoization(n);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("Memoization result (n=" + n + ") = " + result + " took "
                + TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) + " ms");

        // Recursive
        startTime = System.nanoTime();
        result = labSeqRecursive(n);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("Recursive result(n=" + n + ") = " + result + " took "
                + TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) + " ms");

    }

    static BigInteger labSeqMemoization(int n) {
        for (int index = 4; index <= n; index++) {
            int indexFinal = index;
            memoizationMap.computeIfAbsent(index,
                    v -> memoizationMap.get(indexFinal - 4).add(memoizationMap.get(indexFinal - 3)));
        }
        return memoizationMap.get(n);
    }

    static BigInteger labSeqRecursive(int n) {

        return switch (n) {
            case 0, 2 -> BigInteger.ZERO;
            case 1, 3 -> BigInteger.ONE;
            default -> labSeqRecursive(n - 4).add(labSeqRecursive(n - 3));
        };
    }

}
