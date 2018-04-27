package com.repository;

import com.commons.DataSource;
import com.entity.Part;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PartRepository {

    private static Logger log = Logger.getLogger(PartRepository.class.getName());

    public static List<Part> getAll(Map<String, String[]> map) {
        List<Part> result = new ArrayList<>();
        log.info("create sql request");
        String sql = createSqlFilterRequest(map);
        try (Connection connection = DataSource.getConnection()) {
            Statement statement = connection.createStatement();
            log.info("execute query");
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                Part part = new Part();
                part.setName(set.getString("partName"));
                part.setNumber(set.getString("partNumber"));
                part.setQty(set.getInt("qty"));
                part.setVendor(set.getString("vendor"));
                part.setReceived(set.getDate("received"));
                part.setShipped(set.getDate("shipped"));
                result.add(part);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Exception: ", e);
        }
        return result;
    }

    public static String createSqlFilterRequest(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey("name")) {
            sb.append("\"partName\" LIKE '%").append(map.get("name")[0]).append("%'");
        }
        if (map.containsKey("number")) {
            checkConditionAnd(sb);
            sb.append("\"partNumber\" LIKE '%").append(map.get("number")[0]).append("%'");
        }
        if (map.containsKey("vendor")) {
            checkConditionAnd(sb);
            sb.append("vendor LIKE '%").append(map.get("vendor")[0]).append("%'");
        }
        if (map.containsKey("qty")) {
            checkConditionAnd(sb);
            sb.append("qty >= ").append(map.get("qty")[0]);
        }
        if (map.containsKey("receivedAfter")) {
            checkConditionAnd(sb);
            sb.append("received >= '").append(map.get("receivedAfter")[0]).append("'");
        }
        if (map.containsKey("receivedBefore")) {
            checkConditionAnd(sb);
            sb.append("received <= '").append(map.get("receivedBefore")[0]).append("'");
        }
        if (map.containsKey("shippedAfter")) {
            checkConditionAnd(sb);
            sb.append("shipped >= '").append(map.get("shippedAfter")[0]).append("'");
        }
        if (map.containsKey("shippedBefore")) {
            checkConditionAnd(sb);
            sb.append("shipped <= '").append(map.get("shippedBefore")[0]).append("'");
        }
        if (sb.length() > 0) {
            sb.insert(0, "SELECT * FROM part WHERE ");
        } else {
            sb.append("SELECT * FROM part");
        }
        System.out.println(sb);
        return sb.toString();

    }

    private static void checkConditionAnd(StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(" AND ");
        }
    }


}