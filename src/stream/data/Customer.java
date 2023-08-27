package stream.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/27
 */

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;//顾客姓名

    public static List<Customer> getData() {
        return Arrays.asList(
                new Customer(10L, "小张"),
                new Customer(11L, "小王"),
                new Customer(12L, "小李"),
                new Customer(13L, "小朱"),
                new Customer(14L, "小徐")
        );
    }
}
