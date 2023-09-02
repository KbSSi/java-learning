package stream.test;

import org.junit.Before;
import org.junit.Test;
import stream.data.Customer;
import stream.data.Order;
import stream.data.OrderItem;
import stream.data.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
                .mapToObj(i -> new Product((long) i, "product" + i, i * 100.0))
                .collect(Collectors.toList()));
    }

    @Test
    public void sorted() {
        //大于50的订单，按照订单价格倒序前5

        orders.stream().filter(order -> order.getTotalPrice() > 50)
                .sorted(Comparator.comparing(Order::getTotalPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void distinct() {
        //去重下单用户
        System.out.println(orders.stream()
                .map(order -> order.getCustomerName())
                .distinct()
                .collect(Collectors.toList()));

        //提取出订单中所有的商品名，然后利用distinct来去重
        System.out.println(orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .map(OrderItem::getProductName)
                .distinct()
                .collect(Collectors.toList()));
    }

    @Test
    public void skipAndLimit() {
        //skip跳过一定项，limit用于限制项的总数

        //按照下单顺序排序，查询第三个和第四个订单的顾客姓名和下单时间
        orders.stream()
                .sorted(Comparator.comparing(Order::getPlacedAt))
                .map(order -> order.getCustomerName() + "@" + order.getPlacedAt())
                .skip(2).limit(2).forEach(System.out::println);
    }

    //collect操作

    //生成一定位数的随机字符串
    @Test
    public void randomTest() {
        System.out.println(random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(20)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString());
    }

    //所有下单的用户，使用toset去重后实现字符串拼接
    @Test
    public void toSetTest() {
        System.out.println(orders.stream()
                .map(order -> order.getCustomerName()).collect(Collectors.toSet())
                .stream().collect(Collectors.joining(",", "[", "]")));
    }

    //使用toCollection收集器指定集合类型
    @Test
    public void toCollectTest() {
        System.out.println(orders.stream().limit(2)
                .collect(Collectors.toCollection(LinkedList::new)).getClass());
    }

    //使用toMap获取下单id和用户名
    @Test
    public void toMapTest() {
        orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getCustomerName))
                .entrySet().forEach(System.out::println);
    }

    //使用toMap获取下单用户最近一次下单的Map, 第三个参数是一个merge Function，用于解决tomap后key冲突的问题
    @Test
    public void toMapTest2() {
        orders.stream()
                .collect(Collectors.toMap(Order::getCustomerName, Order::getPlacedAt, (x, y) -> x.isAfter(y) ? x : y))
                .entrySet().forEach(System.out::println);
    }

    //订单平均购买的商品数量
    @Test
    public void quantityTest() {
        System.out.println(orders.stream().collect(Collectors.averagingInt(order ->
                order.getOrderItemList().stream()
                        .collect(Collectors.summingInt(OrderItem::getProductQuantity)))));
    }


    //groupBy: 分组操作，类似SQL中的 group by语句

    // 按照用户名分组，统计下单数量，再按照下单数量倒序输出
    @Test
    public void groupByTest1() {
        System.out.println(orders.stream().collect(Collectors.groupingBy(Order::getCustomerName, Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList()));
    }

    //按照用户名分组，统计订单总数量
    @Test
    public void groupByTest2() {
        //这里要加上<String, Double> 才能使用reversed()，原因是编译器不能推断出泛型
        System.out.println(orders.stream().collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order::getTotalPrice)))
                .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).collect(Collectors.toList()));
    }

    //按照用户名分组，统计商品采购数量
    @Test
    public void groupByTest3() {
        System.out.println(
                orders.stream().collect(Collectors.groupingBy(Order::getCustomerName,
                                Collectors.summingInt(order -> order.getOrderItemList().stream()
                                        .collect(Collectors.summingInt(OrderItem::getProductQuantity)))))
                        .entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .collect(Collectors.toList())
        );
    }

    //统计最受欢迎的商品，倒序后取第一个
    // 相当于 map+flat，通过map把每一个元素替换成一个流，然后展开这个流
    @Test
    public void groupByTest4() {
        orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.summingInt(OrderItem::getProductQuantity)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(System.out::println);
    }

    //统计最受欢迎的商品的另一种方式，直接利用maxBy
    @Test
    public void groupByTest5() {
        orders.stream()
                .flatMap(order -> order.getOrderItemList().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName, Collectors.summingInt(OrderItem::getProductQuantity)))
                .entrySet().stream()
                .collect(Collectors.maxBy(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
    }

    //按照用户名分组，选用户下的总金额的最大的订单
    @Test
    public void groupByTest6() {
        orders.stream().collect(Collectors.groupingBy(
                        Order::getCustomerName, Collectors.collectingAndThen(Collectors.maxBy(Comparator
                                .comparingDouble(Order::getTotalPrice)), Optional::get))) //Optional::get - 如果值存在则获取值，否则抛出NoSuchElementExcetion
                .forEach((k, v) -> System.out.println(k + "#" + v.getTotalPrice() + "@" + v.getPlacedAt()));
    }

    //根据下单年月分组，统计订单ID列表
    @Test
    public void groupByTest7() {
        System.out.println(orders.stream().collect(Collectors.groupingBy(
                order -> order.getPlacedAt().format(DateTimeFormatter.ofPattern("yyyyMM")),
                Collectors.mapping(order -> order.getId(), Collectors.toList())
        )));
    }

    //根据下单年月 + 用户名两次分组，统计订单ID列表
    @Test
    public void groupTest8() {
        System.out.println(orders.stream().collect
                (Collectors.groupingBy(order -> order.getPlacedAt().format(DateTimeFormatter.ofPattern("yyyMM")),
                        Collectors.groupingBy(order -> order.getCustomerName(),
                                Collectors.mapping(order -> order.getId(), Collectors.toList())))));
    }

    // partitionBy: 用于分区

    //可以把用户分为下过订单和没下过订单两组：
    @Test
    public void groupTest9() {
        System.out.println(Customer.getData().stream().collect(
                Collectors.partitioningBy(customer -> orders.stream().mapToLong(Order::getCustomerId)
                        .anyMatch(id -> id == customer.getId()))
        ));
    }

    // 利用.peek()来观察整个过程中数据的变化
    @Test
    public void peek() {
        IntStream.rangeClosed(1, 10)
                .peek(i -> {
                    System.out.println("第一次peek");
                    System.out.println(i);
                })
                .filter(i -> i > 5)
                .peek(i -> {
                    System.out.println("第二次peek");
                    System.out.println(i);
                })
                .filter(i -> i % 2 == 0)
                .forEach(i -> {
                    System.out.println("最终结果");
                    System.out.println(i);
                });
    }
}
