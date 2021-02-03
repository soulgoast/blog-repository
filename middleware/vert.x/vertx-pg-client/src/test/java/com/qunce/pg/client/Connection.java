package com.qunce.pg.client;

import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class Connection {

    public static void main(String[] args) throws InterruptedException {
        Connection con = new Connection();
        con.test();
    }

    public void test() throws InterruptedException {
        PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(5432)
                .setHost("127.0.0.1")
                .setDatabase("thingsboard")
                .setUser("postgres")
                .setPassword("postgres");
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);
        PgPool client = PgPool.pool(connectOptions, poolOptions);

        client.query("select * from tb_user")
                .execute(resp -> {
                    System.out.println("查询结束");
                    if (resp.succeeded()) {
                        RowSet<Row> result = resp.result();
                        System.out.println("Got " + result.size() + " rows ");
                    } else {
                        System.out.println("Future:" + resp.cause().getMessage());
                    }
                });
        client.close();
    }

}
