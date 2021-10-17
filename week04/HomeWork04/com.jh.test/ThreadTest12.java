import java.util.concurrent.Exchanger;
/*
Exchanger是最适合两个线程通信的工具。尤其是交替执行的两个线程。主线程和计算线程都通过exchange方法，同时被阻塞住，然后交换数据
 */
public class ThreadTest12 {
        private static final Exchanger<Integer> exchanger = new Exchanger<>();

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
            Integer result = exchanger.exchange(0);

            System.out.println("异步计算结果：" + result);
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
        }

        static class SumThread extends Thread {


            @Override
            public void run() {
                try {
                    int result = sum();
                    exchanger.exchange(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

}
