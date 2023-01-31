import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class Atest {
    AtomicInteger count = new AtomicInteger(0);
    List<String> strList = createList();
    Spliterator<String> spliterator = strList.spliterator();
    Semaphore semaphore = new Semaphore(4);

    public static boolean isInteger(String str) {
        Pattern pattern = compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) throws InterruptedException {
        Atest atest = new Atest();
        atest.mytest();
    }

    /**
     * 多线程计算list中数值的和
     * 测试spliterator遍历
     */
    public void mytest() throws InterruptedException {

        for (int i = 0; i < 4; i++) {
            new MyThread().start();
        }


        boolean b = semaphore.tryAcquire(3, TimeUnit.SECONDS);
        if (!b) {
            System.out.println("结果为：" + count);
        }
     /*   try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    private List<String> createList() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 10 == 0) {
                result.add(i + "");
            } else {
                result.add("wqwq");
            }
        }
        return result;
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("线程" + threadName + "开始运行-----");
            spliterator.trySplit().forEachRemaining(o -> {
                if (isInteger(o)) {
                    int num = Integer.parseInt(o + "");
                    count.addAndGet(num);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("数值：" + num + "------" + threadName);
                }
            });
            System.out.println("线程" + threadName + "运行结束-----");
        }
    }
}
