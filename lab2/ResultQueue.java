package org.example;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Queue;
public class ResultQueue {
    private Queue<Integer> results = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    public void add(int number) {
        lock.lock();
        try {
            results.add(number);
        } finally {
            lock.unlock();
        }
    }
    public int get() {
        lock.lock();
        try {
            return results.poll();
        } finally {
            lock.unlock();
        }
    }

}
