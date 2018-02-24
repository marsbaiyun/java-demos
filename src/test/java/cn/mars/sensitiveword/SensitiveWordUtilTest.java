package cn.mars.sensitiveword;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
* SensitiveWordUtil Tester. 
* 
* @author Mars 
* @since 11/06/2017
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SensitiveWordUtilTest { 

    @Autowired
    SensitiveWordUtil sensitiveWordUtil;

        /** 
    * 
    * Method: addWord(String lineText) 
    * 
    */ 
    @Test
    public void testAddWord() throws Exception {
        long start = System.currentTimeMillis();
        String result = sensitiveWordUtil.filter("你好啊习近平");
        System.out.println("过滤结果：" +result + "，耗时：" +(System.currentTimeMillis()-start)+ "ms");
    } 
    
    
    } 
