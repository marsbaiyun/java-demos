package cn.mars.designpattern.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class StatisticsDisplay implements Observe, DisplayElement {

    private float temperature;
    private WeatherData weatherData;
    private List<Float> list;

    public StatisticsDisplay(WeatherData weatherData) {
        list = new ArrayList<Float>();
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        list.add(temp);
        display();
    }

    @Override
    public void display() {
        // 排序
        Collections.sort(list);
        float temp = 0;
        for (int i = 0; i < list.size(); i++) {
            temp+=list.get(i);
        }
        int vagtemp = (int) (temp/list.size());
        System.out.println("气温最大值/平均值/最小值："+list.get(list.size()-1)+"/"+vagtemp+"/"+list.get(0)+" 摄氏度");
    }
}
