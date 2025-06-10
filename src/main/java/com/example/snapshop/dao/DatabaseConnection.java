package com.example.snapshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:h2:mem:testdb"; // In-memory database
        Connection conn = DriverManager.getConnection(url);
        initializeDatabase(conn); // Gọi phương thức khởi tạo
        return conn;
    }

    private static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Chạy schema.sql (cần đọc từ file)
            String sql = new String(DatabaseConnection.class.getClassLoader().getResourceAsStream("schema.sql").readAllBytes());
            stmt.execute(sql);
            System.out.println("✅ Đã tạo bảng và thêm dữ liệu mẫu!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}