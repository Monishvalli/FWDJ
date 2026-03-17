import java.util.*;


class Odd {
    int a;
    Scanner s;
    void input(){
        s= new Scanner (System.in);
        System.out.println("Enter a number:");
        a=s.nextInt();
        if (a%2 !=0){
            System.out.println(a+" is an odd number");
        }
        else {
            System.out.println(a +" is an even number");
        }
        }
    
    public static void main(String args[]){
        Odd o= new Odd();
        //added
        o.input();
    }
}

