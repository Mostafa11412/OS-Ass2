
public class PS {

    public static void calc(Process[] processes, int size){
        //int[] priority = new int[size];
        int[] waitingTime = new int[size];
        int[] turnAroundTime = new int[size];
        int[] x = new int[size];
        int smallest , count = 0 , time;
        double avg = 0 , tt = 0 , end;

        for (int i = 0 ; i < size;i++){
            x[i] = processes[i].burst;
        }

        //processes[size].pri = 10000;
        int mx = Integer.MAX_VALUE;

        for (time = 0 ; count != size ; time++){
            smallest = size - 1;
            for (int i = 0 ; i < size ; i++){
                if(processes[i].arrival <= time && processes[i].pri < processes[smallest].pri && processes[i].pri < mx && processes[i].burst > 0){
                    smallest = i;
                    mx = processes[i].pri;
                }
            }
            processes[smallest].burst--;
            if(processes[smallest].burst == 0){
                count++;
                end = time + 1;
                waitingTime[smallest] = (int) end - processes[smallest].arrival - x[smallest];
                turnAroundTime[smallest] = (int)end - processes[smallest].arrival;
            }
        }
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time " +
                "   priority");
        for (int i = 0 ; i < size ; i++){
            System.out.print(processes[i].pName);
            System.out.print("\t\t\t");
            System.out.print(x[i]);
            System.out.print("\t\t\t\t");
            System.out.print(waitingTime[i]);
            System.out.print("\t\t\t\t");
            System.out.print(turnAroundTime[i]);
            System.out.print("\t\t\t\t");
            System.out.println(processes[i].pri);
            System.out.println("\n");
        }


    }
}
