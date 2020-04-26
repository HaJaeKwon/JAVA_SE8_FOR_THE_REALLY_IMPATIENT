package Chapter01;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by jaekwonha on 2018. 11. 24..
 */
public class Question09 {

    /**
     * Collection 으로부터 Collection2라는 서브클래스를 만들고
     * filter가 true를 리턴하는 각 요소를 대상으로 액션(action)을 적용하는 디폴트 메서드인
     * void forEachIf(Consumer<T> action, Predicate<T>)를 추가하라
     * 이 디폴트 메서드를 어떻게 사용할 수 있는가
     */
    public interface Collection2<T> extends Collection<T> {
        default void forEachIf(Consumer<T> action, Predicate<T> filter) {
            Iterator<T> iter = iterator();
            while (iter.hasNext()) {
                T item = iter.next();
                if (filter.test(item)) {
                    action.accept(item);
                }
            }
            /**
             * forEach 는 iterable 의 디폴트 메서드
             * 보면 알겠지만 for T t : this
             * 를 실행하기 때문에 아래와 같이 사용하면 iter.hasNext 와 같은 코드를 쓸 필요 없다
             */
//            forEach(x -> {
//
//            });
        }
    }

    public class ArrayList2<E> extends ArrayList<E> implements Collection2<E> {

    }

    @Test
    public void solution() {
        ArrayList2<String> list = new ArrayList2<>();
        Collections.addAll(list, "jaekwon", "kay", "cream");
//        list.add("jaekwon");
//        list.add("kay");
//        list.add("cream");
        List<String> target = new ArrayList<>();

        list.forEachIf(target::add, s -> s.length() > 5);
        target.forEach(System.out::println);

    }

    /**
     * default method 는 static method 는 아니다. override 하지 않아도 사용할 수 있다는 의미이다
     * Consumer, Predicate 에 어떻게 람다식을 넘겨야 하는지 정도를 알고 넘어가면 좋을 것 같다
     */

}
