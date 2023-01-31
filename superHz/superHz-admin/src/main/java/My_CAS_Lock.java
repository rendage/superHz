import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自己实现一个自旋锁 (原子引用+CAS)
 * @author ruoxi
 */
public class My_CAS_Lock {
    /**
     * 使用原子引用，指定泛型类型为Thread
     */
    static AtomicReference<Thread> atomicReference = new AtomicReference<>();
    /**
     * CAS算法实现自旋锁的加锁
     */
    public void myLock() {
        //获取当前线程
        Thread thread = Thread.currentThread();
        //CAS算法实现 自旋锁
        //预期值：thread=null  读取内存中的值：当前线程的引用 thread = this.thread
        //如果当前线程不是空，则不能获取锁(说明有线程拿到锁了)，开始一直自旋
        while(!atomicReference.compareAndSet(null,thread)){
            try {
                //使线程每次自旋间隔为1毫秒
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName()+"=>在等待解锁..自旋中..");
        }
        //加锁 (其实只是利用CAS算法将其他线程隔离了)
        System.out.println(thread.getName()+"拿到了锁");
    }
    /**
     * CAS算法实现自旋锁的解锁
     */
    public void unlock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"释放了锁");
        //将当前线程置换为空，也就是释放了锁
        atomicReference.compareAndSet(thread,null);
    }
    /**
     * 测试
     */
    public static void main(String[] args) {
        //实例化一个自己实现的自旋锁
        My_CAS_Lock lock = new My_CAS_Lock();
        //开启两个线程，都需要获取自旋锁才能执行
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                //上锁
                lock.myLock();
                try {
                    //让线程沉睡10毫秒再解锁
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //解锁
                    lock.unlock();
                }
            },"线程"+(i+1)).start();
        }
    }
}


