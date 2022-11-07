package example.p207.optional;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        //创建一个空的Optional对象
        Optional<Car> optCar = Optional.empty();
        //使用静态工厂方法，根据一个非空值创建一个Optional对象
        Car car = new Car();
        //若car为空，立即抛出异常，而不是等到运行时才知道
        Optional<Car> optCar1 = Optional.of(car);
        //创建允许空值的Optional
        Optional<Car> optCat2 = Optional.ofNullable(car);
        //利用map提取可能为空的对象里的属性
        Insurance ins = new Insurance();
        Optional<Insurance> optInsurance = Optional.ofNullable(ins);
        Optional<String> name = optInsurance.map(Insurance::getName);
        //使用flatMap实现Optional传递,而不是map(链式map会造成Optional嵌套)
        Person person = new Person();
        Optional<String> insuranceName = Optional.of(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);
    }
}
