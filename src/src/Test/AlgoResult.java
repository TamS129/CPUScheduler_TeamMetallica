package Test;

import java.util.ArrayList;

public class AlgoResult {
    ArrayList<Integer> responseTimes;
    ArrayList<Integer> waitTimes;
    ArrayList<Integer> turnTimes;
    ArrayList<Pair> CPUactivity;
    ArrayList<String> executionOrder;

    public AlgoResult(){
        responseTimes = new ArrayList<>();
        waitTimes = new ArrayList<>();
        turnTimes = new ArrayList<>();
    }

    /**
     * Constructor to hold all result arraylists for our algorithms.
     * @param responseTimes - Arraylist of response times.
     * @param waitTimes - Arraylist of waiting times.
     * @param turnTimes - Arraylist of turnaround times.
     */
    public AlgoResult(ArrayList<Integer> responseTimes, ArrayList<Integer> waitTimes, ArrayList<Integer> turnTimes ){
            this.responseTimes = responseTimes;
            this.waitTimes = waitTimes;
            this.turnTimes = turnTimes;
    }

    /**
     * Getter method that returns the arraylist of response times.
     * @return - Arraylist of responseTimes.
     */
    public ArrayList<Integer> getResponseTimes() {
        return responseTimes;
    }

    /**
     * Getter method that returns the arraylist of turnaround times.
     * @return - Arraylist of turnaround times.
     */

    public ArrayList<Integer> getTurnTimes() {
        return turnTimes;
    }

    /**
     * Getter method that reutns the arraylist of waiting times.
     * @return - Arraylist of waiting times.
     */

    public ArrayList<Integer> getWaitTimes() {
        return waitTimes;
    }

    /**
     * Sets new values for the Arraylist responseTimes.
     * @param responseTimes - Arraylist that holds the new response times.
     */

    public void setResponseTimes(ArrayList<Integer> responseTimes) {
        this.responseTimes = responseTimes;
    }

    /**
     * Sets new values for the Arraylist turnTimes.
     * @param turnTimes - New Arraylist for turnaround times.
     */

    public void setTurnTimes(ArrayList<Integer> turnTimes) {
        this.turnTimes = turnTimes;
    }


    /**
     * Sets new values for the Arraylist waitTimes.
     * @param waitTimes - New Arraylist for waiting times.
     */
    public void setWaitTimes(ArrayList<Integer> waitTimes) {
        this.waitTimes = waitTimes;
    }

    private class Pair {
        private int startTime;
        private int stopTime;
        public Pair(int num1, int num2){
            this.startTime = num1;
            this.stopTime = num2;
        }

        public void setStartTime(int time) { this.startTime = time;}
        public int getStartTime() { return startTime;}
        public void setStopTime(int time) { this.stopTime = time;}
        public int getStopTime() { return stopTime;}
    }
}
