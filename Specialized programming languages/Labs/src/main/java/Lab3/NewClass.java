package Lab3;

public class NewClass {
    private int privateField;
    public int publicField;
    public NewClass() {

    }

    public NewClass(int privateField, int publicField) {
        this.privateField = privateField;
        this.publicField = publicField;
    }

    public NewClass(NewClass newClass) {
        this.publicField = newClass.publicField;
        this.privateField = newClass.privateField;
    }

}
