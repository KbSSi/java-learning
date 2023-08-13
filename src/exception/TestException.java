package exception;

/**
 * @author KangWenBin
 * @description 测试是否e.printStack可以返回多个抛出的错误
 * @date 2023/8/13
 */
public class TestException {
    public static void main(String[] args) {
        test();
    }

    public static  void test(){
        String s = null;

        try{
            s.toLowerCase();
        }catch (NullPointerException e){ //这里如果不将e传入到下面的异常中，会忽略上面的一个异常
            throw new PasswordErrorException("test");
        }
    }
}


