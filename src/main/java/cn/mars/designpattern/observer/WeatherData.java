package cn.mars.designpattern.observer;

import java.util.ArrayList;

/**
 * Description：
 * Created by Mars on 2018/4/19.
 */
public class WeatherData implements Subject {

    private ArrayList<Observe> observes;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observes = new ArrayList<Observe>();
    }

    @Override
    public void registerObserver(Observe observe) {
        observes.add(observe);
    }

    @Override
    public void removeObserver(Observe observe) {
        if(observes.contains(observe)){
            observes.remove(observe);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observes.size(); i++) {
            Observe observe = observes.get(i);
            observe.update(temperature, humidity, pressure);
        }
    }

    public void measurementChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
