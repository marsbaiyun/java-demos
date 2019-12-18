package cn.mars.designpattern.decorator;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public abstract class NoodleDecorator implements Noodle {
    protected Noodle decoratedShape;

    public NoodleDecorator(Noodle decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void cook() {
        decoratedShape.cook();
    }
}
