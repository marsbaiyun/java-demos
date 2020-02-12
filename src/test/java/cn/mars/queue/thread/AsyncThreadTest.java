package cn.mars.queue.thread;

import cn.mars.queue.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* AsyncThread Tester. 
* 
* @author Mars 
* @since 11/06/2017
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AsyncThreadTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedissonClient redissonClient;

        /** 
    * 
    * Method: run() 
    * 
    */ 
    @Test
    public void testRun() throws Exception {
        Random random = new Random(1000000);
        RGeo<Object> geo = redissonClient.getGeo("points");
//        for (long i = 0;i < 10000000000l;i ++) {
        for (long i = 0;i < 30000l;i ++) {
            double lat = random.nextDouble();
            double lng = random.nextDouble();

            List<Object> points = geo.radius(lng, lat, Double.MAX_VALUE, GeoUnit.FEET, GeoOrder.ASC, 1);
            if(CollectionUtils.isEmpty(points)){
                geo.add(lng, lat, i);
                logger.info("插入点{}-{},{}成功，暂时没有其他点", i, lng, lat);
                continue;
            }

            Object o = points.get(0);
            Map<Object, GeoPosition> posMap = geo.pos(o);
            GeoPosition nearestPosition = posMap.get(o);
            double latitude = nearestPosition.getLatitude();
            double longitude = nearestPosition.getLongitude();

            if(lat == latitude && lng == longitude){
                logger.info("已存在点{},{}-{}，已存在的点id为{}，故不插入点{}", lng, lat, String.valueOf(o), i);
            } else {
                geo.add(lng, lat, i);
                logger.info("插入点{}-{},{}成功，距离它最近的点是{}", i, lng, lat, String.valueOf(o));
            }
        }



    }
    
        /** 
    * 
    * Method: addTask(Task task)
    * 
    */ 
    @Test
    public void testAddTask() throws Exception {
        for(int i = 0;i < 50;i ++){
            AsyncThread.addTask(new Task(i));
        }
    }
    
        /** 
    * 
    * Method: hashCode() 
    * 
    */ 
    @Test
    public void testHashCode() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: equals(Object var1) 
    * 
    */ 
    @Test
    public void testEquals() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: uncaughtException(Thread var1, Throwable var2) 
    * 
    */ 
    @Test
    public void testUncaughtException() throws Exception { 
    //TODO: Test goes here... 
    } 
    
    
    } 
