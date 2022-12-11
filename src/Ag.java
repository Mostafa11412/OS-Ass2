import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Ag {
    int current_time;
    int completed;
    int size;
    ArrayList<Process> ready;
    ArrayList<Process> p;
    ArrayList<Integer> arrival;
    ArrayList<Integer> burst;
    ArrayList<String> order;
    ArrayList<Integer> waiting;
    ArrayList<Integer> turn_around;
    Process current_process;
    ArrayList<Double>quantam_Array;
    public Ag(ArrayList<Process> pi,int size){
        current_process=null;
        current_time=0;
        completed=0;
        this.size=size;
        waiting=new ArrayList<>();
        turn_around=new ArrayList<>();
        order=new ArrayList<>();
        ready=new ArrayList<>();
        arrival=new ArrayList<>();
        burst=new ArrayList<>();
        p=new ArrayList<>();
        quantam_Array = new ArrayList<>();
        for (int i=0;i<size;i++){
            arrival.add(pi.get(i).arrival);
        }
        for (int i=0;i<size;i++){
            burst.add(pi.get(i).burst);
        }
        for (int i=0;i<size;i++){
            this.p.add(pi.get(i));
        }
        for (int i=0;i<size;i++){
            quantam_Array.add(pi.get(i).quantum);
        }
    }

    private double getQuarter (Process p)
    {
        return Math.ceil ((0.25) * p.getQuantum ())  ;
    }
    public void updateQueue(){
        for (int i=0;i<size;i++) {
            if (arrival.get(i)<=current_time&&p.get(i).burst>0){
                if(p.get(i)!=current_process){
                    int flag=0;
                    for (int j=0;j<ready.size();j++){
                        if(p.get(i).pName==ready.get(j).pName){
                            flag=1;
                        }
                    }
                    if(flag==0){
                        ready.add(p.get(i));
                    }
                }
            }
        }
    }

    public void updateQuantam(){
        for (int i=0;i<quantam_Array.size();i++){
            System.out.print(quantam_Array.get(i)+" ");
        }
        System.out.println("");
        quantam_Array.clear();
        for (int i =0 ; i<size ; i++){
            quantam_Array.add(p.get(i).quantum);
        }
    }
    public void execute(){
        boolean flag=false;
        while (true){
            for (int i=0;i<size;i++) {
                if (arrival.get(i)<=current_time&&burst.get(i)>0){
                    ready.add(p.get(i));
                    flag=true;
                }
            }
            if(!flag){
                current_time++;
                continue;
            }else{
                break;
            }
        }
        while (completed<size){
            if(current_process==null){
                current_process=ready.get(0);
                ready.remove(0);
            }
            double q=getQuarter(current_process);
            current_time+=Math.min(current_process.burst,q);
            double oldQuatum=current_process.getQuantum();
            current_process.quantum-=q;

            current_process.burst-=Math.min(current_process.burst,q);
            if(current_process.burst<=0){
                current_process.finish_time=current_time;
                current_process.quantum=0;
                completed++;
                order.add(current_process.pName);
                current_process=null;
                updateQuantam();
                continue;
            }
            updateQueue();
            boolean flag1=false;
            Process readyProcess=null;
            int ind=0,mn=Integer.MAX_VALUE;
            for (int i=0;i<ready.size();i++){
                if(ready.get(i).pri<current_process.pri && ready.get(i).pri<mn){
                    mn=ready.get(i).pri;
                    flag1=true;
                    readyProcess=ready.get(i);
                    ind=i;
                }
            }
            if (flag1){
                ready.remove(ind);
                current_process.setQuantum(oldQuatum+Math.ceil(current_process.getQuantum()/2));
                ready.add(current_process);
                order.add(current_process.pName);
                current_process=readyProcess;
            }else{
                q =getQuarter(current_process);
                current_time+=Math.min(current_process.burst,q);
                current_process.quantum-=q;
                current_process.burst-=Math.min(current_process.burst,q);
                if(current_process.burst<=0){
                    current_process.quantum=0;
                    current_process.finish_time=current_time;
                    completed++;
                    order.add(current_process.pName);
                    current_process=null;
                    updateQuantam();
                    continue;
                }
                updateQueue();
                flag1=false;
                readyProcess=null;
                ind=0;
                for (int i=0;i<ready.size();i++){
                    if(ready.get(i).burst<current_process.burst){
                        flag1=true;
                        readyProcess=ready.get(i);
                        ind=i;
                    }
                }
                if(flag1){
                    ready.remove(ind);
                    current_process.setQuantum(oldQuatum+Math.ceil(current_process.getQuantum()));
                    ready.add(current_process);
                    order.add(current_process.pName);
                    current_process=readyProcess;
                }else{
                    while (current_process.quantum>0 &&current_process.burst>0){
                        q =getQuarter(current_process);
                        current_time+=Math.min(q,current_process.burst);
                        current_process.quantum-=q;
                        current_process.burst-=q;
                    }
                    if(current_process.burst<=0){
                        current_process.quantum=0;
                        current_process.finish_time=current_time;
                        completed++;
                        order.add(current_process.pName);
                        current_process=null;
                        updateQuantam();
                        continue;
                    }
                    if(current_process.quantum<=0){
                        current_process.quantum+=2;
                        ready.add(current_process);
                        order.add(current_process.pName);
                        current_process=null;
                    }
                }

            }
            updateQuantam();
        }
     
    }
    public void calc(){
        execute();
        System.out.println("");
        for (int i=0;i<order.size();i++){
            System.out.print(order.get(i)+"  ");
        }
        for (int i=0;i<p.size();i++){
            waiting.add(p.get(i).finish_time-(burst.get(i)+arrival.get(i)));
        }
        for (int i = 0; i < size; i++) {
            turn_around.add(waiting.get(i)+ burst.get(i));
        }

        float totalWait=0,totalTurn=0;

        System.out.println("\n");
        System.out.println("Processes " +
                " Burst time " +
                " Waiting time " +
                " Turn around time");

        for (int i=0;i<size;i++)
        {
            totalWait+=waiting.get(i);
            totalTurn+=turn_around.get(i);

            System.out.println(" " + p.get(i).pName + "\t\t\t\t"
                    + burst.get(i) + "\t\t\t\t " + waiting.get(i)
                    + "\t\t\t\t" + turn_around.get(i));
        }
        System.out.println("");
        totalWait=totalWait/size;
        totalTurn=totalTurn/size;
        System.out.println("Average waiting time = " +
                totalWait);
        System.out.println("Average turn around time = " +
                totalTurn);
//        for (int i=0;i<Order_quantam.size();i++){
//            for (int j =0;j<Order_quantam.get(i).size();j++){
//                System.out.print(Order_quantam.get(i).get(j));
//                System.out.print(" ");
//            }
//            System.out.println();
//        }

    }

}
