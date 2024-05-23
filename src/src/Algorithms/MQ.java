package Algorithms;

import Test.AlgoResult;
import Test.SProcess;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;public class MQ implements SchedulerAlgorithm {
    private static final String ALGO_NAME = "Multilevel Queue Scheduling";  /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        AlgoResult result = new AlgoResult(ALGO_NAME);    // Assuming there are 3 levels of queues
        Queue<SProcess> highPriorityQueue = new LinkedList<>();
        Queue<SProcess> mediumPriorityQueue = new LinkedList<>();
        Queue<SProcess> lowPriorityQueue = new LinkedList<>();    // Simulated priority assignment based on title for simplicity (adjust as needed)
        for (SProcess process : processes) {
            String title = process.getTitle();
            if (title.contains("HP")) {
                highPriorityQueue.add(process);
            } else if (title.contains("MP")) {
                mediumPriorityQueue.add(process);
            } else {
                lowPriorityQueue.add(process);
            }
        }    int currentTime = 0;    // Process queues based on priority
        while (!highPriorityQueue.isEmpty() || !mediumPriorityQueue.isEmpty() || !lowPriorityQueue.isEmpty()) {
            if (!highPriorityQueue.isEmpty()) {
                currentTime = processQueue(result, highPriorityQueue, currentTime);
            } else if (!mediumPriorityQueue.isEmpty()) {
                currentTime = processQueue(result, mediumPriorityQueue, currentTime);
            } else if (!lowPriorityQueue.isEmpty()) {
                currentTime = processQueue(result, lowPriorityQueue, currentTime);
            }
        }    return result;
    }  /**
     * Processes the given queue of processes and updates the result and current time.
     *
     * @param result  The AlgoResult object to update
     * @param queue   The queue of processes to process
     * @param startTime The current time
     * @return The updated current time
     */
    private int processQueue(AlgoResult result, Queue<SProcess> queue, int startTime) {
        SProcess process = queue.poll();
        int[] burstTimes = process.getBurstTimes();
        int burstTime = burstTimes[process.getCurrCPUindex()];
        result.getExecutionOrder().add(process.getTitle());
        result.getCPUactivity().add(new AlgoResult.Pair(startTime, startTime + burstTime));
        process.setCurrCPUindex(process.getCurrCPUindex() + 1); // Update to the next burst time
        return startTime + burstTime;
    }  /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return ALGO_NAME;
    }
}