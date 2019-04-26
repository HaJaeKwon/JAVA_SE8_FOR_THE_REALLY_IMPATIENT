package Chapter08;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by hajaekwon on 2019-04-26.
 */
public class Question12 {

    /**
     * @TestCase 애너테이션과 이 애너테이션을 포함하는 클래스를 로드하는 프로그램을 구현하고,
     * 애너테이션이 붙은 메서드를 호출해 예상한 값을 주는지 검사하라
     * 파라미터와 리턴 값을 정수로 가정한다
     */
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = TestClass.class.getMethod("testMethod", int.class);
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof TestCase) {
                TestCase testCase = (TestCase) annotation;
                TestClass testClass = new TestClass();
                if (testCase.result() == testClass.testMethod(testCase.param())) {
                    System.out.println("TEST success");
                } else {
                    System.out.println("TEST fail");
                }
            }
        }
    }

}
