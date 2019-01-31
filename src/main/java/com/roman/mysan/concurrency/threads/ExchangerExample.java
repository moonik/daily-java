package com.roman.mysan.concurrency.threads;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Mike(exchanger).start();
        new Questionary(exchanger).start();
    }

    static class Mike extends Thread {
        private Exchanger<String> exchanger;

        public Mike(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                sleep(1000);
                String question = exchanger.exchange("Hi, my name is Mike.");
                System.out.println(question);
                question = exchanger.exchange("I am 20 years old.");
                System.out.println(question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Questionary extends Thread {
        private Exchanger<String> exchanger;

        public Questionary(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                String answer = exchanger.exchange("What is your name ?");
                sleep(1000);
                System.out.println(answer);
                answer = exchanger.exchange("How old are you?");
                sleep(1000);
                System.out.println(answer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
