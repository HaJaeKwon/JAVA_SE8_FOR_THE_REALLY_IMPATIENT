package Chapter08;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hajaekwon on 2019-04-26.
 */

/**
 * 실행 시 main 메서드에 있는 테스트를 수행하는 프로그램을 만들어내는 소스 수준 애너테이션 처리기를 만들어
 * 연습문제 12를 반복하라 (소스 수순 애너테이션에 관한 소개는 호스트만과 코넬이 쓴 <<Core Java, 9th Edition, Volume 2>> 10.6 참고
 */

public class Question13 extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(TestCase.class.getName()));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "start");
        for (TypeElement t : annotations) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, t.getQualifiedName());
            for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
                if (e.getKind() != ElementKind.METHOD) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@TestCase must be applied to method");
                }
                TestCase testCase = e.getAnnotation(TestCase.class);
                String methodName = e.getSimpleName().toString();
                int param = testCase.param();
                int result = testCase.result();

                try {
                    writeTestFile(methodName, param, result);
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return true;
    }

    private void writeTestFile(String methodName, int param, int result) throws IOException {
        JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile("TestMethodTest");
//        JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(methodName + "Test");
        PrintWriter out = new PrintWriter(sourceFile.openWriter());
        int i = methodName.lastIndexOf(".");
        if (i>0) {
            out.print("package ");
            out.print(methodName.substring(0, i));
            out.println(";");
        }
        out.println("import Chapter08.TestClass;");
        out.print("public class ");
        out.print("TestMethodTest {");
//        out.print(methodName.substring(0, i));

        out.println("public static void main(String[] args)");
        out.println("{");
        out.println("   TestClass testClass = new TestClass();");
        out.println("   if (testClass."+methodName+"("+param+") == "+result+")");
        out.println(    "{");
        out.println("       System.out.println(\"TEST SUCCESS\");");
        out.println("    } else {");
        out.println("       System.out.println(\"TEST FAIL\");");
        out.println("    }");
        out.println("}");
        out.println("}");

        out.close();
    }

    /**
     * usage
     * cd ~/workspace/git/JAVA_SE8_FOR_THE_REALLY_IMPATIENT/src/main
     * javac -encoding UTF-8 Chapter08/Question13.java
     * javac -cp . -processor Chapter08.Question13 Chapter08/TestClass.java
     * java TestMethodTest
     * 이런식으로 annotation 이 붙은 Class, Method, Filed 등등을 처리하는구나..라는 걸 알게됬다
     * 좀 하드코딩 같은 느낌이 있지만 달리 방법이 있을까?
     * java compile 명령어나 annotation processor 에 대한 것만 알고 가면 될 듯 하다
     *
     * ret:
     * http://ptgmedia.pearsoncmg.com/images/9780137081608/samplepages/013708160X.pdf
     * https://medium.com/@jason_kim/annotation-processing-101-%EB%B2%88%EC%97%AD-be333c7b913
     */
}
