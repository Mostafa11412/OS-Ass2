import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("1-SJF 2-RR 3-PS 4-Ag");
        int status = in.nextInt();
            switch (status) {
                case 1:
                    System.out.println("Enter number of process");
                    int n = in.nextInt();
                    Process p[] = new Process[n];
                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter name of process ,arrival time and burst time");
                        String s = in.next();
                        int arr = in.nextInt();
                        int ex = in.nextInt();
                        p[i] = new Process(s, arr, ex);
                    }
                        System.out.println("Enter Context switching cost");
                        int con1 = in.nextInt();
                        SJF.calc(p, n, con1);
                        break;
                case 2:
                    System.out.println("Enter number of process");
                    int n2 = in.nextInt();
                    Process p2[] = new Process[n2];
                    for (int j = 0; j < n2; j++) {
                        System.out.println("Enter name of process ,arrival time and burst time ");
                        String s2 = in.next();
                        int arr2 = in.nextInt();
                        int ex2 = in.nextInt();
                        p2[j] = new Process(s2, arr2, ex2);
                    }
                    System.out.println("Enter Context switching cost");
                    int con2 = in.nextInt();
                    System.out.println("Enter Quantum");
                    int q = in.nextInt();
                    RR.calc(p2, n2, q, con2);
                    break;
                case 3:
                    System.out.println("Enter number of process");
                    int n3 = in.nextInt();
                    Process p3[] = new Process[n3];
                    for (int i = 0; i < n3; i++) {
                        System.out.println("Enter name of process ,arrival time , burst time and priority");
                        String s3 = in.next();
                        int arr3 = in.nextInt();
                        int ex3 = in.nextInt();
                        int pri3= in.nextInt();
                        p3[i] = new Process(s3, arr3, ex3,pri3);
                    }
                    PS.calc(p3, n3);
                    break;
                case 4:
                    System.out.println("Enter number of process");
                    int n4 = in.nextInt();
                    Process p4[] = new Process[n4];
                    for (int i = 0; i < n4; i++) {
                        System.out.println("Enter name of process ,arrival time ,burst time,priority and quantum");
                        String s4 = in.next();
                        int arr4 = in.nextInt();
                        int ex4 = in.nextInt();
                        int pri4= in.nextInt();
                        int q4= in.nextInt();
                        p4[i] = new Process(s4, arr4, ex4,pri4,q4);
                    }
                    Ag a = new Ag(p4, n4);
                    a.calc();
                    break;

            }
    }


//========================SJF=======================================================================================

//        Process p[] =
//                {
//                        new Process("p1", 0, 3),
//                        new Process("p2", 2, 5),
//                        new Process("p3", 1, 4),
//                        new Process("p4", 4, 2),
//                        new Process("p5", 6, 9),
//                        new Process("p6", 5, 4),
//                        new Process("p7", 7, 10),
//                };
//        SJF.calc(p, p.length, 0);

//=============================RR=======================================================================================

//        Process p[]={new Process("p1",0,3),
//                new Process("p2",2,5),
//                new Process("p3",1,4),
//                new Process("p4",4,2),
//                new Process("p5",6,9),
//                new Process("p6",5,4),
//                new Process("p7",7,10),
//                };
//        RR.calc(p,p.length,2,0);

//========================PS=======================================================================================

//                Process p[]={new Process("p1",0,1 , 2),
//                             new Process("p2",1,7 , 6),
//                             new Process("p3",2,3 , 3),
//                             new Process("p4",3,6 , 5),
//                             new Process("p5",4,5 , 4),
//                             new Process("p6",5,15 , 10),
//                             new Process("p7",15,8 , 9)};
//                PS.calc(p , p.length);

//========================AG===============================================================================================

//        ArrayList<Process> p=new ArrayList<>();
//        p.add(new Process("p1",0,17,4,7));
//        p.add(new Process("p2",2,6,7,9));
//        p.add(new Process("p3",5,11,3,4));
//        p.add(new Process("p4",15,4,6,6));
//        Ag a=new Ag(p, p.size());
//        a.calc();




}