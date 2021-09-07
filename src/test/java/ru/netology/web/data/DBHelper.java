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
    public static String getOrderStatus(String orderQuery) {
        val paymentIdQuery = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try (val mysqlConn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            val orderId = runner.query(mysqlConn, paymentIdQuery, new ScalarHandler<String>());
            return runner.query(mysqlConn, orderQuery, new ScalarHandler<>(), orderId);
        } catch (Exception e) {
            val postgresqlConn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
            val orderId = runner.query(postgresqlConn, paymentIdQuery, new ScalarHandler<String>());
            return runner.query(postgresqlConn, orderQuery, new ScalarHandler<>(), orderId);
        }
    }

    public static String getPaymentStatus() {
        return getOrderStatus("SELECT status FROM payment_entity WHERE transaction_id = ?;");
    }

    public static String getCreditStatus() {
        return getOrderStatus("SELECT status FROM credit_request_entity WHERE bank_id = ?;");
    }
}
