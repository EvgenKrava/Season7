package lab8;

public class Task1<T extends Comparable<T>> implements Comparable<T> {
    private T a;
    private T b;
    private T c;

    public Task1(T a, T b, T c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public int compareTo(T o) {
        return (a.compareTo(o) + b.compareTo(o) + c.compareTo(o)) / 3;
    }


    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    public T getC() {
        return c;
    }

    public void setC(T c) {
        this.c = c;
    }
}
