package lab8;

public class App {
    public static void main(String[] args) {
        System.out.println("Task 1");
        task1();
        System.out.println("Task 2");
        task2();
        System.out.println("Task 3");
        task3();
    }

    private static void task3() {
        MyStack<Number> stN = new MyStack<>();
        MyStack<Integer> stI = new MyStack<>();
        MyStack<Double> stD = new MyStack<>();

        for(int i = 0; i < 3; i++) {
            stI.push( new Integer( i ) );
            stD.push( new Double( i + 3 ) );
        }

        System.out.println( "Integer stack: " + stI.toString() );
        System.out.println( "Double stack: " + stD.toString() );

        stN.addAll( stI );
        stD.moveElementsTo( stN );

        System.out.println( "Number stack: " + stN.toString() );
    }

    private static void task2() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.copy());
        System.out.println(new MyStack<>(stack));
        System.out.println(stack == stack.copy());
        System.out.println(stack.equals(stack.copy()));
    }

    private static void task1() {
        Task1<Long> task1_1 = new Task1<>(1L, 2L, 3L);
        Task1<Long> task1_2 = new Task1<>(1L, 2L, 4L);
        //System.out.println(task1_1.compareTo(task1_2));
    }
}
