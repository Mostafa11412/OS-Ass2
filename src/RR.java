import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java. util. Arrays;

import static java.lang.Math.max;

public class RR {
    public static void waitingTime(Process p[], int size , int waiting[], ArrayList<String> o,int quantum,int cost){
        int current_time=0,mn=Integer.MAX_VALUE,low=0,completed=0,finish_time=0,cost1=0;
        boolean flag=false;
        int remaining_exec_time[]=new int[size];
        int arrival[]=new int[size];
        for (int i=0;i<size;i++){
            remaining_exec_time[i]=p[i].burst;
        }

        for (int i=0;i<size;i++){
            arrival[i]=p[i].arrival;
        }
        int mx=-1;
        for (int i=0;i<size;i++){
            mx=max(mx,p[i].arrival);
        }
        int mx1=mx;
        while(completed<size)
        {
            String s="";
            for (int i=0;i<size;i++){
                if(arrival[i] <=current_time && remaining_exec_time[i]>0 &&arrival[i]<mn ){
                    mn=arrival[i];
                    low=i;
                    flag=true;
                    s=p[i].pName;
                }
            }
            if(!flag){
                current_time++;
                if(mx1!=mx)finish_time++;
                continue;
            }
            if(s.length()>0) {
                o.add(s);
            }
            if(remaining_exec_time[low]>quantum){
                current_time += quantum;
                mx++;
                int t=current_time,prc=0;
                while (true){
                    int c=0;
                    for (int i=0;i<size;i++){
                        if(arrival[i]<=t &&i !=low){
                            c++;
                        }
                    }
                    if(prc==c){
                        break;
                    }
                    t+=quantum;
                    prc=c;
                }
                arrival[low]=t;
                cost1+=cost;
                remaining_exec_time[low] -= quantum;
                mn=Integer.MAX_VALUE;
            }else{
                current_time = current_time + remaining_exec_time[low];
                cost1+=cost;
                waiting[low] = (current_time-finish_time+cost1) - (p[low].burst+p[low].arrival);
                completed++;
                remaining_exec_time[low] = 0;
                mn=Integer.MAX_VALUE;
            }
            flag=false;
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
