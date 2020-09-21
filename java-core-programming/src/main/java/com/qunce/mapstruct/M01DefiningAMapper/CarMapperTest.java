/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M01DefiningAMapper;

import org.junit.Test;

/**
 * @ClassName CarMapperTest
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/21 18:33
 * @ModifyDate 2020/7/21 18:33
 * @Version 1.0
 */
public class CarMapperTest {

    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car( "Morris", 5, CarType.SEDAN );

        //when
        CarDTO carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        System.out.println(carDto.toString());

        Person person = new Person("张三", "武汉");
        PersonDto personDto = CarMapper.INSTANCE.personToPersonDto(person);
        System.out.println(personDto);

    }

}
