package Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Outputer {
    private AlgoResult[] results;

    public Outputer (Executor executer) {
        results = executer.getResults();
    }
    public Outputer() {
        //Constructor for Testing purposes only.
    }

    /**
     * Formats and prints the results of the given scheduling algorithm
     */
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

    public void writeResultsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (AlgoResult result : results) {
                for (int i = 0; i < result.getCPUactivity().size(); i++) {

                }
                if (result != null) {
                    writer.write("========================================\n");
                    writer.write("Results for " + result.getAlgorithmName() + ":\n");
                    writer.write("Total time to complete all processes: " + getTotalTime(result) + "\n");
                    writer.write("CPU Utilization: " + getCPUUtilization(result) + "%\n");

                    Map<String, Integer> waitingTimes = getWaitingTimes(result);
                    writer.write("Waiting times for each process:\n");
                    for (Map.Entry<String, Integer> entry : waitingTimes.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }
                    writer.write("Average waiting time: " + getAverageTime(waitingTimes) + "\n");

                    Map<String, Integer> turnaroundTimes = getTurnaroundTimes(result);
                    writer.write("Turnaround times for each process:\n");
                    for (Map.Entry<String, Integer> entry : turnaroundTimes.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }
                    writer.write("Average turnaround time: " + getAverageTime(turnaroundTimes) + "\n");

                    Map<String, Integer> responseTimes = getResponseTimes(result);
                    writer.write("Response times for each process:\n");
                    for (Map.Entry<String, Integer> entry : responseTimes.entrySet()) {
                        writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                    }
                    writer.write("Average response time: " + getAverageTime(responseTimes) + "\n");
                    writer.write("========================================\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Creates a divider for style
     */
    private void printDivider() {
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print('-');
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Returns the total time to complete all processes
     * @param result results of the algorithm
     * @return total execution time
     */
    private int getTotalTime(AlgoResult result) {
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        if (result.getCPUactivity().isEmpty()) {
            return 0;
        }
        return cpuActivity.get(cpuActivity.size() - 1).getStopTime();
    }

    /**
     * Calculates and returns the cpu utilization of the algorithm
     * @param result results of the algorithms
     * @return cpu utilization
     */
    public double getCPUUtilization(AlgoResult result) {
        int totalCPUTime = 0;
        for (AlgoResult.Pair pair : result.getCPUactivity())
            totalCPUTime += pair.getStopTime() - pair.getStartTime();
        return ((double) totalCPUTime / getTotalTime(result)) * 100;
    }

    /**
     * Calculates and returns the waiting times of each process
     * @param result results of the algorithm
     * @return waiting times for each process
     */
    public Map<String, Integer> getWaitingTimes(AlgoResult result) {
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

    /**
     * Calculates and returns the turnaround times of each process
     * @param result results of the algorithm
     * @return turnaround times for each process
     */
    public Map<String, Integer> getTurnaroundTimes(AlgoResult result) {
        Map<String, Integer> turnaroundTimes = new TreeMap<>();
        ArrayList<AlgoResult.Pair> cpuActivity = result.getCPUactivity();
        ArrayList<String> executionOrder = result.getExecutionOrder();
        for (int i = 0; i < cpuActivity.size(); i++) {
            turnaroundTimes.put(executionOrder.get(i), cpuActivity.get(i).getStopTime());
        }
        return turnaroundTimes;
    }

    /**
     * Calculates and returns the response times of each process
     * @param result results of the algorithm
     * @return response times for each process
     */
    public Map<String, Integer> getResponseTimes(AlgoResult result) {
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

    /**
     * Calculates and returns averages of given times recorded in a map
     * @param times times for each process
     * @return the average of all times
     */
    public double getAverageTime(Map<String, Integer> times) {
        int totalTime = 0;
        for (int time : times.values()) {
            totalTime += time;
        }
        return (double) totalTime / times.size();
    }
}
