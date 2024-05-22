package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Outputer {
    private AlgoResult[] results;


    public Outputer (Executer executer) {
        results = executer.getResults();
    }

    public void printResults() {
        for (AlgoResult result: results) {
            if (result != null) {
                printDivider();
                System.out.println("Results for " + result.getAlgorithmName() + ":");
                System.out.println("Total time to complete all processes: " + getTotalTime(result));
                System.out.println("CPU Utilization: " + getCPUUtilization(result) + "%");

                Map<String, Integer> waitingTimes = getWaitingTimes(result);
                System.out.println("Waiting times for each process:");
                for (Map.Entry<String, Integer> entry : waitingTimes.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("Average waiting time: " + getAverageTime(waitingTimes));

                Map<String, Integer> turnaroundTimes = getTurnaroundTimes(result);
                System.out.println("Turnaround times for each process:");
                for (Map.Entry<String, Integer> entry : turnaroundTimes.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("Average turnaround time: " + getAverageTime(turnaroundTimes));

                Map<String, Integer> responseTimes = getResponseTimes(result);
                System.out.println("Response times for each process:");
                for (Map.Entry<String, Integer> entry : responseTimes.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println("Average response time: " + getAverageTime(responseTimes));
                printDivider();
            }
        }

    }

    private void printDivider() {
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.println();
        System.out.println();
    }

    private int getTotalTime(AlgoResult result) {
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        if (result.getCPUactivity().isEmpty()) {
            return 0;
        }
        return cpuActivity.get(cpuActivity.size() - 1).getStopTime();
    }

    private double getCPUUtilization(AlgoResult result) {
        int totalCPUTime = 0;
        for (AlgoResult.Pair pair : result.getCPUactivity())
            totalCPUTime += pair.getStopTime() - pair.getStartTime();
        return ((double) totalCPUTime / getTotalTime(result)) * 100;
    }

    private Map<String, Integer> getWaitingTimes(AlgoResult result) {
        return new HashMap<>();
    }

    private Map<String, Integer> getTurnaroundTimes(AlgoResult result) {
        return new HashMap<>();
    }

    private Map<String, Integer> getResponseTimes(AlgoResult result) {
        return new HashMap<>();
    }

    private double getAverageTime(Map<String, Integer> times) {
        return 0.0;
    }
}
