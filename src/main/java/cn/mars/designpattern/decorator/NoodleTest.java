package cn.mars.designpattern.decorator;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class NoodleTest {

    public static void main(String[] args) {
        RiceNoodle riceNoodle = new RiceNoodle();
        riceNoodle.cook();

        SpicyNoodleDecorator spicyNoodleDecorator = new SpicyNoodleDecorator(riceNoodle);
        spicyNoodleDecorator.cook();

        BeefNoodleDecorator beefNoodleDecorator = new BeefNoodleDecorator(spicyNoodleDecorator);
        beefNoodleDecorator.cook();

        BeefNoodleDecorator beefNoodleDecorator2 = new BeefNoodleDecorator(beefNoodleDecorator);
        beefNoodleDecorator2.cook();
    }
}
