package toString;

/*
* 直接输出某个对象的时候，会输出{对象名@hash值}
* 但若重写toString方法，则会输出自己定义的东西
* */

public class Main {
    public static void main(String[] args) {
        Person kangwenbin = new Person("kangwenbin", 27);

        System.out.println(kangwenbin);
    }
}
