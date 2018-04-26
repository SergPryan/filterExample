package com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PartRepository {

    public static List<Part> getAll(Map<String,String[]> map){
        List<Part> result = new ArrayList<>();
        Connection connection = DataSource.getConnection();
        String sql = createSqlFilterRequest(map);
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while (set.next()){
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

        }
        return result;
    }

    private static String createSqlFilterRequest(Map<String,String[]> map){
        StringBuilder sb = new StringBuilder();
        if(map.containsKey("name")){
            sb.append("\"partName\" LIKE '%").append(map.get("name")[0]).append("%'");
        }
        if(map.containsKey("number")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" \"partNumber\" LIKE '%").append(map.get("number")[0]).append("%'");
        }
        if(map.containsKey("vendor")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" vendor LIKE '%").append(map.get("vendor")[0]).append("%'");
        }
        if(map.containsKey("qty")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" qty NOT LESS ").append(map.get("qty")[0]);
        }
        //date
        if(map.containsKey("receivedAfter")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" received >= '").append(map.get("receivedAfter")[0]).append("'");
        }
        if(map.containsKey("receivedBefore")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" received <= '").append(map.get("receivedBefore")[0]).append("'");
        }
        if(map.containsKey("shippedAfter")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" shipped >= '").append(map.get("shippedAfter")[0]).append("'");
        }
        if(map.containsKey("shippedBefore")){
            if(sb.length()>0) {
                sb.append(" AND ");
            }
            sb.append(" shipped <= '").append(map.get("shippedBefore")[0]).append("'");
        }
        if(sb.length()>0){
            sb.insert(0,"SELECT * FROM part WHERE ");
        } else {
            sb.append("SELECT * FROM part");
        }
        System.out.println(sb);
        return sb.toString();

    }



}