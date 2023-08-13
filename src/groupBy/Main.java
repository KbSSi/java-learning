package groupBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        People aa = new People("aa", 1);
        People bb = new People("bb", 1);
        People cc = new People("cc", 2);
        People dd = new People("dd", 2);

        ArrayList<People> peopleList = new ArrayList<>();
        peopleList.add(aa);
        peopleList.add(bb);
        peopleList.add(cc);
        peopleList.add(dd);

        // 利用stream()和.collect(Collectors.groupBy(xxx))进行分组
        Map<Integer, List<People>> map = peopleList.stream().
                collect(Collectors.groupingBy(People::getType));

        map.forEach((k, v) ->
                System.out.println(k + "=" + v));
    }
}
