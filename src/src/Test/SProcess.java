package Test;

/**
 * Class implementation for a Process that needs scheduling
 *
 * @author Team Metallica/CSB340
 * @version 1.6
 */
public class SProcess {
    /** Name of SProcess **/
    private String title;
    private int[] burstTimes;
    private int currCPUindex, currIOindex, exitTime, startTime, stopTime, waitTime, turnTime, repTime, returnTime, priorityLevel;


    /**
     * Constructor that holds the basic information of the Process object title and burstimes.
     */
    public SProcess() {
        this.title = null;
        this.burstTimes = null;
    }

    /**
     * Constructor that holds the total information of the object.
     * @param title - Name of the current Process. Ex: "P1"
     * @param burstTimes - array that holds all of the processes CPU/IO burst times.
     * @param priorityLevel - Holds the priority level of the processes based on a number system.
     */
    public SProcess(String title, int[] burstTimes, int priorityLevel ){
        this.title = title;
        this.burstTimes = burstTimes;
        this.priorityLevel = priorityLevel;
        currCPUindex = 0;
        currIOindex = 1;

        exitTime = -1;
        startTime = -1;
        stopTime = -1;
        returnTime = -1;

        waitTime = -1;
        repTime = -1;
        turnTime = -1;

    }


    /**
     * Getter method that returns the priority level of the current process.
     * @return int priority level of current process.
     */
    public int getPriorityLevel() { return priorityLevel;}

    public void setPriorityLevel(int newPriorityLevel) { this.priorityLevel = newPriorityLevel;}

    /**
     * Getter method that returns the time in which a process returns from a IO wait.
     * @return int, return time of process after IO wait time.
     */
    public int getReturnTime() {
        return returnTime;
    }

    /**
     * Sets a new return time for a current process.
     * @param returnTime Return time of current process.
     */
    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * Returns the time in which the process has finished it's burst.
     * @return exit time of which the process has finished it's burst.
     */
    public int getExitTime() { return exitTime;}

    /**
     * Sets a new Exit time for which a process has finished it's burst.
     * @param exitTime - New exittime of which the process has finished its burst.
     */
    public void setExitTime(int exitTime) { this.exitTime = exitTime;}

    /**
     * Getter method that returns the starting time of which a Process has begun it's CPU burst.
     * @return startTime of current CPU burst.
     */
    public int getStartTime() { return startTime; }

    /**
     * Sets a new start time for current CPU burst.
     * @param startTime - New start time for current CPU burst.
     */
    public void setStartTime(int startTime) { this.startTime = startTime; }

    /**
     * Returns the time when a process finishes its run.
     * @return stop time of current process.
     */
    public int getStopTime() { return stopTime;}

    /**
     * Sets and records the stop time of the current processes end time.
     * @param stopTime - New stop time of the current process.
     */
    public void setStopTime(int stopTime) { this.stopTime = stopTime;}

    /**
     * Gets the current index in what place the current cpu time the process is in.
     * @return current index of current cpu/io time.
     */
    public int getCurrCPUindex(){ return currCPUindex;}

    /**
     * Sets the current cpu index of the current process run.
     * @param index the new current point of the process run.
     */
    public void setCurrCPUindex(int index) { this.currCPUindex = index;}

    /**
     * Gets the current position of the processes in it's I/0 wait time.
     * @return current I/0 wait time of the processes.
     */
    public int getCurrIOindex() { return currIOindex;}

    /**
     * Sets the current position of the I/0 wait time of a process.
     * @param index new I/O current postion of a process.
     */
    public void setCurrIOindex(int index) { this.currIOindex = index;}

    /**
     * Returns an array of the CPU/IO time of a process.
     * @return array of CPU/IO time of a current process.
     */
    public int[] getBurstTimes() {
        return burstTimes;
    }

    /**
     * Returns the title of the current process.
     * @return Title of the current process. Ex: "P1"
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets new bursts times for a current process.
     * @param burstTimes - New burst times for a process.
     */
    public void setBurstTimes(int[] burstTimes) {
        this.burstTimes = burstTimes;
    }

    /**
     * Sets a new title for a processes name.
     * @param title - New title for a process.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the Response time for a process.
     * @return current Response time for a process.
     */
    public int getRepTime() {
        return repTime;
    }

    /**
     * Returns the turnaround time for a current process.
     * @return current turnaround time for a process.
     */
    public int getTurnTime() {
        return turnTime;
    }
    /**
     * Returns the wait time for a current process.
     * @return current wait time for a process.
     */
    public int getWaitTime() {
        return waitTime;
    }

    /**
     * Sets the reponse time for a process.
     * @param repTime - New response time for the current process.
     */
    public void setRepTime(int repTime) {
        this.repTime = repTime;
    }
    /**
     * Sets the turn around tme time for a process.
     * @param turnTime - New turn around  time for the current process.
     */
    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }
    /**
     * Sets the wait time for a process.
     * @param waitTime - New wait time for the current process.
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }


}
