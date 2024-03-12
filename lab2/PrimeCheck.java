package org.example;

public class PrimeCheck extends Thread{
    private final PrimeQueue primeQueue;
    private final ResultQueue resultQueue;

    public PrimeCheck(PrimeQueue primeQueue, ResultQueue resultQueue) {
        this.primeQueue = primeQueue;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int number = primeQueue.get();
                Prime prime = new Prime(number);
                if (prime.isPrime()) {
                    resultQueue.add(number);
                    System.out.println("PrimeCheck: " + number);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}
