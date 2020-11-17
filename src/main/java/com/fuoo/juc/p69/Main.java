package com.fuoo.juc.p69;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author fuoo
 * @date 2020/11/16 16:26
 * @description: 函数式接口
 */
public class Main {
    public static void main(String[] args) {
        Function<String, Object> function = para->{
            return para + "使用函数型接口";
        };
        System.out.println(function.apply("fuoo"));

        Consumer consumer = para->{
            System.out.println(para + "使用消费型接口");
        };
        consumer.accept("fuoo");

        Supplier supplier = () -> {
            return "使用供给型接口";
        };
        System.out.println(supplier.get());

        Predicate predicate = para -> {
            System.out.println("使用断言型接口");
            return false;
        };
        System.out.println(predicate.test(1));
    }
}
