package Algorithms;

import Test.AlgoResult;
import Test.SProcess;
import Test.AlgoResult.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class implementation of Priority Scheduling Algorithm
 *
 * @author KRB/CSB340
 * @version 1.6
 */
public class PrioritySch implements SchedulerAlgorithm {
    /** AlgoResult to hold results **/
    private AlgoResult myResult;

    /**
     * Constructor for PriorityScheduling Class
     */
    public PrioritySch(boolean showOutput) {
        this.myResult = new AlgoResult(getName(), showOutput);
    }

    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        int timeElapsed = 0;
        Queue<SProcess> waitingForIO = new LinkedList<>();
        ArrayList<SProcess> readyQueue = new ArrayList<>(processes);

        while (!readyQueue.isEmpty() || !waitingForIO.isEmpty()) {
            Queue<SProcess> tempQ = new LinkedList<>();
            while (!waitingForIO.isEmpty()) {
                SProcess process = waitingForIO.poll();
                if (process.getReturnTime() <= timeElapsed) {
                    readyQueue.add(process);
                } else {
                    tempQ.add(process);
                }
            }
            waitingForIO = tempQ;

            if (!readyQueue.isEmpty()) {
                readyQueue.sort(Comparator.comparingInt(SProcess::getPriorityLevel));

                SProcess current = readyQueue.get(0);
                int currCPUburst = current.getBurstTimes()[current.getCurrCPUindex()];
                boolean preempted = false;

                while (currCPUburst > 0) {
                    timeElapsed++;
                    currCPUburst--;

                    // Check if any process in IO wait queue is ready
                    tempQ = new LinkedList<>();
                    while (!waitingForIO.isEmpty()) {
                        SProcess process = waitingForIO.poll();
                        if (process.getReturnTime() <= timeElapsed) {
                            readyQueue.add(process);
                        } else {
                            tempQ.add(process);
                        }
                    }
                    waitingForIO = tempQ;

                    // Re-sort readyQueue based on priority
                    readyQueue.sort(Comparator.comparingInt(SProcess::getPriorityLevel));

                    // Check for preemption
                    if (!readyQueue.isEmpty() && readyQueue.get(0) != current) {
                        readyQueue.add(current);
                        preempted = true;
                        break;
                    }
                }

                if (!preempted) {
                    current.setStartTime(timeElapsed - current.getBurstTimes()[current.getCurrCPUindex()]);
                    current.setStopTime(timeElapsed);

                    myResult.getCPUactivity().add(new Pair(current.getStartTime(), current.getStopTime()));
                    myResult.getExecutionOrder().add(current.getTitle());

                    if (current.getCurrIOindex() + 2 >= current.getBurstTimes().length) {
                        current.setExitTime(timeElapsed);
                    } else {
                        int ioBurst = current.getBurstTimes()[current.getCurrIOindex()];
                        current.setReturnTime(timeElapsed + ioBurst);
                        current.setCurrCPUindex(current.getCurrCPUindex() + 2);
                        current.setCurrIOindex(current.getCurrIOindex() + 2);
                        waitingForIO.add(current);
                    }

                    readyQueue.remove(current);
                }
            } else {
                assert waitingForIO.peek() != null;
                int nextIOcompleted = waitingForIO.peek().getReturnTime();
                for (SProcess process : waitingForIO) {
                    if (process.getReturnTime() < nextIOcompleted) {
                        nextIOcompleted = process.getReturnTime();
                    }
                }
                timeElapsed = nextIOcompleted;
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
        return "Priority Scheduling (naturally pre-emptive)";
    }

    /**
     * Method to result AlgoResult object that has final order of execution
     * @return AlgoResult object
     */
    @Override
    public AlgoResult getResults() {
        return myResult;
    }
}