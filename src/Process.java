public class Process {
    public String pName;
    public int arrival;
    public int burst;
    int pri;


    public Process(String pName, int arrival, int burst) {
        this.pName = pName;
        this.arrival = arrival;
        this.burst = burst;
    }

    public Process(String pName, int arrival, int burst , int pri) {
        this.pName = pName;
        this.arrival = arrival;
        this.burst = burst;
        this.pri = pri;
    }
}
