package exception;

/**
 * @author KangWenBin
 * @description 计算异常所带来的开销
 * @date 2023/8/13
 */
public class ExceptionTime {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    //没有try catch
    public static void test1(){
        long start = System.nanoTime();
        int a = 0;
        a++;
        System.out.println(System.nanoTime() - start);
    }

    public static void test2(){
        long start = System.nanoTime();
        int a = 0;
        try {
            a++;
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(System.nanoTime() - start);
    }

    public static void test3(){
        long start = System.nanoTime();
        int a = 0;
        try {
            a++;
            throw new Exception();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(System.nanoTime() - start);
    }


}
