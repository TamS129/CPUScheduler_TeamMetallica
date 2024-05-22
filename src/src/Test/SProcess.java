package Test;


public class SProcess {
    private String title;
    private int[] burstTimes;


    public SProcess() {
        this.title = null;
        this.burstTimes = null;
    }
    public SProcess(String title, int[] burstTimes ){
        this.title = title;
        this.burstTimes = burstTimes;

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
