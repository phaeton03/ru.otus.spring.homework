import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("murzik");
        final List<String> collect = Collections.emptyList().stream().map(cat::getName).collect(Collectors.toList());
        System.out.println(collect.size());
    }
}