import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java. util. Arrays;

public class RR {
    public static void waitingTime(Process p[], int size , int waiting[], ArrayList<String> o,int quantam,int cost){
        int current_time=0,mn=Integer.MAX_VALUE,low=0,completed=0,finish_time=0;
        Deque<Integer> arrival = new ArrayDeque<>();
        int arr[] = new int[size];
        for (int i=0;i<size;i++){
            arr[i]=p[i].arrival;
        }
        Arrays.sort(arr);
        for (int i=0;i<size;i++){
            arrival.push(arr[i]);
        }
        boolean flag=false;
        int remaining_exec_time[]=new int[size];
        for (int i=0;i<size;i++){
            remaining_exec_time[i]=p[i].burst;
        }
        while (completed <size){
            String s="";
            if(current_time <arr[0] )
                flag=false;
            
            if(!flag){
                current_time++;
                continue;
            }
            if(s.length()>0) {
                o.add(s);
                if(remaining_exec_time[low]>1)current_time += cost;

            }
            if(remaining_exec_time[low] > quantam){
                current_time+=quantam;
                remaining_exec_time[low]-=quantam;
            }
            else {
                current_time+=remaining_exec_time[low];
                waiting[low] = current_time -(p[low].burst + p[low].arrival); //
                completed++;
            }
        }
    }
    public static void tat(Process p[],int size,int wt[],int tat[]){
        for (int i = 0; i < size; i++)
            tat[i] = p[i].burst + wt[i];
    }
    public static void calc(Process p[],int size,int quantam,int cost){
        int wt[]=new int[size];
        int tat[]=new int[size];
        float totalWait=0,totalTurn=0;
        ArrayList<String> o=new ArrayList<>();
        waitingTime(p,size,wt,o,quantam,cost);
        tat(p,size,wt,tat);
        for (int i=0;i<o.size();i++){
            System.out.print(o.get(i)+"  ");
        }
        System.out.println("\n");
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");
        for (int i=0;i<size;i++){
            totalWait+=wt[i];
            totalTurn+=tat[i];
            System.out.println(" " + p[i].pName + "\t\t\t\t"
                    + p[i].burst + "\t\t\t\t " + wt[i]
                    + "\t\t\t\t" + tat[i]);
        }
        totalWait=totalWait/size;
        totalTurn=totalTurn/size;
        System.out.println("Average waiting time = " +
                totalWait);
        System.out.println("Average turn around time = " +
                totalTurn);
    }
}
