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

public class RR implements SchedulerAlgorithm {

    AlgoResult result;

    public RR(boolean showOutput){
        this.result = new AlgoResult(getName(), showOutput);
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
            ioWait = remainingIoWait;

            if (!ready.isEmpty()) {
                SProcess currentProcess = ready.poll();
                int[] bursts = currentProcess.getBurstTimes();
                int cpuBurst = bursts[currentProcess.getCurrCPUindex()];
                currentProcess.setStartTime(currentTime);

                if (cpuBurst <= tq) {
                    // Update return time based on sum of CPU and I/O burst/
                    if (currentProcess.getCurrCPUindex() != bursts.length - 1) {
                        currentProcess.setReturnTime(currentTime + cpuBurst + bursts[currentProcess.getCurrIOindex()]);
                    }
                    currentTime += cpuBurst;


                    currentProcess.setStopTime(currentTime);

                    result.getCPUactivity().add(new Pair(currentProcess.getStartTime(), currentProcess.getStopTime()));
                    result.getExecutionOrder().add(currentProcess.getTitle());

                    Queue<SProcess> currentReadyQueue = new LinkedList<>();
                    for (SProcess process : ready) {
                        int[] burstTimes = process.getBurstTimes();
                        SProcess newProcess = new SProcess(process.getTitle(), burstTimes, process.getPriorityLevel());
                        newProcess.setCurrCPUindex(process.getCurrCPUindex());
                        currentReadyQueue.add(newProcess);
                    }
                    result.getReadyQueueActivity().add(currentReadyQueue);

                    Queue<SProcess> currentIOQueue = new LinkedList<>();
                    for (SProcess process : ioWait) {
                        SProcess newProcess = new SProcess(process.getTitle(), new int[0], process.getPriorityLevel());
                        newProcess.setCurrIOindex(process.getCurrIOindex());
                        currentIOQueue.add(newProcess);
                    }
                    result.getIoQueueActivity().add(currentIOQueue);



                    if (currentProcess.getCurrCPUindex() < bursts.length - 1) {
                        currentProcess.setCurrCPUindex(currentProcess.getCurrCPUindex() + 2);
                        ioWait.add(currentProcess);
                    } else {
                        currentProcess.setExitTime(currentTime);
                    }

                } else {

                    currentTime += tq;
                    currentProcess.setStopTime(currentTime);
                    result.getCPUactivity().add(new Pair(currentProcess.getStartTime(), currentProcess.getStopTime()));
                    result.getExecutionOrder().add(currentProcess.getTitle());

                    Queue<SProcess> currentReadyQueue = new LinkedList<>();
                    for (SProcess process : ready) {
                        int[] burstTimes = process.getBurstTimes();
                        SProcess newProcess = new SProcess(process.getTitle(), burstTimes, process.getPriorityLevel());
                        newProcess.setCurrCPUindex(process.getCurrCPUindex());
                        currentReadyQueue.add(newProcess);
                    }
                    result.getReadyQueueActivity().add(currentReadyQueue);

                    Queue<SProcess> currentIOQueue = new LinkedList<>();
                    for (SProcess process : ioWait) {
                        SProcess newProcess = new SProcess(process.getTitle(), new int[0], process.getPriorityLevel());
                        newProcess.setCurrIOindex(process.getCurrIOindex());
                        currentIOQueue.add(newProcess);
                    }
                    result.getIoQueueActivity().add(currentIOQueue);

                    bursts[currentProcess.getCurrCPUindex()] -= tq;
                    ready.add(currentProcess);

                }

            }
            else {
                int fastForward = ioWait.peek().getReturnTime();
                for(SProcess process : ioWait){
                    if(process.getReturnTime() < fastForward){
                        fastForward = process.getReturnTime();

                    }
                }
                currentTime = fastForward;
            }
        }

        return result;
    }

    /**
     * Getter method that returns the start time.
     * @return Current start time of our process.
     */
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
