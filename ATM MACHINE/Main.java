
import java.util.Scanner;

class ATM{
    float Balance;
    int PIN=1234;

public void cheakBalance(){
    System.out.println("Enter your pin :");
    Scanner sc = new Scanner(System.in);
    int pin = sc.nextInt();
    if(pin == PIN){
        menu();
    }    
    else{
        System.out.println("Incorrect PIN. Please try again.");
        
    }

}  

public void menu(){
    System.out.println("Enter your choice: ");
    System.out.println("1. Check Balance");
    System.out.println("2. Deposit Money");
    System.out.println("3. Withdraw Money");
    System.out.println("4. Exit");

    Scanner sc = new Scanner(System.in);
    int opt=sc.nextInt();

    if(opt==1){
        checkBalance();

    }
    else if(opt==2){
        depositMoney();
    }
    else if(opt==3){
        withdrawMoney();
    }
    else if(opt==4){
        System.out.println("Thank you for using our ATM service.");
    }
    else{
        System.out.println("Invalid option. Please try again.");
        menu();
    }



}  

public void checkBalance(){
    System.out.println("Your current balance is: " + Balance);
    menu();
    
}
public void withdrawMoney(){
    System.out.println("Enter the amount to withdraw: ");
    Scanner sc = new Scanner(System.in);
    float amount = sc.nextFloat();
    
    if(amount > Balance){
        System.out.println("Insufficient balance. Please try again.");
    } else {
        Balance = Balance - amount;
        System.out.println("Withdrawal successful. New balance is: " + Balance);
    }
    menu();
}
public void depositMoney(){
    System.out.println("Enter the amount to deposit: ");
    Scanner sc = new Scanner(System.in);
    float amount = sc.nextFloat();
    
    if(amount <= 0){
        System.out.println("Invalid amount. Please try again.");
    } else {
        Balance = Balance + amount;
        System.out.println("Deposit successful. New balance is: " + Balance);
    }
    menu();
}


}

public class Main {
    public static void main(String[] args) {
        ATM obj=new ATM();
        obj.cheakBalance();
    }

}
