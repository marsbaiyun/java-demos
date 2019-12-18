package cn.mars.concurrent;

import java.util.concurrent.*;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class MyCompletionService implements Callable<String> {

    private int id;

    public MyCompletionService(int i) {
        this.id = i;
    }

    @Override
    public String call() throws Exception {
        Integer time = (int) (Math.random()*1000);
        System.out.println(this.id + " start");
        Thread.sleep(time);
        System.out.println(this.id + " end");
        return this.id+":"+time;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<String> completion = new ExecutorCompletionService<String>(service);
        for(int i = 0;i < 10;i ++){
            completion.submit(new MyCompletionService(i));
        }
        for(int i = 0;i < 10;i ++){
            System.out.println(completion.take().get());
        }
        service.shutdown();
    }
}
