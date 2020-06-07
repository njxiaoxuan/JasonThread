import com.jason.service.BlogService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-27 00:19
 **/
public class BookServiceImplTest extends SpringBaseTest implements Runnable{
    @Autowired
    private  BlogService blogService;

    //控制并发
    final Semaphore  semaphore=new Semaphore(10);

    @Test
    public void selectByPrimaryKey() {
        ApplicationContext act =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        act.getBean("blogService");
        System.out.println(this.blogService.queryByPage());
    }

    //压力测试
    @Test
    public  void batchStressTest() {
        ApplicationContext act =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        act.getBean("blogService");
        BookServiceImplTest   impl=new BookServiceImplTest();
         int THREAD_COUNT=30;
          //创建线程池
         ExecutorService   executorService= Executors.newFixedThreadPool(THREAD_COUNT);

         for(int i=0;i<THREAD_COUNT;i++)
             executorService.execute( new BookServiceImplTest());

         executorService.shutdown();

    }

    @Override
    public void run() {
        try {
//            long  start=System.currentTimeMillis();
            semaphore.acquire();
            this.selectByPrimaryKey();
            semaphore.release();
//            long  end=System.currentTimeMillis();
//            System.out.println("批量执行完");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
