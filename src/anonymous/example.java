package anonymous;

/**
 * @author KangWenBin
 * @description 匿名类是指没有名字的类，通常匿名类用于继承一个类或实现一个接口，并且可以在创建对象时直接定义类的行为。
 * 其作用是简化代码，避免定义过多的类，提高代码的可读性和可维护性。
 * @date 2023/8/26
 */

public class example {
    public void createClass(){
        //创建的匿名类继承了Polygon类
        Polygon p1 = new Polygon(){
            public void display(){
                System.out.println("在匿名类内部");
            }
        };
        p1.display();
    }
}
