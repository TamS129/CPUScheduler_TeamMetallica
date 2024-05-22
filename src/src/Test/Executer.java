package Test;

import Algorithms.*;

import java.util.ArrayList;

public class Executer {
    SchedulerAlgorithm[] algorithms;
    AlgoResult[] results;

    public Executer() {
        this.algorithms = null;
        this.results = new AlgoResult[7];
    }

    public void execute(ArrayList<SProcess> SProcesses) {
        SJF sjf = new SJF();
        sjf.runAlgo(SProcesses);

    }

    public AlgoResult[] getResults() {
        return results;
    }
}
