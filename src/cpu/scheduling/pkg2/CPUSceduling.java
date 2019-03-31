/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu.scheduling.pkg2;

import java.util.Scanner;
import jdk.nashorn.internal.objects.Global;
      

/**
 *
 * @author Have_Fun
 */
public class CPUSceduling {

   public static dowork x;
    
    
    
    
    public static void main(String[] args) {
    
        x=new  dowork();
        new ChoiceMethod(x).setVisible(true);
        x.Getdata();
        x.Fcfs();//done
        x.Sjf();//done
        x.SjfNp();//done
        x.Priority();//done
        x.RoundRobin();//done
        x.sjfp2();//done
  
    }
    
    
 
    
    
}

class dowork{
    
        /*
    global variables 
    */
    
     ;
  
      float w;
      //Awt is the Average waiting time 
     float Awt;
    // the sum of the waiting time befor div on n 
     float Twt;
      // n is the acctually number of process 
    int n,tq;

         int Bu[] = new int[20];
   int Wt[]=new int[10];
     float A[] =new float[10];
     int [] P=new int[10];
     Scanner in = new Scanner(System.in); 
     
    
    
    
    
    void Getdata(){
            int i;
    System.out.println("Enter the no of processes:");
    // 20 is the maximum 
    n=in.nextInt();
  
    if(n<=20&&n>=0){
    for(i=1; i<=n; i++)
    {
        System.out.println("Enter The BurstTime for Process p" +i+"=	");
       Bu[i]= in.nextInt();
 
    }
    }else{
        System.out.println("please enter a number below 20 and up to 0");
    }
    }
    
   
   
   
   
   
   void sjfp2(){int B[]=new int[10];//burst time
float A[] =new float[10];//arrival time
for(int i=1; i<=n; i++) 
        {
        B[i]=Bu[i];
        System.out.println("Burst time for process p"+i+"=	"+B[i]);
        
        }
for(int i=1; i<=n; i++)
    {
       
        System.out.println("Enter the Arrival Time for"+i+"the process=	");
       
       // A[i]=in.nextFloat();
    }
   // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//maybe we wont need it
      //int n;//number of processes
      //System.out.println("Please enter the number of Processes: ");//maybe we wont need it
       //n = Integer.parseInt(br.readLine());//maybe we wont need it
       int proc[][] = new int[n + 1][4];//proc[][0] is the AT array,[][1] - RT,[][2] - WT,[][3] - TT
       //procc[][] is the [arr][burst]
       for(int i = 1; i <= n; i++)
       {
      System.out.println("Please enter the Arrival Time for Process " + i + ": ");
      proc[i][0] = (int)A[i];//assign arrival
      System.out.println("Please enter the Burst Time for Process " + i + ": ");
      proc[i][1] = B[i];//assign burst
     }
       System.out.println();
     
       //Calculation of Total Time and Initialization of Time Chart array
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += proc[i][1];
     }
     int time_chart[] = new int[total_time];
     
     for(int i = 0; i < total_time; i++)
     {
      //Selection of shortest process which has arrived
      int sel_proc = 0;
      int min = 99999;
      for(int j = 1; j <= n; j++)
      {
       if(proc[j][0] <= i)//Condition to check if Process has arrived
       {
        if(proc[j][1] < min && proc[j][1] != 0)
        {
         min = proc[j][1];
         sel_proc = j;
        }
       }
      }
      
      //Assign selected process to current time in the Chart
      time_chart[i] = sel_proc;
      
      //Decrement Remaining Time of selected process by 1 since it has been assigned the CPU for 1 unit of time
      proc[sel_proc][1]--;
      
      //WT and TT Calculation
      for(int j = 1; j <= n; j++)
      {
       if(proc[j][0] <= i)
       {
        if(proc[j][1] != 0)
        {
         proc[j][3]++;//If process has arrived and it has not already completed execution its TT is incremented by 1
            if(j != sel_proc)//If the process has not been currently assigned the CPU and has arrived its WT is incremented by 1
             proc[j][2]++;
        }
        else if(j == sel_proc)//This is a special case in which the process has been assigned CPU and has completed its execution
         proc[j][3]++;
       }
      }
      
      //Printing the Time Chart
      if(i != 0)
      {
       if(sel_proc != time_chart[i - 1])
        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
        //the new Process
       {
        System.out.print("--" + i + "--P" + sel_proc);
       }
      }
      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
       System.out.print(i + "--P" + sel_proc);
      if(i == total_time - 1)//All the process names have been printed now we have to print the time at which execution ends
       System.out.print("--" + (i + 1));
      
     }
     System.out.println();
     System.out.println();
     
     //Printing the WT and TT for each Process
     System.out.println("P\t WT \t TT ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%2dms\t%2dms",i,proc[i][2],proc[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     //Printing the average WT & TT
     float WT = 0,TT = 0;
     for(int i = 1; i <= n; i++)
     {
      WT += proc[i][2];
      TT += proc[i][3];
     }
     WT /= n;
     TT /= n;
     System.out.println("The Average WT is: " + WT + "ms");
     System.out.println("The Average TT is: " + TT + "ms");
 
    
}
   
   
    void Fcfs(){
          int i;
          int B[]=new int[10];
           Twt=0;
           System.out.println("Using Fcfs The process is : ");
         for(i=1; i<=n; i++)
    {
        // adding each process with it's time unit 
        B[i]=Bu[i];
        System.out.println("Burst time for process p"+i+"=	"+B[i]);
        
    }
          // setting the first process waiting th=ime to 0 
         Wt[1]=0;
    for(i=2; i<=n; i++)
    {
        // setting the waiting time to each process 
        /*
        ---- wt[1]=0;  wt[2]=B[1]+wt[1];    ==  [==][--]
        */
        Wt[i]=B[i-1]+Wt[i-1];
    }
    
     //Calculating Average Weighting Time
    // sum of all times waiting for ....
    for(i=1; i<=n; i++)
    {
        Twt=Twt+Wt[i];
    }
    
    Awt=Twt/n;

        System.out.println("Total   Weighting Time="+Twt);
        System.out.println("Average Weighting Time="+Awt+"\n");
  
    
    }
    
    
    
    
    
    
    
    
    
    void Sjf(){ 
        
        int i,j,temp;
        int B[]=new int[10];
        Twt=0;
        System.out.println("Using sjf The Process is :");
        for(i=1; i<=n; i++)
        {
        B[i]=Bu[i];
        System.out.println("Burst time for process p"+i+"=	"+B[i]);
        
        }
        
        for(i=n; i>=1; i--)
    {
        for(j=1; j<=n; j++)
        {
            if(B[j-1]>B[j])
            {
                temp=B[j-1];
                B[j-1]=B[j];
                B[j]=temp;
            }
        }
    }
        
         Wt[1]=0;
    for(i=2; i<=n; i++)
    {
        Wt[i]=B[i-1]+Wt[i-1];
    }
    //calculating Average Weighting Time
    for(i=1; i<=n; i++)
    {
        Twt=Twt+Wt[i];
    }
    Awt=Twt/n;
     System.out.println("Total   Weighting Time="+Twt);
     System.out.println("Average Weighting Time="+Awt+"\n");
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    **  non premitive Shotest jop first  
    */
    
    void SjfNp(){
    int i,temp,j;
    int Tt=0;
    int B[]=new int[10];
    char S[]=new char[10];
    float A[] =new float[10];
       float temp1,t;
    Twt=(float) 0.0;
    w=(float) 0.0;
     for(i=1; i<=n; i++)
    {
        B[i]=Bu[i];
       System.out.println("Burst time for process p"+i+"=	"+B[i]);
        S[i]='T';
        Tt=Tt+B[i];
        System.out.println("Enter the Arrival Time for"+i+"the process=	");
       
     //   A[i]=in.nextFloat();
    }
       for(i=n; i>=1; i--)
    {
        for(j=3; j<=n; j++)
        {
            if(B[j-1]>B[j])
            {
                temp=B[j-1];
                temp1=A[j-1];
                B[j-1]=B[j];
                A[j-1]=A[j];
                B[j]=temp;
                A[j]=temp1;
            }
        }
    }
 for(i=1; i<=n; i++)
    {
        System.out.println("p"+i+" "+B[i]+"  "+A[i]);
       
    }
     //For the 1st process
    Wt[1]=0;
    w=w+B[1];
    t=w;
    S[1]='F';
    
        while(w<Tt)
    {
        i=2;
        System.out.println(" 1 while");
        while(i<=n)
        {
            System.out.println(" 2 while");
        
            if(S[i]=='T'&&A[i]<=t)
            {
                Wt[i]= (int) w;
                System.out.println("WT"+i+"="+Wt[i]);
           
                S[i]='F';
                w=w+B[i];
                t=w;
                i=2;
                System.out.println(w);
            }
            else
                i++;
        }
    }

            for(i=1; i<=n; i++) {
       System.out.println("WT"+i+"=="+Wt[i]);
    }

          for(i=1; i<=n; i++)
             Twt=Twt+(Wt[i]-A[i]);
            Awt=Twt/n;
            
              System.out.println("Total   Weighting Time="+Twt);
            System.out.println("Average Weighting Time="+Awt+"\n");
        
    }
    
    
    void Priority(){
         int i,j;
         int [] B=new int[10];
         
         w=(float) 0.0;
    int max;
    Twt= (float) 0.0;
    max=1;
        
        for(i=1; i<=n; i++)
    {
        B[i]=Bu[i];
        System.out.println("Burst time for process p"+i+"=	"+B[i]);
       
        System.out.println("Enter the priority for process P"+i+"=	");
       // P[i]=in.nextInt();
        if(max<P[i]){
            max=P[i];
        }
    }
    j=1;
      while(j<=max)
    {
        i=1;
        while(i<=n)
        {
            if(P[i]==j)
            {
                Wt[i]=(int) w;
                w=w+B[i];
            }
            i++;
        }
        j++;
    }

    //calculating average weighting Time
    for(i=1; i<=n; i++){
        Twt=Twt+Wt[i];
    }
    Awt=Twt/n;
    System.out.println("Total   Weighting Time="+Twt);
    System.out.println("Average Weighting Time="+Awt+"\n");

    }
    
    
    
    
    
    
    
    
    
    
    void RoundRobin(){
        int i,j,k;
        int[]B=new int[10] ;
       int [][]Rrobin=new int[10][10];
       int [] count = new int[10];
    int max=0;
    int m;
    Twt=(float) 0.0;
    for(i=1; i<=n; i++)
    {
        B[i]=Bu[i];
        System.out.println("Burst time for process p"+i+"=	"+B[i]);
        
        if(max<B[i])
            max=B[i];
        Wt[i]=0;
    }
        System.out.println("Enter the Time Quantum=");
    
   // tq=in.nextInt();
    
            
    //TO find the dimension of the Rrobin array
    m=max/tq+1;
    
    
    //initializing Rrobin array
    for(i=1; i<=n; i++)
    {
        for(j=1; j<=m; j++)
        {
            Rrobin[i][j]=0;
        }
    }
    //placing value in the Rrobin array
    i=1;
    while(i<=n)
    {
        j=1;
        while(B[i]>0)
        {
            if(B[i]>=tq)
            {
                B[i]=B[i]-tq;
                Rrobin[i][j]=tq;
                j++;
            }
            else
            {
                Rrobin[i][j]=B[i];
                B[i]=0;
                j++;
            }
        }
        count[i]=j-1;
        i++;
    }

        System.out.println("Display");
    for(i=1; i<=n; i++)
    {
        for(j=1; j<=m; j++)
        {
            System.out.println("Rr["+i+","+j+"]="+Rrobin[i][j]+"	");
            
            
        }
        System.out.print("");
    }
    //calculating weighting time
    int x=1;
    i=1;
    while(x<=n)
    {
        for(int a=1; a<x; a++)
        {
            Wt[x]=Wt[x]+Rrobin[a][i];
        }
        i=1;
        int z=x;
        j=count[z];
        k=1;
        while(k<=j-1)
        {
            if(i==n+1)
            {
                i=1;
                k++;
            }
            else
            {
                if(i!=z)
                {
                    Wt[z]=Wt[z]+Rrobin[i][k];
                }
                i++;
            }
        }
        x++;
    }
    for(i=1; i<=n; i++) {
        System.out.println("Weighting Time for process P"+i+"="+Wt[i]);
    }

    //calculating Average Weighting Time
    for(i=1; i<=n; i++) {
        Twt=Twt+Wt[i];
    }
    Awt=Twt/n;

 System.out.println("Total   Weighting Time="+Twt);
    System.out.println("Average Weighting Time="+Awt+"\n");

    
    
    
    }
    
    
    
       
}
