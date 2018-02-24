package cn.mars.queue.thread;

import cn.mars.queue.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
* AsyncThread Tester. 
* 
* @author Mars 
* @since 11/06/2017
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AsyncThreadTest.class)
@WebAppConfiguration
public class AsyncThreadTest {

        /** 
    * 
    * Method: run() 
    * 
    */ 
    @Test
    public void testRun() throws Exception { 
    //TODO: Test goes here... 
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
