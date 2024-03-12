package org.example;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimeQueue {
    private final Queue<Integer> prime_queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();

    public void add(int number) {
        lock.lock();
        try {
            prime_queue.add(number);
            synchronized (this) {
                notify();
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized int get() throws InterruptedException {
        while (prime_queue.isEmpty()) {
            wait(1000);
        }
        int prime = -1;
        lock.lock();
        try {
            prime = prime_queue.poll();
        } finally {
            lock.unlock();
        }
        return prime;
    }
}
