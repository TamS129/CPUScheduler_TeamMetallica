package Test;

import java.util.Map;

public class Outputer {
    private AlgoResult[] results;


    public Outputer (Executer executer) {
        results = executer.getResults();
    }

    public void printResults() {
        for (AlgoResult result: results) {
            System.out.println("Results for " + result.getAlgorithmName() + ":");
            System.out.println("Total time to complete all processes: " + getTotalTime());
            System.out.println("CPU Utilization: " + getCPUUtilization() + "%");

            Map<String, Integer> waitingTimes = getWaitingTimes();
            System.out.println("Waiting times for each process:");
            for (Map.Entry<String, Integer> entry : waitingTimes.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Average waiting time: " + getAverageWaitingTime());

            Map<String, Integer> turnaroundTimes = getTurnaroundTimes();
            System.out.println("Turnaround times for each process:");
            for (Map.Entry<String, Integer> entry : turnaroundTimes.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Average turnaround time: " + getAverageTurnaroundTime());

            Map<String, Integer> responseTimes = getResponseTimes();
            System.out.println("Response times for each process:");
            for (Map.Entry<String, Integer> entry : responseTimes.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Average response time: " + getAverageResponseTime());
        }
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
