package com.qunce.jest;

import com.qunce.jest.domain.Customer;
import com.qunce.jest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

/**
 * @ClassName JestApplication
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 9:43
 * @ModifyDate 2020/9/21 9:43
 * @Version 1.0
 */
@SpringBootApplication(exclude = {ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class JestApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        customerRepository.deleteAll();
        this.saveCustomers();
    }

    private void saveCustomers() {
        this.customerRepository.save(new Customer("Alice", "Smith"));
        this.customerRepository.save(new Customer("Bob", "Smith"));
    }

    public static void main(String[] args) {
        SpringApplication.run(JestApplication.class, "--debug").close();
    }
}
