package Test;

import Algorithms.*;

import java.util.ArrayList;

/**
 * Class implementation for Executor which runs all algorithms against a List of SProcesses
 *
 * @author KRB/CSB340
 * @version 1.1
 */
public class Executor {
    SchedulerAlgorithm[] algorithms;
    AlgoResult[] results;

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

    public void execute(ArrayList<SProcess> SProcesses) {
        for(int i = 0; i < algorithms.length; i++) {
            results[i] = algorithms[i].runAlgo(SProcesses);
        }
    }

    public void setResults(AlgoResult[] results) {
        this.results = results;
    }

    public AlgoResult[] getResults() {
        return results;
    }

    public static void main(String[] args) {
        Executor myExec = new Executor();
        myExec.execute(new Generator<>().getProcesses());

        Outputer outputer = new Outputer(myExec);
        outputer.printResults();
    }
}
