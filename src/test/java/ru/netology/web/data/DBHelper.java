package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    private DBHelper() {}

    @SneakyThrows
    public static String getOrderStatus(String orderQuery) {
        Connection conn;
        val paymentIdQuery = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        }
        catch (Exception e) {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/app", "app", "pass");
        }
        val orderId = runner.query(conn, paymentIdQuery, new ScalarHandler<String>());
        val orderStatus = runner.query(conn, orderQuery, new ScalarHandler<String>(), orderId);
        if (conn != null) conn.close();
        return orderStatus;
    }

    public static String getPaymentStatus() {
        return getOrderStatus("SELECT status FROM payment_entity WHERE transaction_id = ?;");
    }

    public static String getCreditStatus() {
        return getOrderStatus("SELECT status FROM credit_request_entity WHERE bank_id = ?;");
    }
}
