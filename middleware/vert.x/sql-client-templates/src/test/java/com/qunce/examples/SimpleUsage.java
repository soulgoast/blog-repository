package com.qunce.examples;

import io.vertx.sqlclient.templates.SqlTemplate;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class SimpleUsage {

    @Test
    public void test() {
        Map<String, Object> parameters = Collections.singletonMap("id", 1);
/*        SqlTemplate
                .forQuery(client, "SELECT * FROM users WHERE id=#{id}")
                .execute(parameters)
                .onSuccess(users -> {
                    users.forEach(row -> {
                        System.out.println(row.getString("first_name") + " " + row.getString("last_name"));
                    });
                });*/
    }

}
