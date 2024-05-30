package Test;

import Algorithms.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Class implementation for Executor which runs all algorithms against a List of SProcesses
 *
 * @author KRB/CSB340
 * @version 1.1
 */
public class Executor {
    /** All the different Scheduling Algorithms available **/
    SchedulerAlgorithm[] algorithms;
    /** Array of AlgoResult from each Scheduling Algorithm **/
    AlgoResult[] results;

    /**
     * Constructor for an Executor Class
     */
    public Executor() {
        this.algorithms = new SchedulerAlgorithm[7];
        algorithms[0] = new SJF();
        algorithms[1] = new FCFSNonPre();
        algorithms[2] = new MFQ();
        algorithms[3] = new PrioritySch();
        algorithms[4] = new RR();
        algorithms[5] = new SRTF();
        algorithms[6] = new MQ();
        this.results = new AlgoResult[7];
    }

    /**
     * Method to execute a list of SProcesses against a Scheduling Algorithm
     */
    public void execute() {
        //TODO: Make fresh array list for each run
        for(int i = 0; i < algorithms.length; i++) {
            Generator mygen = new Generator<>();
            ArrayList<SProcess> list = mygen.getProcesses();
            results[i] = algorithms[i].runAlgo(list);
        }
    }

    /**
     * Method to reset the values of each SProcess in the list for the next Algo
     * @param SProcesses List of SProcesses
     */
    public void resetValues(ArrayList<SProcess> SProcesses){
        for (SProcess temp : SProcesses) {
            temp.setStartTime(-1);
            temp.setReturnTime(-1);
            temp.setExitTime(-1);
            temp.setStopTime(-1);
            temp.setCurrCPUindex(0);
            temp.setCurrIOindex(1);
            temp.setWaitTime(-1);
            temp.setRepTime(-1);
            temp.setTurnTime(-1);
        }
    }

    /**
     * Method to return the Array of AlgoResults after running all algorithms through the list of SProcesses
     * @return AlgoResult Array
     */
    public AlgoResult[] getResults() {
        return results;
    }


    public static void main(String[] args) {
        Executor myExec = new Executor();
        myExec.execute();

        Outputer outputer = new Outputer(myExec);
        outputer.printResults();
        outputer.writeResultsToFile("output.txt");

    }
}
