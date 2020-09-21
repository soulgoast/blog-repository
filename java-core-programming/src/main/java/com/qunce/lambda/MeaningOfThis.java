package com.qunce.lambda;

public class MeaningOfThis {

    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable runnable = new Runnable() {

            public final int value = 4;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        runnable.run();
    }

    public static void main(String[] args) {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doIt();
    }
}
