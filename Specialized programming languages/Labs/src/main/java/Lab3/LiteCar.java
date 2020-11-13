package Lab3;

public class LiteCar extends Car {
    @Override
    public void printSpeed() {
        System.out.println(100);
    }
    @Override
    public void printName() {
        System.out.println("Name");
    }
    @Override
    public void consumedFuel() {
        System.out.println(12);
    }
    @Override
    public void printPassengerCount() {
        System.out.println(4);
    }
}
