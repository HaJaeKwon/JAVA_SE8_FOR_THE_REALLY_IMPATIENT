package Chapter02;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question01 {

    @Test
    public void main() {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
//        locales.forEach(System.out::println);

        Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));
        countryToLocales.forEach((k, v) -> {
            System.out.print(k + " : ");
            System.out.println(v);
        });

        IntSummaryStatistics intSummaryStatistics;

//        List<String> wordList = Arrays.asList("jaekwon", "haha", "kay", "cream"); // Arrays.asList 는 크기에 영향을 주는 조작을 허용하지 않는 목록을 리턴한다
        List<String> wordList = new ArrayList<>();
        wordList.add("jaekwon");
        wordList.add("kay");
        wordList.add("cream");
        Stream<String> words = wordList.stream();
        wordList.add("zzzz");
        words.forEach(System.out::println);

//        words.forEach((s) -> { if (s.length() > 4) wordList.remove(s); });

    }

    /**
     * 41페이지에 있는 "2.1 반복에서 스트림 연산으로" 절의 for 루프를 벙렬 버전으로 작성하라.
     * 먼저 프로세서의 개수를 얻는다. 다수의 스레드를 생성해 각 스레드가 리스트의 각 세그먼트를 대상으로 작업하게 하고, 결과들이 나오면 합산한다
     * 여러분은 스레드들이 단일 카운터를 업데이트하는 사황을 원하지 않을 것이다.
     * 그 이유는 무엇인가?
     */
    @Test
    public void solution() {
//        try(Stream<String> contents = Files.lines(Paths.get("alice.txt"))) {
//            Arrays.asList(contents.flatMap(c -> Stream.of(c.split("[\\P{L}]+"))));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            String contents = new String(Files.readAllBytes(Paths.get("./src/test/java/Chapter02/alice.txt")), StandardCharsets.UTF_8);
            List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

            int cores = Runtime.getRuntime().availableProcessors();
            long[] results = new long[cores];
            int step = (int) Math.ceil((double)words.size() / cores);
            int limit = 5;
            for (int i=0; i<cores; i++) {
                words.subList(0,0);
                final int index = i;
                Thread thread = new Thread(() -> {
                    results[index] = words.subList( (index*step), Math.min((index+1)*step, words.size()) ).stream().filter(w -> w.length() > limit).count();
                    System.out.println(Thread.currentThread().getId());
                });
                thread.run();
            }
            System.out.println(Arrays.stream(results).sum());
            long total = words.stream().filter(w -> w.length() > limit).count();
            System.out.println(total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * subList를 이용해 나눈 List를 각 thread에 할당해주고 result값을 모았다
     * 단일 카운터를 업데이트하는 상황이란 단일 카운터가 thread safe하지 않기 때문이다
     */



}
