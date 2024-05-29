package Algorithms;

import Test.AlgoResult;
import Test.AlgoResult.Pair;
import Test.Executor;
import Test.Generator;
import Test.SProcess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RR implements SchedulerAlgorithm{

    AlgoResult result;

    public RR(){
        this.result = new AlgoResult(getName());
    }

    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    @Override
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        Queue<SProcess> ready = new LinkedList<>(processes);
        Queue<SProcess> ioWait = new LinkedList<>();

        int tq = 5;
        int currentTime = 0;

        while (!ready.isEmpty() || !ioWait.isEmpty()) {

            Queue<SProcess> remainingIoWait = new LinkedList<>();
            while (!ioWait.isEmpty()) {
                SProcess ioProcess = ioWait.poll();
                int[] bursts = ioProcess.getBurstTimes();
                int ioBurst = bursts[ioProcess.getCurrIOindex()];

                if (currentTime >= ioProcess.getReturnTime()) {

                    ready.add(ioProcess);

                } else {
                    remainingIoWait.add(ioProcess);
                }
            }

            if (!ready.isEmpty()) {

                SProcess currentProcess = ready.poll();
                int[] bursts = currentProcess.getBurstTimes();
                int cpuBurst = bursts[currentProcess.getCurrCPUindex()];
                currentProcess.setStartTime(currentTime);

                if (cpuBurst <= tq) {
                    // Update return time based on sum of CPU and I/O burst
                    currentProcess.setReturnTime(currentTime + cpuBurst + bursts[currentProcess.getCurrIOindex()]);
                    currentTime += cpuBurst;
                    currentProcess.setCurrCPUindex(currentProcess.getCurrCPUindex() + 2);

                    currentProcess.setStopTime(currentTime);

                    result.getCPUactivity().add(new Pair(currentProcess.getStartTime(), currentProcess.getStopTime()));
                    result.getExecutionOrder().add(currentProcess.getTitle());

                    if (currentProcess.getCurrCPUindex() < bursts.length - 1) {
                        ioWait.add(currentProcess);
                    } else {
                        currentProcess.setExitTime(currentTime);
                    }

                } else {

                    currentTime += tq;
                    bursts[currentProcess.getCurrCPUindex()] -= tq;
                    ready.add(currentProcess);

                }

            }
            else{
                int fastForward = ioWait.peek().getReturnTime();
                for(SProcess process : ioWait){
                    if(process.getReturnTime() < fastForward){
                        fastForward = process.getReturnTime();

                    }
                }
                currentTime = fastForward;
            }

            ioWait = remainingIoWait;
        }

        return result;
    }


    @Override
    public AlgoResult getResults(){
        return result;
    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return "Round Robin (FCFS)";
    }

}


