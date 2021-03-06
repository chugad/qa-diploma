package ru.netology.data;

import java.sql.*;

public class SQLDataGenerator {

    public static boolean approvedPay() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
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

//    public static boolean approvedPayFake() {
//        boolean result = false;
//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
//            PreparedStatement stmt = connection.prepareStatement("select oe.payment_id from order_entity as oe\n" +
//                    "left join credit_request_entity as pe on oe.payment_id = pe.bank_id\n" +
//                    "where pe.status = 'APPROVED';");
//            ResultSet rs = stmt.executeQuery();
//            result = rs.next();
//
//        } catch(SQLException exception) {
//            exception.getErrorCode();
//        }
//        return result;
//    }

    public static boolean declinedPay() {
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
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

    public static void cleanTables() throws SQLException {
        String deleteOrderEntity = "delete from order_entity;";
        String deletePaymentEntity = "delete from payment_entity;";
        String deleteCreditEntity = "delete from credit_request_entity;";

        try (
                Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");

                PreparedStatement statementOrderEntity = connection1.prepareStatement(deleteOrderEntity);
                PreparedStatement statementPaymentEntity = connection1.prepareStatement(deletePaymentEntity);
                PreparedStatement statementCreditEntity = connection1.prepareStatement(deleteCreditEntity);
        ) {
            statementOrderEntity.executeUpdate();
            statementPaymentEntity.executeUpdate();
            statementCreditEntity.executeUpdate();
        }
    }
}