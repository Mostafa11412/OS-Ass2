import java.util.ArrayList;

public class SJF {
    public static void waitingTime(Process p[], int size , int w[], ArrayList<String> o,int cost){
        int current_time=0,mn=Integer.MAX_VALUE,low=0,completed=0,finish_time=0,j=0;
        boolean flag=false;
        int et[]=new int[size];
        for (int i=0;i<size;i++){
            et[i] = p[i].burst;
        }
        while (completed <size){
            String s="";
            for (int i=0;i<size;i++){
                if(p[i].arrival <=current_time && et[i]<mn /*&&et[i]>0*/ ){
                    mn=et[i];
                    low=i;
                    flag=true;
                    s=p[i].pName;
                }
            }
            if(!flag){
                current_time++;
                continue;
            }
            if(s.length()>0) {
                o.add(s);
                if(et[low]>1)current_time += cost;
            }
            et[low]--;
            mn = et[low];


            if (et[low] == 0) {
                mn = Integer.MAX_VALUE;
                completed++;
                flag = false;
                current_time+=cost;
                finish_time = current_time + 1;
                w[low] = finish_time - (p[low].burst + p[low].arrival);

            }
            current_time++;
        }
    }
    public static void tat(Process p[],int size,int wt[],int tat[]){
        for (int i = 0; i < size; i++)
            tat[i] = p[i].burst + wt[i];
    }

    public static void calc(Process p[],int size,int cost){
        int wt[]=new int[size];
        int tat[]=new int[size];
        float totalWait=0,totalTurn=0;
        ArrayList<String> o=new ArrayList<>();
        waitingTime(p,size,wt,o,cost);
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
