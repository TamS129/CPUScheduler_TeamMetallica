package Algorithms;

import Test.AlgoResult;
import Test.SProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MFQ implements SchedulerAlgorithm{

    AlgoResult result;

    public MFQ() {
        this.result = new AlgoResult(getName());
    }
    /**
     * Method to run a list of processes through a scheduling algorithm
     *
     * @param processes List of processes
     * @return An AlgoResult object, a summary of the findings
     */
    public AlgoResult runAlgo(ArrayList<SProcess> processes) {
        int currentTime = 0;

        Queue<SProcess> queue1 = new LinkedList<>(processes);
        Queue<SProcess> queue2 = new LinkedList<>();
        Queue<SProcess> queue3 = new LinkedList<>();
        Queue<SProcess> ioQueue = new LinkedList<>();

        while (!queue1.isEmpty() || !queue2.isEmpty() || !queue3.isEmpty() || !ioQueue.isEmpty()) {
            handleIOQueue(ioQueue, queue1, currentTime);

            if (!queue1.isEmpty()) {
                SProcess currentProcess = queue1.poll();
                int executedTime = executeProcess(currentProcess, 5, currentTime);
                currentTime += executedTime;
                if (currentProcess.getBurstTimes()[currentProcess.getCurrCPUindex()] > 0) {
                    queue2.add(currentProcess);
                } else {
                    finalizeProcess(currentProcess, currentTime, ioQueue);
                }
            } else if (!queue2.isEmpty()) {
                SProcess currentProcess = queue2.poll();
                int executedTime = executeProcess(currentProcess, 10, currentTime);
                currentTime += executedTime;
                if (currentProcess.getBurstTimes()[currentProcess.getCurrCPUindex()] > 0) {
                    queue3.add(currentProcess);
                } else {
                    finalizeProcess(currentProcess, currentTime, ioQueue);
                }
            } else if (!queue3.isEmpty()){
                SProcess currentProcess = queue3.poll();
                int executedTime = executeProcess(currentProcess, Integer.MAX_VALUE, currentTime);
                currentTime += executedTime;
                finalizeProcess(currentProcess, currentTime, ioQueue);
            } else {
                if (!ioQueue.isEmpty()) {
                    int minReturnTime = Integer.MAX_VALUE;
                    for (SProcess process : ioQueue) {
                        if (process.getReturnTime() < minReturnTime) {
                            minReturnTime = process.getReturnTime();
                        }
                    }
                    currentTime = minReturnTime;
                    handleIOQueue(ioQueue, queue1, currentTime);
                } else {
                    currentTime++;
                }
            }
        }
        return result;
    }

    /**
     * Method to handle the IO Queue
     * @param ioQueue Queue of processes in IO
     * @param queue1 Queue of processes in the first level
     * @param currentTime Current time
     */
    private void handleIOQueue(Queue<SProcess> ioQueue, Queue<SProcess> queue1, int currentTime) {
        Queue<SProcess> tempQueue = new LinkedList<>();
        while (!ioQueue.isEmpty()) {
            SProcess currentProcess = ioQueue.poll();
            if (currentProcess.getReturnTime() <= currentTime) {
                queue1.add(currentProcess);
            } else {
                tempQueue.add(currentProcess);
            }
        }
        ioQueue.addAll(tempQueue);
    }

    /**
     *
     * @param process
     * @param quantum
     * @param currentTime
     * @return
     */
    private int executeProcess(SProcess process, int quantum, int currentTime) {
        int remainingBurst = process.getBurstTimes()[process.getCurrCPUindex()];
        int executedTime = Math.min(remainingBurst, quantum);
        process.setStartTime(currentTime);
        process.setStopTime(currentTime + executedTime);
        result.getCPUactivity().add(new AlgoResult.Pair(process.getStartTime(), process.getStopTime()));
        result.getExecutionOrder().add(process.getTitle());
        process.getBurstTimes()[process.getCurrCPUindex()] -= executedTime;
        return executedTime;
    }

    /**
     * Method to finalize a process
     * @param process Process to finalize
     * @param currentTime Current time
     * @param ioQueue Queue of processes in IO
     */
    private void finalizeProcess(SProcess process, int currentTime, Queue<SProcess> ioQueue){
        if (process.getCurrCPUindex() >= process.getBurstTimes().length - 1) {
            process.setExitTime(currentTime);
        } else {
            int ioBurst = process.getBurstTimes()[process.getCurrIOindex()];
            process.setReturnTime(currentTime + ioBurst);
            process.setCurrCPUindex(process.getCurrCPUindex() + 2);
            process.setCurrIOindex(process.getCurrIOindex() + 2);
            ioQueue.add(process);
        }
    }

    /**
     * Method to return the name of the scheduling algorithm
     *
     * @return Name of Scheduling algo
     */
    @Override
    public String getName() {
        return "Multilevel Feedback Queue";
    }

    /**
     * Method to result AlgoResult object that has final order of execution
     * @return AlgoResult object
     */
    @Override
    public AlgoResult getResults(){
        return result;
    }

}
