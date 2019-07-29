import Chapter08.TestClass;
public class TestMethodTest {public static void main(String[] args)
{
   TestClass testClass = new TestClass();
   if (testClass.testMethod(10) == 100)
{
       System.out.println("TEST SUCCESS");
    } else {
       System.out.println("TEST FAIL");
    }
}
}
