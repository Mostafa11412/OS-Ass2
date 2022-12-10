public class Process {
    public String pName;
    public int arrival;
    public int burst;
    public int pri;
    public double quantum;
    public int finish_time;



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

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public double getQuantum() {
        return quantum;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }

    public Process(String pName, int arrival, int burst, int pri, int quantum) {
        this.pName = pName;
        this.arrival = arrival;
        this.burst = burst;
        this.pri = pri;
        this.quantum = quantum;
    }
}
