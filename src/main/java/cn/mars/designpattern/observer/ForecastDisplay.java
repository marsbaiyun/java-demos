package cn.mars.designpattern.observer;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class ForecastDisplay implements Observe, DisplayElement {

    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    @Override
    public void display() {
        System.out.println("天气不错，可以出去郊游！");
    }
}
