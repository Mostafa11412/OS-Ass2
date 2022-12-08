import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Process p[]={new Process("p1",0,3),
                new Process("p2",2,5),
                new Process("p3",1,4),
                new Process("p4",4,2),
                new Process("p5",6,9),
                new Process("p6",5,4),
                new Process("p7",7,10),
                };
        RR.calc(p,p.length,2,0);



//        new Process("p1",0,4),
//                new Process("p2",1,8),
//                new Process("p3",3,2),
//                new Process("p4",10,6),
//                new Process("p5",12,5),

//        System.out.println("Enter number of process");
//        int n= in.nextInt();
//        Process p[]=new Process[n];
//        for (int i=0;i<n;i++){
//            System.out.println("Enter name of process ,arrival time and burst time");
//            String s=in.next();
//            int arr= in.nextInt();
//            int ex= in.nextInt();
//            p[i]=new Process(s,arr,ex);
//        }
//        System.out.println("Enter Context switching cost");
//        int c=in.nextInt();
//        SJF.calc(p,n,c);

    }
}
