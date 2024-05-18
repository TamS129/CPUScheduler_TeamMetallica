package Test;

import java.util.ArrayList;

public class Process {
    private String title;
    private int[] burstTimes;


    Process(String title, int[] burstTimes ){
        title = this.title;
        burstTimes = this.burstTimes;

    }

    public int[] getBurstTimes() {
        return burstTimes;
    }

    public String getTitle() {
        return title;
    }

    public void setBurstTimes(int[] burstTimes) {
        this.burstTimes = burstTimes;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
