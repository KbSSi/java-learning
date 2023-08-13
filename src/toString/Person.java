package toString;

public class Person {
    private String name;
    private int age;

    Person(){};

    Person(String _name, int _age){
        name = _name;
        age = _age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString(){
        return "people{" + name +"}";
    }
}
