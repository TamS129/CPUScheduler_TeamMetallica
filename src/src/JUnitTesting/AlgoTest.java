package JUnitTesting;

import org.junit.Test;
import Algorithms.*;
import Test.*;

import java.util.ArrayList;
import java.util.Map;

public class AlgoTest{
    ArrayList testProcessList;
    Map<String,Integer> responseTimes;
    Map<String,Integer> ttTimes;
    Map<String, Integer> waitTimes;

    Map<String, Integer> expectedRT;
    Map<String, Integer> expectedTT;
    Map<String, Integer> getExpectedTT;


    public void createProcessTest(){
        Generator gen = new Generator(0);

        String title1 = "P1";
        int[] processTest1 = {4,10,6,3,5,10,6};
        gen.generateProcess(title1,processTest1,1);

        String title2 = "P2";
        int[] processTest2 = {10,5,4,8,9};
        gen.generateProcess(title2, processTest2,3);

        String title3 = "P3";
        int[] processTest3 ={9,13,6,3,16,11,10};
        gen.generateProcess(title3, processTest3,2);

        testProcessList = gen.getProcesses();




    }
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

        //Expected Results:
        //expectedRT.put(); information to be put here.
        //expectedTT.put(); information to put here
        //expectedWait.put(); information to be put here.




    }

    public void testFCFSNonPre(){
        createProcessTest();
        FCFSNonPre test = new FCFSNonPre();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        //Expected Results:
        //expectedRT.put(); information to be put here.
        //expectedTT.put(); information to put here
        //expectedWait.put(); information to be put here.



    }

    public void testMFQ(){
        createProcessTest();
        MFQ test = new MFQ();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

        //Expected Results:
        //expectedRT.put(); information to be put here.
        //expectedTT.put(); information to put here
        //expectedWait.put(); information to be put here.


    }

    public void testMQ(){
        createProcessTest();
        MQ test = new MQ();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);
    }

    public void testPrioritySch(){
        createProcessTest();
        PrioritySch test = new PrioritySch();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);
    }

    public void testSJF(){
        createProcessTest();
        SJF test = new SJF();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

    }

    public void testSRTP(){

        createProcessTest();
        PrioritySch test = new PrioritySch();
        Outputer testOut = new Outputer();

        test.runAlgo(testProcessList);
        AlgoResult testResults = test.getResults();
        responseTimes = testOut.getResponseTimes(testResults);
        waitTimes = testOut.getWaitingTimes(testResults);
        ttTimes = testOut.getTurnaroundTimes(testResults);

    }
}