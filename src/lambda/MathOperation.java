package lambda;

/**
 * @description 定义自己的函数式接口
 * @author kangwenbin
 * @date 2023/8/26
 */
@FunctionalInterface
public interface MathOperation {
    int operation(int a, int b);
}
