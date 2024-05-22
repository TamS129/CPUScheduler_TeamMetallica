package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        Map<String, Integer> waitingTimes = new TreeMap<>();
        Map<String, Integer> turnaroundTimes = getTurnaroundTimes(result);
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        ArrayList<String> executionOrder = result.getExecutionOrder();

        // Get burst time for all processes
        for (int i = 0; i < cpuActivity.size(); i++) {
            int burstTime = cpuActivity.get(i).getStopTime() - cpuActivity.get(i).getStartTime();
            if (!waitingTimes.containsKey(executionOrder.get(i))) {
                waitingTimes.put(executionOrder.get(i), burstTime);
            } else {
                waitingTimes.put(executionOrder.get(i), waitingTimes.get(executionOrder.get(i)) + burstTime);
            }
        }

        // Calculate waiting time (TAT - BT)
        for (Map.Entry<String, Integer> entry : waitingTimes.entrySet()) {
            waitingTimes.put(entry.getKey(), turnaroundTimes.get(entry.getKey()) - entry.getValue());
        }
        return waitingTimes;
    }

    private Map<String, Integer> getTurnaroundTimes(AlgoResult result) {
        Map<String, Integer> turnaroundTimes = new TreeMap<>();
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        ArrayList<String> executionOrder = result.getExecutionOrder();
        for (int i = 0; i < cpuActivity.size(); i++) {
            turnaroundTimes.put(executionOrder.get(i), cpuActivity.get(i).getStopTime());
        }
        return turnaroundTimes;
    }

    private Map<String, Integer> getResponseTimes(AlgoResult result) {
        Map<String, Integer> responseTimes = new TreeMap<>();
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        ArrayList<String> executionOrder = result.getExecutionOrder();
        for (int i = 0; i < cpuActivity.size(); i++) {
            if (!responseTimes.containsKey(executionOrder.get(i))) {
                responseTimes.put(executionOrder.get(i), cpuActivity.get(i).getStartTime());
            }
        }
        return responseTimes;
    }

    private double getAverageTime(Map<String, Integer> times) {
        int totalTime = 0;
        for (int time : times.values()) {
            totalTime += time;
        }
        return (double) totalTime / times.size();
    }
}
