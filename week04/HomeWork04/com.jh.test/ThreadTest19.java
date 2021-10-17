import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
jvm1.8中引入了CompleteFuture
 */
public class ThreadTest19 {
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
            ExecutorService service = Executors.newCachedThreadPool();

            CompletableFuture<Integer> future = new CompletableFuture<>();
            SumThread sumThread = new SumThread(future);
            service.submit(sumThread);
            service.shutdown();

            System.out.println("异步计算结果：" + future.get());
            System.out.println("计算耗时：" + (System.currentTimeMillis() - start) + "  ms");
        }

        static class SumThread implements Runnable {

            private CompletableFuture<Integer> future;

            public SumThread(CompletableFuture<Integer> future) {
                this.future = future;
            }

            @Override
            public void run() {
                future.complete(sum());
            }
        }

}
