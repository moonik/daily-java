package com.roman.mysan.concurrency.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionsExample {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static int account = 0;

    public static void main(String[] args) {
        new AccountWithdraw().start();
        new AccountDeposit().start();
    }

    static class AccountDeposit extends Thread {
        @Override
        public void run() {
            lock.lock();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account += 10;
            System.out.println("Added 10$. Your balance: " + account);
            condition.signal(); // notifies that cash was added, and you can withdraw
            lock.unlock();
        }
    }

    static class AccountWithdraw extends Thread {
        @Override
        public void run() {
            lock.lock();
            if (account - 10 < 0) {
                System.out.println("You can't withdraw. Your balance: " + account);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            account -= 10;
            System.out.println("Withdrawn 10$. Your balance: " + account);
            lock.unlock();
        }
    }
}
