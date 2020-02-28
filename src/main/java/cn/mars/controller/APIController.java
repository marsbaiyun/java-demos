package cn.mars.controller;

import cn.mars.demo.entity.TreeNode;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description：
 * Created by Mars on 2019/12/29.
 */
@RestController
@RequestMapping("/api")
public class APIController {

    @GetMapping("/hello")
    public Object hello(){
        return "hello";
    }

    @GetMapping("exception")
    public Object exception(){
        throw new NullPointerException("NPE");
    }

    @GetMapping("oom/{count}")
    public Object oom(@PathVariable Integer count){
        List<TreeNode> list = Lists.newArrayList();
        for(int i = 0;i < count;i ++){
            TreeNode node = new TreeNode(1, null, null);
            list.add(node);
        }
        return "oom";
    }

}
