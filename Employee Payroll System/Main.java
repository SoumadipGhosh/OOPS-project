// Employee ..

abstract class Employee{
    private String name;
    private int id;

    // Constructor ..
    public Employee(String name,int id){
        this.name=name;
        this.id=id;
    }

    // getter .. for not directly acess the name to others for increase security ..
    public String getName(){
        return name;
    }
    public int getid(){
        return id;
    }

    // abstract method .. only declare we not give body ..

    public abstract double calculateSalary();

    @Override
    public String toString(){
        return "Employee [name="+name+", id="+id+", salary="+calculateSalary()+"]";
    }
}

// Fulltime Employee ..

class FullTimeEmployee extends Employee{ // basically inherit ..
    private double Monthlysalary;


    // constructor ..
    public FullTimeEmployee(String name,int id,double Monthlysalary){  // dont want to make onother name , id for this we inherit ..
       super(name,id);
       this.Monthlysalary=Monthlysalary;
    }

    @Override

    public double calculateSalary(){
        return Monthlysalary;
    }

}

// Parttime Employee ..

class PartTimeEmployee extends Employee{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name,int id,int hoursWorked,double hourlyRate){
        super(name,id);
        this.hoursWorked=hoursWorked;
        this.hourlyRate=hourlyRate;
    }
    @Override

    public double calculateSalary(){
        return hoursWorked * hourlyRate;
    }
}


public class Main {
    public static void main(String[] args) {
        
    }
}
