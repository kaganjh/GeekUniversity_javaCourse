import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*
由于Runnable是没有返回值的，那么java再解决这个问题的时候引入了Callable,我们也可以利用Callable来实现
 */
public class ThreadTest17 {
        public static void main(String[] args) throws Exception {
            long start = System.currentTimeMillis();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            Callable<Integer> task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return sum();
                }
            };
            Future<Integer> future = executorService.submit(task);
            System.out.println("异步计算结果：" + future.get());
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
            executorService.shutdown();

        }

        private static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if (a < 2) {
                return 1;
            }
            return fibo(a - 1) + fibo(a - 2);
        }

}
