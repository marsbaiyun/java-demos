package cn.mars.sensitiveword;

import cn.mars.demo.A;
import cn.mars.demo.B;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.InstanceOf;
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
//        long start = System.currentTimeMillis();
//        String result = sensitiveWordUtil.filter("你好啊习近平");
//        System.out.println("过滤结果：" +result + "，耗时：" +(System.currentTimeMillis()-start)+ "ms");

        A a = new A();
        B b = new B();
        checkExtends(a);
        checkExtends(b);
    } 

    private <T extends A> T checkExtends(T t) {
        if(t.getClass() == A.class){
            System.out.println("A...");
        }else if (t.getClass() == B.class) {
            System.out.println("B...");
        }
        return t;
    }
    
    } 
