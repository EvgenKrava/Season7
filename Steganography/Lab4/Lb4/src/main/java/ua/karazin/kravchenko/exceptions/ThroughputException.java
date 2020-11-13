package ua.karazin.kravchenko.exceptions;

public class ThroughputException extends Exception {
    public ThroughputException() {
        super("Data is larger than container");
    }
}
