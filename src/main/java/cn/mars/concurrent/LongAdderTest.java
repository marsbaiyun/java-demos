package cn.mars.concurrent;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description：
 * Created by Mars on 2020/3/4.
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.Throughput)
public class LongAdderTest {

    private static AtomicLong count = new AtomicLong();
    private static LongAdder longAdder = new LongAdder();
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(LongAdderTest.class.getName()).forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(2)
    public void run0(){
        count.getAndIncrement();
    }

    @Benchmark
    @Threads(2)
    public void run1(){
        longAdder.increment();
    }
}
