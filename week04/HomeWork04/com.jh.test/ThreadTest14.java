import java.util.concurrent.SynchronousQueue;
/*
SynchronousQueue是一个特殊的Queue,只能允许1个长度，本质上与BlockingQueue的原理一致
 */
public class ThreadTest14 {
        private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();

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
            Integer result = queue.take();

            System.out.println("异步计算结果：" + result);
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
        }

        static class SumThread extends Thread {


            @Override
            public void run() {
                try {
                    int result = sum();
                    queue.add(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

}
