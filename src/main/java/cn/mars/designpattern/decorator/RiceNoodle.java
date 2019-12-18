package cn.mars.designpattern.decorator;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class RiceNoodle implements Noodle{

    @Override
    public void cook() {
        System.out.println("RiceNoodle");
    }
}
