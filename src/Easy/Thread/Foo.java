package Easy.Thread;

import java.util.concurrent.CountDownLatch;

public class Foo {

    private CountDownLatch count2;
    private CountDownLatch count3;
    public Foo() {
        count2=new CountDownLatch(1);
        count3=new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        count2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        count2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        count3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        count3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}
