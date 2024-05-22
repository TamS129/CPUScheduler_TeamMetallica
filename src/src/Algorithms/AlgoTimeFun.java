package Algorithms;
import java.util.*;
public class AlgoTimeFun {
    ArrayList<Integer> responseTimes;
    ArrayList<Integer> waitTimes;
    ArrayList<Integer> turnTimes;

    /**
     * Calculates the Response Times and returns the ArrayList containing the response times.
     * @param processStart - Integer array that holds all the records of CPU start times.
     * @param arrival - Integer array that holds all arrival times.
     * @return - Arraylist holding all response times for a process.
     */
    public int responseTime(int processStart, int arrival){
        int responseTime = processStart - arrival;

        return responseTime;
    }

    /**
     * Calculates the Waiting times and returns a ArrayList containing the waiting times for a process.
     * @param exitTimes - Integer array that holds all exit times for a process.
     * @param arrivals - Integer array holding all arrival times for a process.
     * @param bursts - Integer array holding all CPU burst times.
     * @return Arraylist of all waiting times.
     */

    public int waitingTime(int exitTimes, int arrivals, int bursts){
            int waitTime;
           waitTime = exitTimes - arrivals - bursts;
           return waitTime;
    }

    /**
     *  Calculates the turnaround time for a process.
     * @param exitTimes - Integer array holding all exit times.
     * @param arrival- Integer holding all arrival time.
     * @return An ArrayList that holds all turn around times.
     */
    public int turnTime(int exitTimes, int arrival){

        int turnTime = exitTimes - arrival;
        return turnTime;
    }

}
