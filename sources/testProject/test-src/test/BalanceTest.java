class A {
}

class B extends A {
}

public class BalanceTest {

    public void call(A a) {
        System.out.println("A");
    }

    public void call(B b) {
        System.out.println("B");
    }

    public static void main(String[] argv) {
        A b = new B();
        BalanceTest test = new BalanceTest();
        test.call(b);
    }
}
