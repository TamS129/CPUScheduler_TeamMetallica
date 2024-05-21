package Algorithms;
import java.util.*;
public class AlgoTimeFun {
    ArrayList<Integer> responseTimes;
    ArrayList<Integer> waitTimes;
    ArrayList<Integer> turnTimes;

    /**
     * Calculates the Response Times and returns the ArrayList containing the response times.
     * @param processStart - Integer array that holds all the records of CPU start times.
     * @param arrivals - Integer array that holds all arrival times.
     * @return - Arraylist holding all response times for a process.
     */
    public ArrayList<Integer> responseTime(int[] processStart, int[] arrivals){
        for(int i = 0; i < processStart.length; i++){
            responseTimes.add( processStart[i] - arrivals[i]);

        }
        return responseTimes;
    }

    /**
     * Calculates the Waiting times and returns a ArrayList containing the waiting times for a process.
     * @param exitTimes - Integer array that holds all exit times for a process.
     * @param arrivals - Integer array holding all arrival times for a process.
     * @param bursts - Integer array holding all CPU burst times.
     * @return Arraylist of all waiting times.
     */

    public ArrayList<Integer> waitingTime(int[] exitTimes, int[] arrivals, int[] bursts){
       for(int i = 0; i < exitTimes.length; i ++){
           waitTimes.add(exitTimes[i] - arrivals[i] - bursts[i]);
       }
       return waitTimes;
    }

    /**
     *  Calculates the turnaround time for a process.
     * @param exitTimes - Integer array holding all exit times.
     * @param arrivals- Integer array holding all arrival times.
     * @return An ArrayList that holds all turn around times.
     */
    public ArrayList<Integer> turnTime(int[] exitTimes, int[] arrivals){

        for(int i = 0; i < exitTimes.length; i++){
            turnTimes.add(exitTimes[i] - arrivals[i]);
        }

        return turnTimes;
    }

}
