package Chapter03;

import com.sun.deploy.util.ReflectionUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question09 {

    /**
     * 주어진 필드를 주어진 순서대로 비교하는 비교자를 돌려주는 lexicographicComparator(String... fieldNames) 메서드를 작성하라
     * 예를 들어, lexicographicComparator("lastname", "firstname")은 두 객체를 받고 리플렉션(reflection)을 이용해
     * lastname의 필드의 값들을 얻는다.
     * 만일 값들이 다르면 차이를 리턴하고, 같으면 firstname 필드로 이동한다. 모든 필드가 같으면 0을 리턴한다
     */
    @Test
    public void solution() {
        Person p1 = new Person("ha", "jaewon");
        Person p2 = new Person("koh", "eunh");
        List<Person> person = Arrays.asList(p1, p2);
        person.sort(lexicographicComparator("firstName", "lastName"));

        person.forEach(p -> {
            System.out.println(p.toString());
        });
    }

    public static <T> Comparator<T> lexicographicComparator(String... fieldNames) {
        return (v1, v2) -> {
            try {
                for (String fieldName : fieldNames) {
                    Class c1 = v1.getClass();
                    Field f1 = c1.getDeclaredField(fieldName);
                    Class c2 = v2.getClass();
                    Field f2 = c2.getDeclaredField(fieldName);
                    Object o1 = f1.get(v1);
                    Object o2 = f2.get(v2);
                    if (!o1.equals(o2)) {
                        return 1;
                    }
                    return 0;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return 0;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return 0;
        };
    }

    class Person {
        String firstName;
        String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return String.format("firstName : %s, lastName : %s", firstName, lastName);
        }
    }

    /**
     * Reflection 응 이용해 field value 를 꺼내면 object 타입이다.
     * object 타입은 equals 말고는 비교할 수 없어서...일단은 같은지만 비교하는 코드로 만들어두었다.
     */
}
