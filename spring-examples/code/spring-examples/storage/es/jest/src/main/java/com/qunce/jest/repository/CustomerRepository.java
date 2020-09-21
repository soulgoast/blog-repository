package com.qunce.jest.repository;

import com.qunce.jest.domain.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @ClassName CustomerRepository
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 10:10
 * @ModifyDate 2020/9/21 10:10
 * @Version 1.0
 */
public interface CustomerRepository extends ElasticsearchCrudRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
