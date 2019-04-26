package Chapter08;


/**
 * Created by hajaekwon on 2019-04-26.
 */

public class TestClass {

    @TestCase(param = 10, result = 100)
    public int testMethod(int param) {
        return param * 10;
    }

}
