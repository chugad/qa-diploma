package ru.netology.data;

import java.sql.*;

import static java.lang.System.*;

public class SQLDataGenerator {

    public static void cleanTables() throws SQLException {
        String deleteOrderEntity = "delete from order_entity;";
        String deletePaymentEntity = "delete from payment_entity;";
        String deleteCreditEntity = "delete from credit_request_entity;";

        try (
                Connection connectionMysql = DriverManager.getConnection(getUrl(), getUser(), getPassword());

                PreparedStatement statementOrderEntity = connectionMysql.prepareStatement(deleteOrderEntity);
                PreparedStatement statementPaymentEntity = connectionMysql.prepareStatement(deletePaymentEntity);
                PreparedStatement statementCreditEntity = connectionMysql.prepareStatement(deleteCreditEntity);
        ) {
            statementOrderEntity.executeUpdate();
            statementPaymentEntity.executeUpdate();
            statementCreditEntity.executeUpdate();
        }
    }

    public static boolean approvedPay() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            PreparedStatement stmt = connection.prepareStatement("select oe.payment_id from order_entity as oe\n" +
                    "left join payment_entity as pe on oe.payment_id = pe.transaction_id\n" +
                    "where pe.status = 'APPROVED';");
            ResultSet rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException exception) {
            exception.getErrorCode();
        }
        return result;
    }

    public static boolean declinedPay() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            PreparedStatement stmt = connection.prepareStatement("select oe.payment_id from order_entity as oe\n" +
                    "left join payment_entity as pe on oe.payment_id = pe.transaction_id\n" +
                    "where pe.status = 'DECLINED';");
            ResultSet rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException exception) {
            exception.getErrorCode();
        }
        return result;
    }

    public static boolean approvedCredit() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            PreparedStatement stmt = connection.prepareStatement("select oe.credit_id from order_entity as oe\n" +
                    "left join credit_request_entity as cre on oe.payment_id = cre.bank_id\n" +
                    "where pe.status = 'APPROVED';");
            ResultSet rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException exception) {
            exception.getErrorCode();
        }
        return result;
    }

    public static boolean declinedCredit() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
            PreparedStatement stmt = connection.prepareStatement("select oe.credit_id from order_entity as oe\n" +
                    "left join credit_request_entity as cre on oe.payment_id = cre.bank_id\n" +
                    "where pe.status = 'DECLINED';");
            ResultSet rs = stmt.executeQuery();
            result = rs.next();

        } catch(SQLException exception) {
            exception.getErrorCode();
        }
        return result;
    }

    private static String getUrl() {
        return System.getProperty("test.db.url");
    }

    private static String getUser() {
        return "app";
    }

    private static String getPassword() {
        return "pass";
    }
}