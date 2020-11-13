package lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class MyStack<T extends Number> {
    private Node<? extends Number> head;
    private int size = 0;

    public MyStack() {

    }

    public MyStack(MyStack<? extends Number> stack) {
        MyStack<T> res = new MyStack<>();
        Node<? extends Number> current = stack.head;
        while (current != null) {
            res.push(current.data);
            current = current.next;
        }
        current = res.head;
        while (current != null) {
            this.push(current.data);
            current = current.next;
        }
    }

    public void push(Number data) {
        if (head == null) {
            head = new Node<>(data, null);
        } else {
            head = new Node<>(data, head);
        }
        size++;
    }

    public Number pop() {
        Number result = head.data;
        head = head.next;
        size--;
        return result;
    }

    public MyStack<T> copy() {
        MyStack<T> result = new MyStack<>();
        MyStack<T> res = new MyStack<>();
        Node<? extends Number> current = head;
        while (current != null) {
            res.push(current.data);
            current = current.next;
        }
        current = res.head;
        while (current != null) {
            result.push(current.data);
            current = current.next;
        }
        return result;
    }

    public void addAll(MyStack<? extends Number> stI) {
        Node<? extends Number> current = stI.head;
        while (current != null) {
            this.push(current.data);
            current = current.next;
        }
    }

    public void moveElementsTo(MyStack<? extends Number> stN) {
        Node<? extends Number> current = head;
        while (current != null) {
            stN.push(current.data);
            current = current.next;
        }
    }

    private static class Node<N extends Number> {
        N data;
        Node<? extends Number> next;

        public Node(N data, Node<? extends Number> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", MyStack.class.getSimpleName() + "[", "]");
        Node<? extends Number> current = head;
        List<Number> list = new ArrayList<>();
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            stringJoiner.add(String.valueOf(list.get(i)));
        }
        return stringJoiner.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStack<T> myStack = (MyStack<T>) o;
        if (size != myStack.size()) return false;
        Node<? extends Number> current = head;
        Node<? extends Number> myCurrent = myStack.head;
        while (current != null) {
            if (current.data != myCurrent.data) {
                return false;
            }
            current = current.next;
            myCurrent = myCurrent.next;
        }
        return true;
    }

    private int size() {
        return size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, size);
    }
}

