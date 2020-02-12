package cn.mars.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/** 
* StarterService Tester. 
* 
* @author Mars 
* @since 02/09/2020
* @version 1.0 
*/ 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class StarterServiceTest { 

    @Autowired
    private StarterService starterService;

    /**
    * 
    * Method: say() 
    * 
    */ 
    @Test
    public void testSay() throws Exception {
        starterService.say();
    } 
    
    
    } 
