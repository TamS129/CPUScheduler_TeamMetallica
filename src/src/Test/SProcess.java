package Test;


public class SProcess {
    private String title;
    private int[] burstTimes;
    private int currCPUindex;
    private int currIOindex;
    private int exitTime, startTime, stopTime;


    public SProcess() {
        this.title = null;
        this.burstTimes = null;
    }
    public SProcess(String title, int[] burstTimes ){
        this.title = title;
        this.burstTimes = burstTimes;
        currCPUindex = 0;
        currIOindex = 1;
        exitTime = -1;
        startTime = -1;
        stopTime = -1;
    }

    public int getExitTime() { return exitTime;}

    public void setExitTime(int exitTime) { this.exitTime = exitTime;}

    public int getStartTime() { return startTime; }

    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getStopTime() { return stopTime;}

    public void setStopTime(int stopTime) { this.stopTime = stopTime;}

    public int getCurrCPUindex(){ return currCPUindex;}
    public void setCurrCPUindex(int index) { this.currCPUindex = index;}
    public int getCurrIOindex() { return currIOindex;}
    public void setCurrIOindex(int index) { this.currIOindex = index;}

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
