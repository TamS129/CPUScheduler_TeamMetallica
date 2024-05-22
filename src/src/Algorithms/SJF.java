package Algorithms;

import Test.AlgoResult;
import Test.SProcess;

import java.util.ArrayList;

/**
 * Class implementation of Shortest Job First (SJF) Scheduling Algorithm
 *
 * @author KRB/CSB340
 * @version 1.4
 */
public class SJF implements SchedulerAlgorithm{
    /** AlgoResult to hold results **/
    private AlgoResult myResult;
    private ArrayList<Process> list;

    /**
     * Constructor for SJF Class
     */
    public SJF(){
        this.myResult = new AlgoResult();
        this.list = null;
    }

    public SJF(ArrayList<Process> processes) {
        this.list = processes;
        this.myResult = new AlgoResult();
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
        SProcess current = processes.get(0);
        int currCPUburst = current.getBurstTimes()[0];

        for(int i = 1; i < processes.size(); i++) {
            SProcess process = processes.get(i);
            if (process.getBurstTimes()[0] < currCPUburst) {
                current = process;
                currCPUburst = process.getBurstTimes()[0];
            }
        }
        timeElapsed += currCPUburst;

        // Need a returnTime for Process to get back from I/O land
        int returnTime = timeElapsed + current.getBurstTimes()[1];

        return null;
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
}
