package Algorithms;

import Test.AlgoResult;
import Test.SProcess;

import java.util.ArrayList;

/**
 * Interface for a Scheduling Algorithm to adhere to.
 */
public interface SchedulerAlgorithm {
    /**
     * Method to run a list of processes through a scheduling algorithm
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    public AlgoResult runAlgo(ArrayList<SProcess> processes);

    /**
     * Method to return the name of the scheduling algorithm
     * @return Name of Scheduling algo
     */
    public String getName();
}
