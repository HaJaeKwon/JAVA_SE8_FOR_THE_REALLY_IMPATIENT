package Chapter01;

import org.junit.Test;

/**
 * Created by jaekwonha on 2018. 12. 1..
 */
public class Question10 {

    /**
     * Collection 클래스의 메서드들을 살펴보자
     * 만일 하루 동안 왕이 된다면 어느 인터페이스의 각 메서드를 둘 것인가?
     * 각 메서드는 디폴트 메서드가 될 것인가 정적 메서드가 될 것인가?
     */

    @Test
    public void solution() {

    }

    /**
     * Collections class 에는 List, Map, Set, Collection 등에 대한 정렬, 추가, 수정, 복사, 셔플, 뒤집기 등에 대한 메소드를 static 으로 제공한다
     * List, Map, Set, Collection 을 받는 각각의 함수를 해당 interface 의 default method 로 옮기는 것이 좋아보인다
     * 다만 다중 상속이 필요한 메소드에 대해서는 interface 로 옮기는 것이 좋아보이지는 않는다
     *
     * Collections 를 보다보면 javadoc 을 굉장히 잘 써두었는데 나도 개발할 때 javadoc 을 잘 작성해두는 버릇이 필요해보인다
     */

}
