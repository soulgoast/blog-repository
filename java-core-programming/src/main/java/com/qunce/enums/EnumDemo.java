package com.qunce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class EnumDemo {
    public static void main(String[] args) {
        Teacher teacher = Teacher.AAA;
        System.out.println(teacher.getName());
        System.out.println(teacher.getId());

    }
}

@AllArgsConstructor
@Getter
enum Teacher {
    AAA("张三"),
    BBB("李四");

    private String name;

    public String getId() {
        return name();
    }

}
