import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ============================
// 🚗 Car Class
// ============================

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() { return isAvailable; }
    public void rent() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }
}

// ============================
// 👤 Customer Class
// ============================

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
}

// ============================
// 📝 Rental Class
// ============================

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() { return car; }
    public Customer getCustomer() { return customer; }
    public int getDays() { return days; }
}

// ============================
// 🛠️ CarRentalSystem Class
// ============================

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    // add data ..
    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("🚫 Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
        }
        if (rentalToRemove != null) {
            rentals.remove(rentalToRemove);
        } else {
            System.out.println("❗ Car was not rented.");
        }
    }

    // ============================
    // 📋 Menu System
    // ============================

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("        🚘 CAR RENTAL SYSTEM MENU         ");
            System.out.println("==========================================");
            System.out.println("1️⃣  Rent a Car");
            System.out.println("2️⃣  Return a Car");
            System.out.println("3️⃣  Exit");
            System.out.print("👉 Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("\n🚘 Rent a Car");
                System.out.print("👤 Enter your name: ");
                String customerName = scanner.nextLine();

                System.out.println("\n✅ Available Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println("🔹 " + car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }

                System.out.print("\n🔑 Enter the car ID you want to rent: ");
                String carId = scanner.nextLine();

                System.out.print("📅 Enter the number of rental days: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine(); // consume newline

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n🧾 Rental Information:");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\n✅ Confirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("🎉 Car rented successfully!");
                    } else {
                        System.out.println("❌ Rental canceled.");
                    }
                } else {
                    System.out.println("⚠️ Invalid car selection or car not available.");
                }

            } else if (choice == 2) {
                System.out.println("\n🔁 Return a Car");
                System.out.print("🔑 Enter the car ID you want to return: ");
                String carId = scanner.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("✅ Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("⚠️ Car was not rented or rental info missing.");
                    }
                } else {
                    System.out.println("❌ Invalid car ID or car is not rented.");
                }

            } else if (choice == 3) {
                System.out.println("\n🙏 Thank you for using the Car Rental System!");
                break;
            } else {
                System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}

// ============================
// 🎬 Main Class
// ============================

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        rentalSystem.addCar(new Car("C001", "Toyota", "Camry", 60.0));
        rentalSystem.addCar(new Car("C002", "Honda", "Accord", 70.0));
        rentalSystem.addCar(new Car("C003", "Mahindra", "Thar", 150.0));

        rentalSystem.menu();
    }
}
