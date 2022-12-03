import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        List<String> list1 = permutation(list, 3);
        System.out.println(list1.toString()+list1.size());
        System.out.println("123".concat("1"));


    }
    public static List<String> permutation(List<String> list, int length) {
        Stream<String> stream = list.stream();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream().map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }
}
