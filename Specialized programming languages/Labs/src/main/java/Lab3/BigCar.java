package Lab3;

public class BigCar extends Car {
    @Override
    public void printSpeed() {
        System.out.println(60);
    }
    @Override
    public void printName() {
        System.out.println("Track");
    }
    @Override
    public void consumedFuel() {
        System.out.println(20);
    }
    @Override
    public void printPassengerCount() {
        System.out.println(3);
    }
}
