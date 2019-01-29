package com.roman.mysan.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreWithCondVariables {

    private ReentrantLock mLock;
    private Condition mCondition;
    private volatile int resources;

    public SemaphoreWithCondVariables(int resources) {
        this.resources = resources;
        this.mLock = new ReentrantLock();
        this.mCondition = mLock.newCondition();
    }

    public void acquire() {
        mLock.lock();
        resources--;
        while (resources == 0) {
            try {
                mCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mLock.unlock();
            }
        }
    }
}
