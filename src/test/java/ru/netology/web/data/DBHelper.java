package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DBHelper {
    private DBHelper() {
    }

    @SneakyThrows
    public static String runQuery(String column, String table) {
        val runner = new QueryRunner();
        val query = "SELECT " + column + " FROM " + table + " ORDER BY created DESC LIMIT 1;";
        try (val mysqlConn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            return runner.query(mysqlConn, query, new ScalarHandler<>());
        } catch (Exception e) {
            val postgresqlConn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
            return runner.query(postgresqlConn, query, new ScalarHandler<>());
        }
    }

    public static String getPaymentStatus() {
        return runQuery("status", "payment_entity");
    }

    public static String getCreditStatus() {
        return runQuery("status", "credit_request_entity");
    }

    public static String getPaymentId() {
        return runQuery("transaction_id", "payment_entity");
    }

    public static String getCreditId() {
        return runQuery("bank_id", "credit_request_entity");
    }

    public static String getPaymentOrderId() {
        return runQuery("payment_id", "order_entity");
    }

    public static String getCreditOrderId() {
        return runQuery("credit_id", "order_entity");
    }
}