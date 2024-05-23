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

            if (!ready.isEmpty()) {

                SProcess currentProcess = ready.poll();
                int[] bursts = currentProcess.getBurstTimes();
                int cpuBurst = bursts[currentProcess.getCurrCPUindex()];
                currentProcess.setStartTime(currentTime);

                if (cpuBurst <= tq) {
                    currentTime += cpuBurst;
                    currentProcess.setCurrCPUindex(currentProcess.getCurrCPUindex() + 2);

                    currentProcess.setStopTime(currentTime);

                    if (currentProcess.getCurrCPUindex() < bursts.length) {

                        ioWait.add(currentProcess);

                    }
                    else {

                        currentProcess.setExitTime(currentTime);
                    }

                } else {

                    currentTime += tq;
                    bursts[currentProcess.getCurrCPUindex()] -= tq;
                    ready.add(currentProcess);
                    result.getCPUactivity().add(new Pair(currentProcess.getStartTime(), currentProcess.getStopTime()));
                    result.getExecutionOrder().add(currentProcess.getTitle());
                }
            }


            Queue<SProcess> remainingIoWait = new LinkedList<>();
            while (!ioWait.isEmpty()) {

                SProcess ioProcess = ioWait.poll();
                int[] bursts = ioProcess.getBurstTimes();
                int ioBurst = bursts[ioProcess.getCurrIOindex()];

                if (currentTime >= ioBurst) {

                    ioProcess.setCurrIOindex(ioProcess.getCurrIOindex() + 2);
                    ready.add(ioProcess);

                }
                else {

                    remainingIoWait.add(ioProcess);
                }
            }
            ioWait = remainingIoWait;
        }



        // Create AlgoResult object to return
        return result;
    }

    private List<Pair> setCPUActivities(ArrayList<SProcess> processes) {
        List<Pair> cpuActivities = new ArrayList<>();
        for (SProcess process : processes) {
            cpuActivities.add(new Pair(process.getStartTime(), process.getExitTime()));
        }
        return cpuActivities;
    }

    private int getCPUBurstSum(int[] burstTimes) {
        int sum = 0;

        for (int i = 0; i < burstTimes.length; i += 2) {

            sum += burstTimes[i];

        }
        return sum;
    }

    public AlgoResult getResults(){
        System.out.println("Round Robin: ");
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
    public static void main(String[] args) {
        Executor exe = new Executor();
        Generator gen = new Generator<>();
        ArrayList<SProcess> test = gen.getProcesses();
        exe.execute(test);

        for(SProcess print: test){
            for(int i = 0; i < test.size(); i++){
                System.out.println(print.getTitle());

                System.out.println("Start Time: ");
                System.out.println(print.getStartTime());

                System.out.println("Exit Time: ");
                System.out.println(print.getExitTime());

                System.out.println("Response Time");
                System.out.println(print.getReturnTime());

            }

        }
    }
}


