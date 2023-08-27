package lambda;

import java.util.function.Supplier;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/26
 */
public class LambdaTest {
    public static void main(String[] args) {
        Supplier stringSupplier = () -> "ok";
        System.out.println(stringSupplier.get());

        Supplier stringSupplier1 = String::new; //返回空字符串
    }
}
