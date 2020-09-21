/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M02;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

/**
 * @ClassName ManyToOne
 * @Description
 * 多个实体类的数据映射到一个实体类中
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/21 18:46
 * @ModifyDate 2020/7/21 18:46
 * @Version 1.0
 */
public class ManyToOne {

    @Test
    public void test() {
        Person person = new Person("张三");
        Address address = new Address("武汉市");
        AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);
        DeliveryAddressDto deliveryAddressDto = addressMapper.personAndAddressToDeliveryAddressDto(person, address);
        System.out.println(deliveryAddressDto.toString());

    }

}
