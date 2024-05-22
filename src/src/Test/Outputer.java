package Test;

import java.util.Map;

public class Outputer {
    private AlgoResult[] results;


    public Outputer (Executer executer) {
        results = executer.getResults();
    }

    public void printResults() {

    }

    public int getTotalTime() {

    }

    public double getCPUUtilization() {

    }

    public Map<String, Integer> getWaitingTimes() {

    }

    public double getAverageWaitingTime() {

    }

    public Map<String, Integer> getTurnaroundTimes() {

    }

    public double getAverageTurnaroundTime() {

    }

    public Map<String, Integer> getResponseTimes() {

    }

    public double getAverageResponseTime() {
        
    }
}
