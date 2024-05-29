# <div align = "center"> CPU Scheduler Algorithm Experiment </div>
## <div align = "center"> Written by: Jackson Kettel, Kelvin Rajbhandari, Ken Cage, Tamara Slone </div>

## Table of Contents
### [Introduction](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-introduction-)
* [First Come, First Serve Non-Preemptive](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-first-come-first-serve-fcfs-non-preemptive-)
* [First Come, First Serve Preemptive](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-first-come-first-serve-fcfs-preemptive-)
* [Shortest Job First](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-shortest-job-first-sjf-)
* [Priority Scheduling](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-priority-scheduling-)
* [Round Robin (FCFS)](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-round-robin-with-fcfs-)
* [Multilevel Queue](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-multilevel-queue-mq)
* [Multilevel Feedback Queue](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-multilevel-feedback-queue-mfq-)

### [Project Design (UML)](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-project-design-uml-)
### [Final Results](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-final-results-)
* [Average Result Times](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-average-result-times-)
  * [CPU Utilization](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-cpu-utilization-)
  * [Waiting Times](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-waiting-times-)
  * [Turnaround Times](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-turnaround-times-)
  * [Response Times](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-response-times-)
* [Algorithm Results Times](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-algorithm-result-times-)
### [Conculsion](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-conclusion-)


## <div align = "center" > Introduction </div>
<!--- Intro paragraph -->
### <div align = "center" > First Come, First Serve (FCFS) Non-Preemptive </div>
<!--- Paragraph about FCFS Non-preemptive Algorithm --->

### <div align = "center" > First Come, First Serve (FCFS) Preemptive </div>
&nbsp; &nbsp; &nbsp; &nbsp; In our experiment FCFS preemptive was not implemented into our project. To understand why this algorithm was not implemented, we need to take into consideration what it means for an algorithm to be FCFS and the definition of preemption. We also need to investigate what an FCFS preemptive algorithm would transform into and if there is a possibility of truly creating a FCFS preemptive algorithm.

&nbsp; &nbsp; &nbsp; &nbsp; **First come, first serve (FCFS)** is a scheduling algorithm used in operating systems that takes in a queue of processes and allows the process to release its CPU bursts by order of arrival. FCFS does not have any form of priority intake and thus in its algorithm structure is classified as a non-preemptive algorithm. However, due to its non-preemptive structure, this algorithm can cause issues with interactive systems and lead to potentially longer wait times than other algorithms.  **Preemption** is a form of priority structuring that allows for the algorithm to pause/stop a currently scheduled task and run another process that is higher on the priority list to run first. Preemption allows for the algorithm that implements it to have a quicker response time, allows the operating system to be more reliable, and prevents the processor from being monopolized by one process.

&nbsp; &nbsp; &nbsp; &nbsp; Now with an understanding of FCFS and preemption in mind. Since FCFS algorithm structure is inherently non-preemptive in its implementation. If a priority hierarchy were to be placed into a FCFS algorithm (Ex. A numbering system that labelled each process on a scale of 1 – 10 where 1 is the highest priority.), then it would change the structuring of our FCFS algorithm to a more optimized algorithm. However, in doing this change, we find that the algorithm transforms from a FCFS algorithm to a preemptive [**Priority Scheduling algorithm**](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-priority-scheduling-). The reason for this transformation is that preemptive Priority Scheduling is a special kind of FCFS algorithm and vice versa.

&nbsp; &nbsp; &nbsp; &nbsp; When checking into the similarities between FCFS being implemented with a preemptive priority level and how Priority Scheduling is structured, I cannot help but agree that these two algorithms are closely related. One factor in this change is the way that Priority scheduling takes in the first process by its priority level (which can be numbered) and executes it based on the order, which is very similar to how FCFS takes in the process based on its arrival time executes it. The two algorithms are alike in how they implement their ready queues, with the highest priority or first arrival being at the top of the queue list and the lowest priority or last arrival being at the bottom of the queue. The only differences between these two algorithms being in the complexity of how they are written and the differences in wait times. Therefore, if FCFS were to implement preemptive hierarchies it would transform into a preemptive Priority Scheduling algorithm.

### <div align = "center"> Shortest Job First (SJF) </div>
<!--- Paragraph about SJF Non-preemptive Algorithm --->

### <div align = "center"> Priority Scheduling </div>
<!--- Paragraph about Priority Scheduling Algorithm --->

### <div align = "center"> Round Robin (with FCFS) </div>
Within our CPU Scheduler experiment, one of the algorithms that was added into our project is Round Robin with FCFS implementation. **Round Robin (RR)** is a commonly used scheduling algorithm that uses **time slicing** (or time quantum’s) to assign a fixed unit of time for every process. Round Robn ensures that no single process can monopolize the CPU, as each process gets a fair share of CPU time in a cyclic manner. If a process’s burst time exceeds the time quantum, it is preempted and moved to the end of the queue, allowing the next process to use the CPU. This continues until all processes are completed. This algorithm can make sure all processes get a fair share of CPU time and is easy to implement. However, it can suffer from high context-switching overhead if the time quantum is too small. Round Robin may also degrade to a First-Come, First-Served (FCFS) behavior if the time quantum is too large. (More information on FCFS can be found [here](https://github.com/TamS129/CPUScheduler_TeamMetallica/blob/main/Report.md#-first-come-first-serve-fcfs-preemptive-).) This can lead to inefficiencies like the "convoy effect" where short processes wait behind long ones. Which in turn makes choosing a time quantum for implementing this algorithm important

In our Round Robin (with FCFS) algorithm, we have implemented a time quantum of 5 for every process. Meaning that every for every 5 units of CPU burst time the process must stop and return into a ready queue. Once the process is transferred to the ready queue, the next process is called and releases a CPU burst for 5 units. If the process has a shorter burst time than our quantum time, the process will complete its burst and go into I/O time. Because we are implementing Round Robin with FCFS the order of the processes sent will be determined based on the arrival time. However, since we are assuming that all arrival times in our processes start at 0, the processes are implemented one at a time from P1 to P8.

### <div align = "center"> Multilevel Queue (MQ)</div>
<!--- Paragraph about SJF MQ Algorithm --->

### <div align = "center"> Multilevel Feedback Queue (MFQ) </div>
<!--- Paragraph about SJF MFQ Algorithm --->

## <div align = "center"> Project Design (UML) </div>

<!--- Insert Image of UML--->
<!--- Insert Dicussion of UML--->

## <div align = "center"> Final Results </div>
## <div align = "center" > Average Result Times </div>
|                     | FCFS (Non-Pre) | FCFS (Pre) | SJF(Non-Pre)  | SRTF | Priority | RR(FCFS) | MLQ | MLFQ |
|:--------------------|:--------------:|:----------:|:-------------:|:----:|:--------:|:--------:|:---:|:----:|
| CPU utilization     |                |    N/A     |               |      |          |          |     |      |
| Avg Waiting time    |                |    N/A     |               |      |          |          |     |      |
| Avg Turnaround Time |                |    N/A     |               |      |          |          |     |      |
| Avg Response Time   |                |    N/A     |               |      |          |          |     |      |
### <div align = "center"> CPU Utilization </div>
<!--- Insert Chart --->
<!--- Insert Dicussion of CPU Utilization--->

### <div align = "center"> Waiting Times </div>
<!--- Insert Table--->
<!--- Insert Dicussion of WT--->

### <div align = "center"> Turnaround Times </div>
<!--- Insert Chart--->
<!--- Insert Table--->
<!--- Insert Dicussion of TT--->

### <div align = "center"> Response Times </div>
<!--- Insert Chart--->
<!--- Insert Table--->
<!--- Insert Dicussion of RT--->


## <div align = "center" > Algorithm Result Times </div>
| FCFS (Non-Pre)  CPU Utilization: |               |                  |                |    |    | SJF CPU Utilization: |                  |                |    |    | Priority Schdueling CPU Utilization: |                  |                |                |    |
|:---------------------------------|:-------------:|:----------------:|:--------------:|:--:|:--:|:--------------------:|:----------------:|:--------------:|:--:|:---|--------------------------------------|:----------------:|:--------------:|:--------------:|:--:|
| P1                               | Waiting Times | Turnaround Times | Response Times |    |    |    Waiting Times     | Turnaround Times | Response Times |    |    | Wait Time                            | TurnAround Times | Response Times |                |    |
| P2                               |               |                  |                |    | P1 |                      |                  |                |    | P1 |                                      |                  |                |                |    | 
| P3                               |               |                  |                |    | P2 |                      |                  |                |    | P2 |                                      |                  |                |                |    |
| P4                               |               |                  |                |    | P3 |                      |                  |                |    | P3 |                                      |                  |                |                |    |
| P5                               |               |                  |                |    | P4 |                      |                  |                |    | P4 |                                      |                  |                |                |    |
| P6                               |               |                  |                |    | P5 |                      |                  |                |    | P5 |                                      |                  |                |                |    |
| P7                               |               |                  |                |    | P6 |                      |                  |                |    | P6 |                                      |                  |                |                |    | 
| P7                               |               |                  |                |    |P7  |                      |                  |                |    | P7 |                                      |                  |                |                |    |
| P8                               |               |                  |                |    | P8 |                      |                  |                |    | P8 |                                      |                  |                |                |    |

| RR (FCFS)  CPU Utilization: |               |                  |                |    |    | MQ CPU Utilization: |                  |                      |    |     | MFQ CPU Utilization: |                  |                |                |    |
|:----------------------------|:-------------:|:----------------:|:--------------:|:--:|:--:|:-------------------:|:----------------:|:--------------------:|:--:|:----|----------------------|:----------------:|:--------------:|:--------------:|:--:|
| P1                          | Waiting Times | Turnaround Times | Response Times |    |    |    Waiting Times    | Turnaround Times |    Response Times    |    |     | Wait Time            | TurnAround Times | Response Times |                |    |
| P2                          |               |                  |                |    | P1 |                     |                  |                      |    | P1  |                      |                  |                |                |    | 
| P3                          |               |                  |                |    | P2 |                     |                  |                      |    | P2  |                      |                  |                |                |    |
| P4                          |               |                  |                |    | P3 |                     |                  |                      |    | P3  |                      |                  |                |                |    |
| P5                          |               |                  |                |    | P4 |                     |                  |                      |    | P4  |                      |                  |                |                |    |
| P6                          |               |                  |                |    | P5 |                     |                  |                      |    | P5  |                      |                  |                |                |    |
| P7                          |               |                  |                |    | P6 |                     |                  |                      |    | P6  |                      |                  |                |                |    | 
| P7                          |               |                  |                |    |P7  |                     |                  |                      |    | P7  |                      |                  |                |                |    |
| P8                          |               |                  |                |    | P8 |                     |                  |                      |    | P8  |                      |                  |                |                |    |

| FCFS (Non-Pre)  CPU Utilization: |               |                  |                |    |
|:---------------------------------|:-------------:|:----------------:|:--------------:|:--:|
| P1                               | Waiting Times | Turnaround Times | Response Times |    |
| P2                               |               |                  |                |    | 
| P3                               |               |                  |                |    |
| P4                               |               |                  |                |    |
| P5                               |               |                  |                |    |
| P6                               |               |                  |                |    |
| P7                               |               |                  |                |    |
|                                  |               |                  |                |    |
| P8                               |               |                  |                |    |


## <div align = "center" > Conclusion </div>
