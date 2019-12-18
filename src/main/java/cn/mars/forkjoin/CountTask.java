package cn.mars.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description：
 * Created by Mars on 2018/5/2.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2;//阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompote = (end - start) <= THRESHOLD;
        if(canCompote){
            for(int i = start;i <= end;i ++) {
                sum += i;
            }
        }else {
            int middle = (start + end)/2;
            CountTask lefTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            lefTask.fork();
            rightTask.fork();

            Integer leftResult = lefTask.join();
            Integer rightResult = rightTask.join();

            sum = leftResult.intValue() + rightResult.intValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        CountTask task = new CountTask(1, 1000000000);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
            System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        int i = 0;
        start = System.currentTimeMillis();
        for(int j = 1;j <= 1000000000;j ++){
            i += j;
        }
        System.out.println(i);
        System.out.println("耗时：" + (System.currentTimeMillis() - start) + " ms");
    }
}
