package Test;

import java.util.ArrayList;
import java.util.Arrays;

// Coded by Tamara Slone
public class Generator<T> {
    ArrayList<SProcess> processes = new ArrayList<>();

    /**
     * Constructor made to use for testing purposes.
     * @param testNum - Integer just to signify for testing.
     */
    public Generator(int testNum){
        testNum = 0;
        //Generator just for Testing purposes.
    }

    /**
     * Constructor that generates Processes to be put through our Executor.
     */
    public Generator(){

        //Process 1
        String title1 = "P1";
        int[] burst1 = {5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4};
        generateProcess(title1, burst1, 3);

        //Process 2
        String title2 = "P2";
        int[] burst2 = {4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8};
        generateProcess(title2, burst2, 6);

        //Process 3
        String title3 = "P3";
        int[] burst3 = {8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6};
        generateProcess(title3, burst3, 5);

        //Process 4
        String title4 = "P4";
        int[] burst4 = {3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3};
        generateProcess(title4, burst4, 4);

        //Process 5
        String title5 = "P5";
        int[] burst5 = {16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4};
        generateProcess(title5, burst5, 1);

        //Process 6
        String title6 = "P6";
        int[] burst6 = {11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8};
        generateProcess(title6, burst6, 2);

        //Process 7
        String title7 = "P7";
        int[] burst7 = {14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10};
        generateProcess(title7, burst7, 8);

        //Process 8
        String title8 = "P8";
        int[] burst8 = {4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6};
        generateProcess(title8, burst8, 7);


    }

    /**
     * Gathers information for our processes and places it into the SProcesses Object class. Once in the SProcesss
     * Object class it's added into an ArrayList of processes.
     * @param title- Title of the current process. Ex. "P1"
     * @param arr - Array that holds our CPU/IO burst times.
     * @param priorityLevel - Priority leveling that marks the priority of that process.
     */
    public void generateProcess(String title, int[] arr, int priorityLevel){
        SProcess newSProcess = new SProcess(title, arr, priorityLevel);
        processes.add(newSProcess);
    }

    /**
     * Returns the arrayList of processes.
     * @return processes ArrayList
     */
    public ArrayList<SProcess> getProcesses() {
        return processes;
    }

}
