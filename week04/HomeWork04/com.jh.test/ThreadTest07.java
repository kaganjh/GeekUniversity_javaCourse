import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*结合Condation,通过Condation的await和signalAll/signal方法。
在启用了计算线程之后，通过Condation的await方法阻塞，待计算线程执行完毕再执行signal方法
 */
public class ThreadTest07 {

        private static final ReentrantLock lock = new ReentrantLock(true);
        private static final Condition c1 = lock.newCondition();

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

            lock.lock();
            try {
                c1.await();
            } finally {
                lock.unlock();
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
                lock.lock();
                try {
                    result = sum();
                    c1.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

}
