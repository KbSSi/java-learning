package lambda;

/**
 * @author KangWenBin
 * @description
 * @date 2023/8/26
 */
public class ThreadTest {
    //使用匿名类
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("hello");
        }
    });

    //使用lambda表达式
    Thread t1 = new Thread(() -> System.out.println("hello1"));
}
