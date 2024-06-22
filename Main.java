    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    //Interface 
    
    interface Rentable{
        public void rent();
        public void returnCar();
        public boolean isRented();
    }
    
    
    //Abstract Class
    
    abstract class AbstractCar implements Rentable{
        
        private String make;
        private String model;
        private int year;
        private boolean rented;
        
        
        //Setters
        
        public void setMake(String make){
            this.make = make;
        }
        
        public void setModel(String model){
            this.model = model;
        }
        
        public void setYear(int year){
            this.year = year;
        }
        
        
        //Getters
        
        public String getMake(){
            return make;
        }
        
        public String getModel(){
            return model;
        }
        
        public int getYear(){
            return year;
        }
        
        
        //Methods Body
        
        public void rent(){
            rented = true;
            System.out.println("Car is Rented");
        }
        
        public void returnCar(){
            rented = false;
            System.out.println("Car is Available");
        }
        
        public boolean isRented(){
            return rented;
        }
        
        
        //Abstract calculateRentalCost Methods
        
        public abstract int calculateRentalCost(int numberofDays);
        
    }
    
    
    //Car Classes
    
    class SUV extends AbstractCar{
        
        private final int dailyRate = 100;
        private final int weeklyRate = 600;
        
        public SUV(String make, String model, int year){
            setMake(make);
            setModel(model);
            setYear(year);
        }
        
        public int calculateRentalCost(int numberofDays){
            
            int days = numberofDays / 7;
            int weeks = numberofDays % 7;
            
            return (days*dailyRate) + (weeks*weeklyRate);
            
            //////////////////////////////////////////////////////////////////
            
            //int weekSV = numberofDays / 7;
            
            //int dailyRate = numberofDays*100;
            //int weeklyRate = weekSV*600;
            
            //return dailyRate;
            //return weeklyRate;
            
            //System.out.println("Daily Rate is: " + numberofDays*100);
            //System.out.println("Weekly Rate is: " + weekSV*600);
            
        }
    }
    
    class Sedans extends AbstractCar{
        
        private final int dailyRate = 80;
        private final int weeklyRate = 480;
        
        public Sedans(String make, String model, int year){
            setMake(make);
            setModel(model);
            setYear(year);
        }
        
        public int calculateRentalCost(int numberofDays){
            
            int days = numberofDays / 7;
            int weeks = numberofDays % 7;
            
            return (days*dailyRate) + (weeks*weeklyRate);
            
            //////////////////////////////////////////////////////////////////
            
            //int weekSD = numberofDays / 7;
            
            //int dailyRate = numberofDays*80;
            //int weeklyRate = weekSD*480;
            
            //return dailyRate;
            //return weeklyRate;
            
            //System.out.println("Daily Rate is: " + numberofDays*80);
            //System.out.println("Weekly Rate is: " + weekSD*480);
            
        }
    }
    
    class CompactSedans extends AbstractCar{
        
        private final int dailyRate = 50;
        private final int weeklyRate = 300;
        
        public CompactSedans(String make, String model, int year){
            setMake(make);
            setModel(model);
            setYear(year);
        }
        
        public int calculateRentalCost(int numberofDays){
            
            int days = numberofDays / 7;
            int weeks = numberofDays % 7;
            
            return (days*dailyRate) + (weeks*weeklyRate);
            
            //////////////////////////////////////////////////////////////////
            
            //int weekCS = numberofDays / 7;
            
            //int dailyRate = numberofDays*50;
            //int weeklyRate = weekCS*300;
            
            //return dailyRate;
            //return weeklyRate;
            
            //System.out.println("Daily Rate is: " + numberofDays*50);
            //System.out.println("Weekly Rate is: " + weekCS*300);
            
        }
    }
    
    class CarRentalSystem{
        public List<AbstractCar> cars = new ArrayList<>();
        
        public void addCar(AbstractCar car) {
            cars.add(car);
        }

        public void rentCar(int index) {
            int carNumber = 1;
            for (AbstractCar car : cars) {
                if (!car.isRented() && carNumber == index) {
                    car.rent();
                    carNumber++;
                    return;
                }
            }
            System.out.println("Car not available for rent.");
        }

        public void returnCarToCars(int index) {
            int carNumber = 1;
            for (AbstractCar car : cars) {
                if (car.isRented() && carNumber == index) {
                    car.returnCar();
                    carNumber++;
                    return;
                }
            }
            System.out.println("Car not found or already returned.");
        }

        public void displayAvailableCars() {
            System.out.println("Available Cars:");
            int i = 0;
            
            for (AbstractCar car : cars) {
                if (!car.isRented()) {
                    i++;
                    System.out.println(i + ". " + car.getMake() + " " + car.getModel() + " (" + car.getYear() + ")");
                }
            }
        }
    }
    
    public class Main{
    
	public static void main(String[] args) {
	    
	    CarRentalSystem rentalSystem = new CarRentalSystem();

        SUV suv1 = new SUV("Toyota", "Land Cruiser", 2021);
        Sedans sedan1 = new Sedans("Honda", "Odyssy", 2020);
        CompactSedans compactSedan1 = new CompactSedans("Ford", "Focus", 2007);

        rentalSystem.addCar(suv1);
        rentalSystem.addCar(sedan1);
        rentalSystem.addCar(compactSedan1);
        
        
         Scanner scanner = new Scanner(System.in);
         
         System.out.println("Welcome to the Advanced Car Rental System!");
         
        while (true) {
            System.out.println("Available Cars: ");
            rentalSystem.displayAvailableCars();
            
            System.out.println("Enter the car number to rent (0 to exit): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if(choice != 0 && choice < rentalSystem.cars.size()){
                rentalSystem.rentCar(choice);
                rentalSystem.displayAvailableCars();
            } else if (choice == 0) {
                scanner.close();
                return;
            } else {
                System.out.println("Input not in available cars, Please try again.");
            }
            
            System.out.println("Enter the car number to return (0 to exit): ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            if(choice != 0 && choice < rentalSystem.cars.size()){
                rentalSystem.returnCarToCars(choice);
                rentalSystem.displayAvailableCars();
            } else if (choice == 0) {
                scanner.close();
                return;
            } else {
                System.out.println("Input not in available cars, Please try again.");
            }

        }
        
	}
}