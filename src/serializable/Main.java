package serializable;
import java.io.*;

/*
* Serializable接口的作用是标记序列化，提供序列化的信息给JVM。这个接口中一个成员函数或者成员变量都没有，仅用于标记功能
* 序列化：把内存中的各种对象的状态（实例）保存下来，需要的时候再恢复（反序列化），序列化的机制可以让对象脱离程序的运行而独立存在
* [格式转变]：转变前：对象状态信息  转变后："可以存储或者传输的字节流"
*
* 更详细的细节参考：https://juejin.cn/post/7090150041024725028
* */
public class Main {
    //对people对象进行序列化和反序列化

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建用于存储序列化文件
        File file = new File("/tmp/people.java_");
        if (!file.exists()){
            file.getParentFile().mkdirs();
            try{
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        People p = new People(10L);

        //创建一个输入流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

        //输出可序列化对象
        oos.writeObject(p);
        //关闭输出流
        oos.close();

        //反序列化

        //创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        //得到反序列的对象
        Object newPerson = ois.readObject();
        ois.close();
        System.out.println(newPerson);
    }

}



