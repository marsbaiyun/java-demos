package cn.mars.designpattern.observer;

/**
 * Description：
 * Created by Mars on 2018/4/20.
 */
public class WeatherTest {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay display = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(20, 75, 30.4f);
        weatherData.setMeasurements(32, 70, 29.4f);
        weatherData.setMeasurements(18, 90, 31.4f);
    }
}
