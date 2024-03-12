package org.example;

public class Prime {
    private final long number;

    public Prime(long number) {
        this.number = number;
    }

    public long getNumber() {
        return number;
    }

    public boolean isPrime() {
        if (number <= 1) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
