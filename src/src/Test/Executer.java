package Test;

import Algorithms.*;

import Test.AlgoResult.Pair;
import java.util.ArrayList;

/**
 * Class implementation for Executer which runs all algorithms against a List of SProcesses
 *
 * @author KRB/CSB340
 * @version 1.1
 */
public class Executer {
    SchedulerAlgorithm[] algorithms;
    AlgoResult[] results;

    public Executer() {
        // need to fill algorithms with the ACTUAL ALGOS
        this.algorithms = null;
        this.results = new AlgoResult[7];
    }

    public void execute(ArrayList<SProcess> SProcesses) {
        SJF sjf = new SJF();
        sjf.runAlgo(SProcesses);
        results[0] = sjf.getMyResult();
    }

    public void setResults(AlgoResult[] results) {
        this.results = results;
    }

    public AlgoResult[] getResults() {
        return results;
    }

    public static void main(String[] args) {
        Executer myExec = new Executer();
        myExec.execute(new Generator<>().getProcesses());
        AlgoResult result = myExec.getResults()[0];
        for (int i = 0; i < result.getExecutionOrder().size(); i++) {
            System.out.println(result.getExecutionOrder().get(i));
            Pair myPair = result.getCPUactivity().get(i);
            System.out.println("Start time: " + myPair.getStartTime() + "\nStop time: " + myPair.getStopTime());
        }
        Outputer outputer = new Outputer(myExec);
        outputer.printResults();
    }
}
