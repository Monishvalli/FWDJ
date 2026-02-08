import java.util.*;
class Simple {
    int s,p,n,r;
    Scanner sc;
    void Cal(){
        sc= new Scanner(System.in);
        p=sc.nextInt();
        n=sc.nextInt();
        r=sc.nextInt();
        s=p*n*r/100;
        System.out.println("Simple Interest is "+s);
    }
    public static void main(String args[]){
        Simple sim= new Simple();
        System.out.println("Enter principal, time and rate:");
        sim.Cal();
    }
    
}
