/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M02;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @ClassName AddressMapper
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/21 18:48
 * @ModifyDate 2020/7/21 18:48
 * @Version 1.0
 */
@Mapper
public interface AddressMapper {

    @Mapping(source = "person.name", target = "username")
    @Mapping(source = "address.address", target = "familyAddress")
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Address address);
}
