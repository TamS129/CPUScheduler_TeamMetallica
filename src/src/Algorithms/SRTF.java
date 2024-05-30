package Algorithms;

import Test.AlgoResult;
import Test.SProcess;
import Test.AlgoResult.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class implementation of Shortest Remaining Time First (SRTF) Scheduling Algorithm
 */
public class SRTF implements SchedulerAlgorithm {

    private AlgoResult myResult;

    /**
     * Constructor for SRTF Class
     */
    public SRTF() {
        this.myResult = new AlgoResult(getName());
    }

    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object with final order of execution
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        int timeElapsed = 0;
        PriorityQueue<SProcess> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.getBurstTimes()[p.getCurrCPUindex()]));
        Queue<SProcess> waitingForIO = new LinkedList<>();

        while (!processes.isEmpty() || !readyQueue.isEmpty() || !waitingForIO.isEmpty()) {
            while (!processes.isEmpty() && processes.get(0).getStartTime() <= timeElapsed) {
                readyQueue.add(processes.remove(0));
            }

            if (!readyQueue.isEmpty()) {
                SProcess current = readyQueue.poll();
                int currCPUburst = current.getBurstTimes()[current.getCurrCPUindex()];
                int nextArrivalTime = processes.isEmpty() ? Integer.MAX_VALUE : processes.get(0).getStartTime();
                int timeToRun = Math.min(currCPUburst, nextArrivalTime - timeElapsed);

                current.setStartTime(timeElapsed);
                timeElapsed += timeToRun;

                if (timeToRun == currCPUburst) {
                    current.setCurrCPUindex(current.getCurrCPUindex() + 2);
                    if (current.getCurrCPUindex() >= current.getBurstTimes().length) {
                        current.setExitTime(timeElapsed);
                    } else {
                        int ioBurst = current.getBurstTimes()[current.getCurrIOindex()];
                        current.setReturnTime(timeElapsed + ioBurst);
                        current.setCurrIOindex(current.getCurrIOindex() + 2);
                        waitingForIO.add(current);
                    }
                } else {
                    current.getBurstTimes()[current.getCurrCPUindex()] -= timeToRun;
                    readyQueue.add(current);
                }

                myResult.getCPUactivity().add(new Pair(current.getStartTime(), timeElapsed));
                myResult.getExecutionOrder().add(current.getTitle());
            } else if (!waitingForIO.isEmpty()) {
                SProcess process = waitingForIO.poll();
                timeElapsed = process.getReturnTime();
                readyQueue.add(process);
            } else if (!processes.isEmpty()) {
                timeElapsed = processes.get(0).getStartTime();
            }
        }

        return myResult;
    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return "Shortest Remaining Time First";
    }

    /**
     * Method to return AlgoResult object that has final order of execution
     * @return AlgoResult object
     */
    @Override
    public AlgoResult getResults() {
        return myResult;
    }
}