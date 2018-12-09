package Chapter01;

import org.junit.Test;

/**
 * Created by jaekwonha on 2018. 12. 1..
 */
public class Question12 {

    /**
     * 과거에는 인터페이스에 메서드를 추가하면 기존 코드를 망가뜨릴 수 있기 ㅐ문에 잘못된 형태라고 했다
     * 하지만 이제는 디폴트 구현을 함께 제공한다면 새로운 메서드를 추가하는 것도 괜찮다고 한다
     * Collection 인터페이스의 새로운 stream 메서드가 레거시 코드 컴파일을 실패하게 하는 시나리오를 설명하라
     * 바이너리 호환성은 어떠한가? JAR 파일에 들어있는 레거시 코드는 여전히 실행될 것인가?
     */

    public interface I {
        default void stream1() {
            System.out.println("stream1");
        }
    }

    public interface J {
//        default void stream1() {
//            System.out.println("stream2");
//        }

    }

    public class C implements I, J {

    }

    @Test
    public void solution() {
        C c = new C();

        c.stream1();
    }

    /**
     * Collection 인터페이스를 구현해둔 Class 들이 구현하고 있는 다른 인터페이스의 default method 와 동일한
     * default method 가 추가될 시 영향 받는 Class 들은 반드시 default method 를 구현해주어야 하기에
     * 컴파일에 실패한다
     *
     * 아래와 같은 이유로
     * 그냥 default method 를 추가하는 것은 바이너리 호환성에 문제가 없을 것이다
     *
     *
     * 일반적으로 실행 파일(executable file)이나 라이브러리(library) 파일은 코드와 데이터 등을 구조적으로 저장하고 링크(link) 과정을 거친 바이너리 파일이다. 반면 Java는 각 클래스 파일을 컴파일하면 .class 확장자를 가진 바이너리 파일을 출력한다.

     일반적으로 코드와 데이터가 모두 포함된 바이너리 파일(일반적으로 portable executable)은 가리키는 offset 에 따라 RVA(relative virtual address)/VA(virtual address)와 매핑된다. 따라서 바이너리 파일을 비정상적으로 수정(삽입/삭제)하면 offset 정보도 함께 변경해 주어야 한다. 그렇지 않으면 메모리에 로드될 때 코드가 정상적으로 동작하지 않을 수 있다.

     반면 Java 의 컴파일 된 바이너리는 클래스 별로 컴파일되어 .class 파일로 출력되기 때문에 이런 offset 정보가 필요가 없다. 컴파일된 코드가 논리적인 구조에 따라 물리적인 파일을 생성하지 않는다. 할 수 있는 건 Stack Size 정도 늘리거나 줄일 수 있다.

     따라서 Java 8 Interface 의 .class 파일에 악의적으로 default 메서드 구현을 임의로 수정하면 간편하게 응용 프로그램 전체에 영향을 끼치도록 소정의 목적을 달성할 수 있다. (상대적으로 Java의 바이너리가 더 취약하다는 의미이다.)



     출처: https://blog.powerumc.kr/473 ["엄준일"과 함께하는 소프트웨어를 위한 플랫폼 이야기]
     */

}
