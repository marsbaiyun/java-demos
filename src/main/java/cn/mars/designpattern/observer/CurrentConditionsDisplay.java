package cn.mars.designpattern.observer;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class CurrentConditionsDisplay implements Observe, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.humidity = humidity;
        this.temperature = temp;
        display();
    }

    @Override
    public void display() {
        System.out.println("当前气温：："+temperature+"摄氏度，相对湿度为："+humidity+"%");
    }
}
