package optional;

import java.util.Optional;

/**
 * @author KangWenBin
 * @description
 * @date 2023/9/2
 */

public class OptionalTest {

    public static void main(String[] args) {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // ofNullable允许参数为null
        Optional<Integer>a = Optional.ofNullable(value1);

        //Optional.of - 如果传递的参数为null，则抛出异常
        Optional<Integer>b = Optional.of(value2);

        System.out.println(OptionalTest.sum(a, b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent判断值是否存在
        System.out.println("第一个参数存在:" + a.isPresent());
        System.out.println("第二个参数存在:" + b.isPresent());

        //Optional.orElse - 如果值存在，返回他，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //optional.get - 获取值，值需要存在
        Integer value2 = b.get();

        return value1 + value2;
    }
}
