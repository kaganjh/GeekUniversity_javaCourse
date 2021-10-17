import java.util.concurrent.TimeUnit;

/*
两个线程共享一个volatile的变量，计算线程在计算完成之后，更新这个volatile变量的状态为ture，那么main线程只需要在计算线程启动之后，不断轮询监控该变量的状态
 */
public class ThreadTest02 {

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

            while (!sumThread.isSuccess()) {
                TimeUnit.MILLISECONDS.sleep(1);
            }

            int result = sumThread.getResult();
            System.out.println("异步计算结果：" + result);
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
        }

        static class SumThread extends Thread {

            private Integer result;
            private volatile boolean success = false;

            public boolean isSuccess() {
                return success;
            }

            public Integer getResult() {
                return result;
            }

            @Override
            public void run() {
                result = sum();
                success = true;
            }
        }

}
