package Test;

import java.util.ArrayList;
import java.util.Queue;

public class AlgoResult {

    String algorithmName;
    ArrayList<Pair> CPUactivity;
    ArrayList<String> executionOrder;
    ArrayList<Queue<SProcess>> readyQueueActivity;
    ArrayList<Queue<SProcess>> ioQueueActivity;
    boolean showOutput;

    /**
     * Constructor that holds an object for our algorithm name, ArrayList of CPU activities, and executionOrder.
     * @param algorithmName - Name of the current algorithm. Ex "Round Robin"
     */
    public AlgoResult(String algorithmName, boolean showOutput){
        this.algorithmName = algorithmName;
        this.CPUactivity = new ArrayList<>();
        this.executionOrder = new ArrayList<>();
        this.readyQueueActivity = new ArrayList<>();
        this.ioQueueActivity = new ArrayList<>();
        this.showOutput = showOutput;
    }

    /**
     * Getter method that returns our algorithm name.
     * @return String that holds the name of the algorithm.
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Returns an Arraylist that holds our CPUActivity list.
     * @return ArraList<Pair> of our CPU activity.
     */
    public ArrayList<Pair> getCPUactivity() {
        return CPUactivity;
    }

    /**
     * Returns an Arraylist that holds our executionOrder list.
     * @return ArraList<Pair> of our execution order.
     */
    public ArrayList<String> getExecutionOrder() {
        return executionOrder;
    }

    public ArrayList<Queue<SProcess>> getReadyQueueActivity() {return readyQueueActivity;}

    public ArrayList<Queue<SProcess>> getIoQueueActivity() {return  ioQueueActivity;}

    public static class Pair {
        private int startTime;
        private int stopTime;

        /**
         * Constructor that holds every stop and start time of our processes.
         * @param num1 - Start time of our current process's burst
         * @param num2 - Stop time of our current process's burst.
         */
        public Pair(int num1, int num2){
            this.startTime = num1;
            this.stopTime = num2;
        }

        /**
         * Sets a new start time for our process.
         * @param time - New start time for our process.
         */
        public void setStartTime(int time) { this.startTime = time;}

        /**
         * Getter method that returns the start time.
         * @return Current start time of our process.
         */
        public int getStartTime() { return startTime;}

        /**
         * Sets a new stop time for our process.
         * @param time - New stop time for our process.
         */
        public void setStopTime(int time) { this.stopTime = time;}

        /**
         * Getter method that returns the stop time.
         * @return Current stop time of our process.
         */
        public int getStopTime() { return stopTime;}
    }
}
