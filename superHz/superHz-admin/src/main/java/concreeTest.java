import java.util.Arrays;
import java.util.Random;

class TheadTest implements Runnable {

    volatile Integer a = 10;

    public synchronized void M1() throws InterruptedException {
        System.out.println("异步执行");
        a = 100;
        Thread.sleep(5);
        System.out.println("M1a=" + a);
    }

    public synchronized void M2() throws InterruptedException {
        a = 200;
        Thread.sleep(5);
        System.out.println("M2a=" + a);
    }

    public void run() {
        try {
            this.M1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class concreeTest {

    public static void main(String[] args) throws InterruptedException {
        TheadTest theadTest = new TheadTest();
        int[] abc = {1, 2, 4};

        Thread thread = new Thread(theadTest);
        thread.start();
        theadTest.M2();
/*        new Thread(()->{
            try {
                theadTest.M2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();*/
        System.out.println("Ma=" + theadTest.a);
        int x = 8, y = 2, z;
        int z1 = ++x * y;
        int z2 = x / y++;
        System.out.println(z1);
        System.out.println(z2);


        int a[] = new int[10];
        Random random = new Random();

        for (int i = 0; i < a.length; i++) {

            int RND = random.nextInt(20);    //生成10个20以内的随机数
            a[i] = RND;

            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {            //判断a[i]和a[j]是否相同，如果相同，将i值退回
                    i--;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(a));


    }
}
