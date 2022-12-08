import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
//        Process p[]={new Process("p1",0,1),
//                new Process("p2",1,7),
//                new Process("p3",2,3),
//                new Process("p4",3,6),
//                new Process("p5",4,5),
//                new Process("p6",5,15),
//                new Process("p7",15,8)};
        System.out.println("Enter number of process");
        int n= in.nextInt();
        Process p[]=new Process[n];
        for (int i=0;i<n;i++){
            System.out.println("Enter name of process ,arrival time and burst time");
            String s=in.next();
            int arr= in.nextInt();
            int ex= in.nextInt();
            p[i]=new Process(s,arr,ex);
        }
        System.out.println("Enter Context switching cost");
        int c=in.nextInt();
        SJF.calc(p,n,c);
    }
}
