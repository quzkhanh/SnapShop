package com.example.snapshop;

import com.example.snapshop.dao.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Objects;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/login.fxml")));
        if (root == null) {
            throw new RuntimeException("Không thể load file login.fxml. Kiểm tra đường dẫn!");
        }
        primaryStage.setTitle("SnapShop");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("✅ Kết nối H2 thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}