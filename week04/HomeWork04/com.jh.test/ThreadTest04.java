/*
利用object对象的wait+notify/notifyAll方法来实现。在启动完计算线程之后将主线程wait,之后在计算线程中，执行完毕之后，调用notify/notifyAll方法来唤醒主线程继续执行
 */
public class ThreadTest04 {
        private static final Object lock = new Object();

        private static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if (a < 2) {
                return 1;
            }
            return fibo(a - 1) + fibo(a - 2);
        }

        public static void main(String[] args) throws Exception {
            long start = System.currentTimeMillis();
            SumThread sumThread = new SumThread();

            sumThread.start();
            synchronized (lock) {
                lock.wait();
            }

            int result = sumThread.getResult();
            System.out.println("异步计算结果：" + result);
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
        }

        static class SumThread extends Thread {

            private Integer result;

            public Integer getResult() {
                return result;
            }

            @Override
            public void run() {
                synchronized (lock) {
                    result = sum();
                    lock.notifyAll();
                }
            }
        }

}
