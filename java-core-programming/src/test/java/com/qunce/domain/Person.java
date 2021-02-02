package com.qunce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person implements Serializable {

    private String name;

    private Integer age;

    private Boolean sex;

    private Double deposit;

}
