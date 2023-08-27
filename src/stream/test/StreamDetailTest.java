package stream.test;

import org.junit.Before;
import org.junit.Test;
import stream.data.Order;
import stream.data.Product;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/27
 */
public class StreamDetailTest {
    private static Random random = new Random();
    private List<Order> orders;

    @Before
    public void data() {
        orders = Order.getData();

        orders.forEach(System.out::println);
        System.out.println("=======================");
    }

    @Test
    public void filter() {
        System.out.println("//最近半年的金额大于40的订单");
        orders.stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getPlacedAt().isAfter(LocalDateTime.now().minusMonths(6)))
                .filter(order -> order.getTotalPrice() > 40)
                .forEach(System.out::println);
    }

    @Test
    public void map() {
        //计算所有订单商品数量

        // 通过两次遍历实现
//        LongAddr longAddr = new LongAdder();
//        orders.stream().forEach(order ->
//                order.getOrderItemList().forEach(orderItem -> longAddr.add(orderItem.getProductQuantity())));

        //把intStream通过转换Stream<Project>
        // rangeClosed前闭后闭，range前闭后开
        System.out.println(IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Product((long) i , "product" + i, i * 100.0))
                .collect(Collectors.toList()));
    }

    @Test
    public void sorted(){
        //大于50的订单，按照订单价格倒序前5

        orders.stream().filter(order -> order.getTotalPrice() > 50)
                .sorted(Comparator.comparing(Order::getTotalPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }


}
