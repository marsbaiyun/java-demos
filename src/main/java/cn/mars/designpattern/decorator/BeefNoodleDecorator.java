package cn.mars.designpattern.decorator;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class BeefNoodleDecorator extends NoodleDecorator {

    public BeefNoodleDecorator(Noodle decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void cook() {
        decoratedShape.cook();
        System.out.println("add beef");
    }
}
