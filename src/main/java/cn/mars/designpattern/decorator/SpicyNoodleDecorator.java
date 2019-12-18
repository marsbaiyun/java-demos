package cn.mars.designpattern.decorator;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class SpicyNoodleDecorator extends NoodleDecorator {

    public SpicyNoodleDecorator(Noodle decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void cook() {
        decoratedShape.cook();
        System.out.println("add spicy");
    }
}
