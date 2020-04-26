package Chapter01;

import org.junit.Test;

/**
 * Created by jaekwonha on 2018. 12. 1..
 */
public class Question11 {

    /**
     * 두 인터페이스 I, J를 구현하는 클래스가 있다고 가정하자
     * 각각은 void f() 메서드를 포함한다.
     * f가 I의 추상 메서드, 디폴트 메서드 또는 정적 메서드인 경우와
     * J의 추상 메서드, 디폴트 메서드 또는 정적 메서드인 경우 정확히 무슨일이 일어나는가?
     * <p>
     * 클래스가 슈퍼 클래스 S를 확장하고 인터페이스 I를 구현하며 둘 모두 void f() 메서드를 포함하는 경우에
     * 대해서도 무슨 일이 일어나는지 설명하라
     */

    public interface I {
        void f();

        static void f2() {
            System.out.println("I f2");
        }
    }

    public interface J {
        default void f() {
            System.out.println("J f");
        }

        default void f2() {
            System.out.println("J f2");
        }
    }

    public class MyClass implements I, J {
        public void f() {
            J.super.f();
            System.out.println("MyClass");
        }
    }

    public class S {
        void f() {
            System.out.println("S");
        }
    }

    public class MyClass2 extends S implements I, J {
        public void f() {
            super.f();
            J.super.f();
            System.out.println("MyClass2");
        }
    }

    @Test
    public void solution() {
        MyClass myClass = new MyClass();
        MyClass2 myClass2 = new MyClass2();

        myClass.f();
        myClass2.f();

        /**
         * I 의 static void f2() 가 무시되고 J의 default method 가 실행된다
         */
        myClass.f2();
    }

    /**
     * 추상 메서드는 반드시 구현체에서 구현해주어야 한다
     * 따라서 다른 interface 에 있는 default method 가 재구현될 수 있다
     *
     * static method 와 default method 가 같이 있을 때는 default method 가 실행된다
     */
}
