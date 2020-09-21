package com.qunce.abstractdocument.tls.exception;

/**
 * @ClassName TslNotFoundException
 * @Description 字段未找到异常
 * JSON格式的数据在传输的过程中，某个阶段只需要解析其中一部分数据进行业务处理。
 * 如果某个字段是业务处理所必须的，但是JSON中没有，则会抛出此异常。
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:58
 * @ModifyDate 2020/9/21 14:58
 * @Version 1.0
 */
public class TslNotFoundException extends Exception {


}
