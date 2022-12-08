import java.util.ArrayList;



public class PS {

    public static void calc(Process[] processes, int size){
        int[] waitingTime = new int[size];
        int[] turnAroundTime = new int[size];
        int[] x = new int[size];
        ArrayList<String> o=new ArrayList<>();
        int smallest , count = 0 , time=0;
        float totalWait=0,totalTurn=0, end;
        int lowPri=0,lowestInd=0,starCount=50,c=1;

        for (int i = 0 ; i < size;i++){
            x[i] = processes[i].burst;
        }



        String t="";
        while (count<size){
            int mx = Integer.MAX_VALUE;
            smallest = size - 1;
            String s="";
            for (int i = 0 ; i < size ; i++){
                if(processes[i].arrival <= time  && processes[i].pri < mx && processes[i].burst > 0){
                    smallest = i;
                    mx = processes[i].pri;
                    s=processes[i].pName;
                }
            }
            for (int i=0;i<size;i++){
                if(lowPri<processes[i].pri){
                    lowPri=processes[i].pri;
                    lowestInd=i;
                }
            }
            processes[smallest].burst--;
            if(!t.equals(s)){
                o.add(s);
                t=s;
            }

            if(processes[smallest].burst == 0){
                count++;
                end = time + 1;
                waitingTime[smallest] = (int) end - processes[smallest].arrival - x[smallest];
                turnAroundTime[smallest] = (int)end - processes[smallest].arrival;
                totalWait+=waitingTime[smallest];
                totalTurn=turnAroundTime[smallest];
            }
           time++;
            if((time/c)==starCount){
                processes[lowestInd].pri--;
                c++;
            }
       }
        for (int i=0;i<o.size();i++){
            System.out.print(o.get(i)+"  ");
        }
        System.out.println("\n");

        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time " );
        for (int i = 0 ; i < size ; i++){
            System.out.println(" " + processes[i].pName + "\t\t\t\t"
                    + x[i] + "\t\t\t\t " + waitingTime[i]
                    + "\t\t\t\t" + turnAroundTime[i]);
        }

        System.out.println("\n");
        totalWait=totalWait/size;
        totalTurn=totalTurn/size;
        System.out.println("Average waiting time = " +
                totalWait);
        System.out.println("Average turn around time = " +
                totalTurn);
    }
}
