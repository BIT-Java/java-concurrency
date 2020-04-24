package pl.mzlnk.bitjava.concurrency.using_volatile;

public class TemperatureMonitor {

    private int temperature = 0;

    public int getTemperature() {
        return temperature;
    }

    public void incrementTemperature() {
        this.temperature++;
    }

}
