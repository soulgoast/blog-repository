/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

/**
 * @ClassName MemoryPersistenceDemo
 * @Description
 * 使用内存进行持久化，就是用一个hashTable来保存数据
 *
 * In cases where reliability is not required across client or device
 * restarts memory this memory peristence can be used. In cases where
 * reliability is required like when clean session is set to false
 * then a non-volatile form of persistence should be used.
 * 如果客户端或者设备重启，内存中的数据不需要保存，则可以使用内存持久化。
 * 如果清除session设置为false，则应该使用非易失性的持久化方式，比如保存到磁盘。
 *
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/17 9:46
 * @ModifyDate 2019/12/17 9:46
 * @Version 1.0
 */
public class MemoryPersistenceDemo {

}
