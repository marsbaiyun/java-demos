package cn.mars.designpattern.observer;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public interface Subject {
    public void registerObserver(Observe observe);
    public void removeObserver(Observe observe);
    public void notifyObserver();
}
