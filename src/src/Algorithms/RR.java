package Algorithms;

import Test.AlgoResult;
import Test.Generator;
import Test.SProcess;

import java.util.ArrayList;

public class RR implements SchedulerAlgorithm{
    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        Generator gen = new Generator<>();
        SProcess list = new SProcess();
        AlgoTimeFun calculate = new AlgoTimeFun();
//        for(SProcess pro){
//
//        }

        return null;

    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return null;
    }
}

class main{
    static public void main(String[] Args){
        Generator test = new Generator<>();
        test.printProcesses();

    }

}
