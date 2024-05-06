Summary

In this project, you will be working with your team to implement 8 CPU scheduling algorithms. If your group only has 3 members, you can implement 6 CPU scheduling algorithms. You will not implement First Come, First Serve Preemptive and you may choose not to implement one of the Shortest Job First (preemptive or non-preemptive). 

    First Come, First Serve (FCFS)
        Nonpreemptive
        Preemptive (Whoever choose this one will not implement it but rather will look up what FCFS with preemption is and explain what it transforms into and why in the report. See more on this below in the Report section)
    Shortest Job First (SJF)
        Nonpreemptive
        Preemptive: Shortest Remaining Time First (SRTF)
    Priority Scheduling
    Round Robin (with FCFS)
        Time Quantum: 5
    Multilevel Queue
        Queue 1: RR TQ = 4, Queues 2: FCFS
            Processes 1-4 are in Queue 1 and Processes 5-8 are in Queue 2
            CPU time is divided up by either doing Fixed Priority Preemptive Scheduling Method or Time Slicing. Information about both of these can be found in our slides and book. Also here is a link that discusses them: MLQ 

            Links to an external site.
                With Fixed Priority Preemptive Scheduling Method, one queue finishes completely before the other can go. This can lead to starvation.
                With Time Slicing, CPU time divided by the queues is done by offering 80% to the first queue and 20% to the second queue, round robin style.
                More information can be found on this in your slides and in your book in section 6.3 under Multilevel Queue
                Please indicate in your code which one you used.
    Multilevel Feedback Queue:
        Three Queues used (see below)

 

You will create your own GitHub repository for this project. Each sorting algorithm should be in its own java file. Each algorithm would be in a different class.

 
Notes on MLFQ

MLFQ has absolute priority in higher queues. It uses 3 queues:

1. Queue 1: uses RR scheduling with TQ = 5

2. Queue 2: uses RR scheduling with TQ = 10

3. Queue 3: uses FCFS

All processes enter first queue 1. If time quantum (TQ) expires before CPU burst is complete, the process is downgraded to next lower priority queue. Processes are not downgraded when preempted by a higher queue level process. Once a process has been downgraded, it will not be upgraded.

 
Assumptions

    All processes are activated at time 0.
    Assume that no process waits on I/O devices.
    After completing an I/O event, a process is transferred to the ready queue.
    Waiting time is accumulated while a process waits in the ready queue.
    Turnaround time is a total of (Waiting time) + (CPU burst time) + (I/O time)
    Response time is the first measure of waiting time from arrival at time 0 until the first time on the CPU.

 

Process Data: 

Process Pattern {CPU burst, I/O time, CPU burst, I/O time, CPU burst, I/O time,…….., last CPU burst}

 

P1 {5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4}

P2 {4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8}

P3 {8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6}

P4 {3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3}

P5 {16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4}

P6 {11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8}

P7 {14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10}

P8 {4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6}

 

For the Priority Algorithm, the priorities are:

For {P1, P2, P3, P4, P5, P6, P7, P8} = {3, 6, 5, 4, 1, 2, 8, 7}

 
Part 1: Designing the Experiment

    Design a UML document for your software's organization.
    Decide which team members will be responsible for which algorithms.
    Decide on who will be responsible for utility code outside of the CPU scheduling algorithms.
    Decide on how plots will be created and who will be responsible for those (one person, or each person is responsible for their plots).

 
Part 2: Java Implementation

Implement the algorithms previously mentioned in Java and perform the experiments with the chosen input lists. Results should be evaluated in terms of the metrics.

The implementation of this is largely up to you. Here are a few things I look for in well-designed implementations:

    Follows principles of OOP: Inheritance, Polymorphism, Abstraction, Encapsulation
        I'm not checking for each one of these. Obviously, there are situations where none or very few will be used and there are situations where all may be used. Use your discretion.
    Well-organized project structure.
    Use of Interfaces.
        For any classes where it will be relevant, use of interfaces is a great idea. 

 
Part 3: Output and Testing

    You will now be required to have Junit tests for each scheduling algorithm. Ensure they test using the data presented above. Additional tests will be helpful to determine the robustness of the algorithms, but aren't required.
    There will also be output required to be printed to a text file. 
        For each context switch (process switched) please include a Boolean flag that allows developer to toggle this output off and on.
            Current execution time.
            Running process.
            The Ready queue, with the CPU burst time for each process.
            The Processes in I/O with the remaining time for every process for its I/O burst completion.
            Indicate when a process has completed its total execution.
        At the end of each simulated CPU scheduling algorithm:
            Total time needed to complete all 8 processes.
            CPU utilization - [%] (U).
            Waiting times for each process and the average waiting time for all processes (Tw)
            Turnaround time for each process and the average turnaround time.(Ttr)
            Response time for each process and the average response time (Tr).

 

The following is an excellent example of how to print into a text file for each algorithm. This is for FCFS-Nonpre. 

Note: Despite having results for FCFS-Nonpre, I expect to see it implemented just the same as the other ones.

Sample FCFS-Nonpre text file

Download Sample FCFS-Nonpre text file

 
Part 4: Report

Your report will contain the following:

    Table of Contents
    Introduction
        Discuss the CPU scheduling algorithms being implemented. Provide a paragraph explaining the algorithm itself.
        For anyone in a 4-person group who chose to do FCFS Preemptive: Your part should be longer, including 2-3 paragraphs. Discuss what FCFS is, what preemption is and what happens when attempting to create a preemptive FCFS algorithm. How does it transform? What does it become or does it not become anything? Include a paragraph discussing if you agree with what it is traditionally considered to become and why or why not? Is that a way it could stay FCFS Preemptive without transforming?
    UML diagram
    Well-presented final results including tables, plots and discussion

    Discussion and Tables (see below) for:
        U (CPU utilization)
        Tw (waiting times)
        Ttr (turnaround times)
        Rt(response times)
    For all processes and averages for each algorithm (see FCFS below)
        Compare results of the algorithms.

 
	

FCFS - NonPre
	

FCFS - Preemp
	

SJF - NonPre
	

SRTF
	

Priority
	

RR
	

MLQ
	

MLFQ

CPU utilization
	

85.34%
		

 
	

 
	

 
	

 
	

 
	

 

Avg Waiting time (Tw)
	

185.25
		

 
	

 
	

 
	

 
	

 
	

 

Avg Turnaround time (Ttr)
	

521.37
		

 
	

 
	

 
	

 
	

 
	

 

Avg Response time (Tr)
	

24.37
		

 
	

 
	

 
	

 
	

 
	

 

 

    The plots created will be comparing each measurement above. So you will have a total of 4 plots.
    Plots:
        CPU Utilization: data for algorithms.
        Average Waiting Time: data for all algorithms
        Average Turnaround Time: data for all algorithms
        Average Response Time: data for all algorithms

 

The second set of tables will follow the format seen below. You will have a sub-table for each algorithm. Three are shown here with data for FCFS Nonpreemptive.

 
	

SJF   CPU utilization:
	

FCFS NonPre: CPU utilization:  85.34%
	

MLFQ CPU utilization:

 
	

Tw
	

Ttr
	

Tr
	

 
	

Tw
	

Ttr
	

Tr
	

 
	

Tw
	

Ttr
	

Tr
	

 

P1
	

 
	

 
	

 
	

170
	

395
	

0
	

 
	

 
	

 

P2
	

 
	

 
	

 
	

164
	

591
	

5
	

 
	

 
	

 

P3
	

 
	

 
	

 
	

165
	

557
	

9
	

 
	

 
	

 

P4
	

 
	

 
	

 
	

164
	

648
	

17
	

 
	

 
	

 

P5
	

 
	

 
	

 
	

221
	

530
	

20
	

 
	

 
	

 

P6
	

 
	

 
	

 
	

230
	

445
	

36
	

 
	

 
	

 

P7
	

 
	

 
	

 
	

184
	

512
	

47
	

 
	

 
	

 

P8
	

 
	

 
	

 
	

184
	

493
	

61
	

 
	

 
	

 
Avg
	

 
	

 
	

 
	

185.25
	

521.37
	

24.37
	

 
	

 
	

 

 

Discussion should be spent comparing algorithm performance and deciding on the best solution to implement. Why its the best solution and why not should also be discussed.

 
Performance Review

Note: there will be no performance reviews on teammates for this project, but if it comes to my attention, I reserve the right to adjust your grade based on your contribution to the final project.

 

 
Attribution Requirement

You are required to indicate who completed what in this project. 

    In the report, create a listing of who completed what.
    In the code, include a Javadoc comment that indicates who completed a portion of code.

 
Project Board

You are required to use a Github project board to track your tasks with this project. You will meet with your teammate, map out the tasks and your issues and pull requests are to be added to the project board (see below for a link on this). You will review this project board during your Milestone Meeting meeting with the instructor or TA. Your project board will be reviewed by the grader using the activity view.

https://docs.github.com/en/enterprise-server@3.4/issues/organizing-your-work-with-project-boards/tracking-work-with-project-boards/adding-issues-and-pull-requests-to-a-project-board

Links to an external site.

 
Milestone Meetings

The topic of CPU Scheduling is revealed  5/3. The due date for this project is 5/28. That means there is a little under a month to complete it. About three and a half weeks. During those weeks, you will be required to meet virtually with an instructor or TA and review your progress. Note: There are assignments located in Canvas that will update your calendar to remind you of these dates. This meeting shouldn't take longer than 10 minutes.

Week 1: Friday or Saturday 5/10, 5/11. Project board should be filled out. Tasks should be assigned to everyone. Everyone should have assigned algorithms they will be responsible for. Project should be created in IntelliJ with organization begun.

Week 2: No meeting, submit Draft assignment.

Week 3: Friday or Saturday, 5/24, 5/25. Second algorithm should be implemented by each person. Tasks should be moved to done. Report should be started.

Week 4: Submission date.

 
Format Requirements

    Please ensure you follow the structure as listed above. Use IntelliJ to do your work. Use JavaDoc comments where appropriate.
    Your submissions will be by providing a link to your Team's Github repo where this code is stored.
    For your final report, be as professional as possible. I am not putting direct limitations on its length. You can choose what format you want it to be in: Word, Markdown language or whatever you feel is most appropriate. Ensure your team wants to submit it as such and ensure that it answers all the questions from above for each algorithm and showcases each plot accordingly.

 
Assessment

Please see the Rubric attached for assessment.

 
Deliverables

Github repo link
