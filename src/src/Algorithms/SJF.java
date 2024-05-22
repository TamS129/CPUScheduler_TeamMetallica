package Algorithms;

import Test.AlgoResult;
import Test.SProcess;
import Test.AlgoResult.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class implementation of Shortest Job First (SJF) Scheduling Algorithm
 *
 * @author KRB/CSB340
 * @version 1.4
 */
public class SJF implements SchedulerAlgorithm{
    /** AlgoResult to hold results **/
    private AlgoResult myResult;


    /**
     * Constructor for SJF Class
     */
    public SJF(){
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
        Queue<SProcess> waitingForIO = new LinkedList<>();
        Queue<SProcess> readyQueue = new LinkedList<>(processes);

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

            if(!readyQueue.isEmpty()) {
                ArrayList<SProcess> sortedList = new ArrayList<>(readyQueue);
                sortedList.sort(Comparator.comparingInt(p -> p.getBurstTimes()[p.getCurrCPUindex()]));
                readyQueue.clear();
                readyQueue.addAll(sortedList);

                SProcess current = readyQueue.poll();
                assert current != null;
                int currCPUburst = current.getBurstTimes()[current.getCurrCPUindex()];
                current.setStartTime(timeElapsed);
                current.setStopTime(timeElapsed + currCPUburst);
                timeElapsed += currCPUburst;

                myResult.getCPUactivity().add(new Pair(current.getStartTime(), current.getStopTime()));
                myResult.getExecutionOrder().add(current.getTitle());

                if(current.getCurrIOindex() + 2 >= current.getBurstTimes().length) {
                    current.setExitTime(timeElapsed);

                    //maybe have a boolean "execComplete" for a SProcess?

                } else {
                    int ioBurst = current.getBurstTimes()[current.getCurrIOindex()];
                    current.setReturnTime(timeElapsed + ioBurst);
                    current.setCurrCPUindex(current.getCurrCPUindex() + 2);
                    current.setCurrIOindex(current.getCurrIOindex() + 2);
                    waitingForIO.add(current);
                }
            } else {
                // Problem lies here I think
                assert waitingForIO.peek() != null;
                int nextIOcompleted = waitingForIO.peek().getReturnTime();
                for(SProcess process : waitingForIO) {
                    if(process.getReturnTime() < nextIOcompleted) {
                        nextIOcompleted = process.getReturnTime();
                    }
                }
                //myResult.getCPUactivity().add(new Pair(timeElapsed, nextIOcompleted));
                //myResult.getExecutionOrder().add("--IDLE--");

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
        return "Shortest Job First, Non-preemptive";
    }

    /**
     * Method to result AlgoResult object that has final order of execution
     * @return AlgoResult object
     */
    public AlgoResult getMyResult() {
        return myResult;
    }
}
