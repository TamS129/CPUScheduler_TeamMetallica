package JUnitTesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;
import Algorithms.*;
import Test.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AlgoTest{
    ArrayList testProcessList;
    Map<String,Integer> responseTimes;
    Map<String,Integer> ttTimes;
    Map<String, Integer> waitTimes;


    /**
     * Creates a new CPU Processes on a smaller scale that will be used to test our Algorithms.
     */
    public void createProcessTest(){
        Generator gen = new Generator(0);

        String title1 = "P1";
        int[] processTest1 = {4,10,6,3,5};
        gen.generateProcess(title1,processTest1,1);

        String title2 = "P2";
        int[] processTest2 = {10,5,4,8,9};
        gen.generateProcess(title2, processTest2,3);

        String title3 = "P3";
        int[] processTest3 ={9,13,6,3,16};
        gen.generateProcess(title3, processTest3,2);

        testProcessList = gen.getProcesses();

    }

    /**
     * Testing method that checks if our Round Robin Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testRR() {
        createProcessTest();
        RR test = new RR();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        // Initialize expected results maps
        Map<String, Integer> expectedRRRT = new HashMap<>();
        Map<String, Integer> expectedRRWT = new HashMap<>();
        Map<String, Integer> expectedRRTT = new HashMap<>();

        //Expected Results:
        expectedRRRT.put("P1",0);
        expectedRRRT.put("P2",4);
        expectedRRRT.put("P3",9);

        expectedRRTT.put("P1",46);
        expectedRRTT.put("P2",56);
        expectedRRTT.put("P3",72);

        expectedRRWT.put("P1",31);
        expectedRRWT.put("P2",33);
        expectedRRWT.put("P3",41);

       Assert.assertEquals(expectedRRRT, responseTimes);
       Assert.assertEquals(expectedRRWT, waitTimes);
       Assert.assertEquals(expectedRRTT, ttTimes);

        responseTimes.clear();
        waitTimes.clear();
        ttTimes.clear();

    }

    /**
     * Testing method that checks if our FCFS Non-Preemptive Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testFCFSNonPre(){
        createProcessTest();
        FCFSNonPre test = new FCFSNonPre();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        // Initialize expected results maps
        Map<String, Integer> expectedFCRT = new HashMap<>();
        Map<String, Integer> expectedFCWT = new HashMap<>();
        Map<String, Integer> expectedFCTT = new HashMap<>();

        expectedFCRT.put("P1",0);
        expectedFCRT.put("P2",4);
        expectedFCRT.put("P3",14);

        expectedFCTT.put("P1",34);
        expectedFCTT.put("P2",53);
        expectedFCTT.put("P3",69);

        expectedFCWT.put("P1",19);
        expectedFCWT.put("P2",30);
        expectedFCWT.put("P3",38);

        Assert.assertEquals(expectedFCRT, responseTimes);
        Assert.assertEquals(expectedFCWT, waitTimes);
        Assert.assertEquals(expectedFCTT, ttTimes);

        responseTimes.clear();
        waitTimes.clear();
        ttTimes.clear();
    }

    /**
     * Testing method that checks if our MFQ Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testMFQ(){
//        createProcessTest();
//        MFQ test = new MFQ();
//        Outputer testOut = new Outputer();
//
//        test.runAlgo(testProcessList);
//        AlgoResult testResults = test.getResults();
//        responseTimes = testOut.getResponseTimes(testResults);
//        waitTimes = testOut.getWaitingTimes(testResults);
//        ttTimes = testOut.getTurnaroundTimes(testResults);

        //Expected Results:
        //expectedRT.put(); information to be put here.
        //expectedTT.put(); information to put here
        //expectedWait.put(); information to be put here.


    }

    /**
     * Testing method that checks if our MQ Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testMQ(){
//        createProcessTest();
//        MQ test = new MQ();
//        Outputer testOut = new Outputer();
//
//        test.runAlgo(testProcessList);
//        AlgoResult testResults = test.getResults();
//        responseTimes = testOut.getResponseTimes(testResults);
//        waitTimes = testOut.getWaitingTimes(testResults);
//        ttTimes = testOut.getTurnaroundTimes(testResults);
    }

    /**
     * Testing method that checks if our Priority Scheduler Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testPrioritySch(){
        createProcessTest();
        PrioritySch test = new PrioritySch();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        // Initialize expected results maps
        Map<String, Integer> expectedPSRT = new HashMap<>();
        Map<String, Integer> expectedPSWT = new HashMap<>();
        Map<String, Integer> expectedPSTT = new HashMap<>();

        expectedPSRT.put("P1",0);
        expectedPSRT.put("P2",13);
        expectedPSRT.put("P3",4);

        expectedPSTT.put("P1",28);
        expectedPSTT.put("P2",82);
        expectedPSTT.put("P3",53);

        expectedPSWT.put("P1",13);
        expectedPSWT.put("P2",59);
        expectedPSWT.put("P3",22);

        Assert.assertEquals(expectedPSRT, responseTimes);
        Assert.assertEquals(expectedPSWT, waitTimes);
        Assert.assertEquals(expectedPSTT, ttTimes);

        responseTimes.clear();
        waitTimes.clear();
        ttTimes.clear();
    }

    /**
     * Testing method that checks if our SJF Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testSJF(){
        createProcessTest();
        SJF test = new SJF();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        // Initialize expected results maps
        Map<String, Integer> expectedSJFRT = new HashMap<>();
        Map<String, Integer> expectedSJFWT = new HashMap<>();
        Map<String, Integer> expectedSJFTT = new HashMap<>();

        expectedSJFRT.put("P1",0);
        expectedSJFRT.put("P2",13);
        expectedSJFRT.put("P3",4);

        expectedSJFTT.put("P1",44);
        expectedSJFTT.put("P2",69);
        expectedSJFTT.put("P3",60);

        expectedSJFWT.put("P1",29);
        expectedSJFWT.put("P2",23);
        expectedSJFWT.put("P3",31);

        Assert.assertEquals(expectedSJFRT, responseTimes);
        Assert.assertEquals(expectedSJFWT, waitTimes);
        Assert.assertEquals(expectedSJFTT, ttTimes);

        responseTimes.clear();
        waitTimes.clear();
        ttTimes.clear();
    }

    /**
     * Testing method that checks if our SRTF Algorithm returns the correct response, wait, and
     * turn around times.
     */
    @Test
    public void testSRTF(){

        createProcessTest();
        PrioritySch test = new PrioritySch();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        // Initialize expected results maps
        Map<String, Integer> expectedSRRT = new HashMap<>();
        Map<String, Integer> expectedSRWT = new HashMap<>();
        Map<String, Integer> expectedSRTT = new HashMap<>();

        expectedSRRT.put("P1",0);
        expectedSRRT.put("P2",4);
        expectedSRRT.put("P3",14);

        expectedSRTT.put("P1",34);
        expectedSRTT.put("P2",53);
        expectedSRTT.put("P3",69);

        expectedSRWT.put("P1",19);
        expectedSRWT.put("P2",30);
        expectedSRWT.put("P3",38);

        Assert.assertEquals(expectedSRRT, responseTimes);
        Assert.assertEquals(expectedSRWT, waitTimes);
        Assert.assertEquals(expectedSRTT, ttTimes);

        responseTimes.clear();
        waitTimes.clear();
        ttTimes.clear();

    }
}