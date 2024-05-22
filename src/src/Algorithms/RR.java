package Algorithms;

import Test.AlgoResult;
import Test.AlgoResult.Pair;
import Test.Executer;
import Test.Generator;
import Test.SProcess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RR implements SchedulerAlgorithm{
    AlgoResult result = new AlgoResult();

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
        AlgoTimeFun timeFun = new AlgoTimeFun();

        while (!ready.isEmpty() || !ioWait.isEmpty()) {

            if (!ready.isEmpty()) {

                SProcess currentProcess = ready.poll();
                int[] bursts = currentProcess.getBurstTimes();
                int cpuBurst = bursts[currentProcess.getCurrCPUindex()];

                if (cpuBurst <= tq) {
                    currentTime += cpuBurst;
                    currentProcess.setCurrCPUindex(currentProcess.getCurrCPUindex() + 2);


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

        // Calculate times for all processes
        ArrayList<Integer> waitTimes = new ArrayList<>();
        ArrayList<Integer> turnTimes = new ArrayList<>();
        ArrayList<Integer> responseTimes = new ArrayList<>();

        for (SProcess process : processes) {

            int waitTime = timeFun.waitingTime(process.getExitTime(), process.getStartTime(), getCPUBurstSum(process.getBurstTimes()));
            int turnTime = timeFun.turnTime(process.getExitTime(), process.getStartTime());
            int responseTime = timeFun.responseTime(process.getStartTime(), process.getStartTime());

            waitTimes.add(waitTime);
            turnTimes.add(turnTime);
            responseTimes.add(responseTime);

            process.setWaitTime(waitTime);
            process.setTurnTime(turnTime);
            process.setRepTime(responseTime);
        }

        // Create AlgoResult object to return


        return result;
    }

    private List<Pair> getCPUActivities(ArrayList<SProcess> processes) {
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
        return null;
    }
    public static void main(String[] args) {
        Executer exe = new Executer();
        Generator gen = new Generator<>();
        ArrayList<SProcess> test = gen.getProcesses();
        exe.execute(test);

        for(SProcess print: test){
            for(int i = 0; i < test.size(); i++){
                System.out.println(print.getTitle());

                System.out.println("Start Time: ");
                System.out.println(print.getExitTime());

                System.out.println("Wait Time: ");
                System.out.println(print.getWaitTime());

                System.out.println("Response time: ");
                System.out.println(print.getRepTime());

                System.out.println("Turnaround time: ");
                System.out.println(print.getTurnTime());
                System.out.println();
            }

        }
    }
}


