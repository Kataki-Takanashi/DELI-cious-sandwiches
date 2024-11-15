package com.pluralsight;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class OrderFileManager {

    private static final String APP_FOLDER = "DELIcious-Sandwiches";
    private static final String RECEIPTS_FOLDER = "receipts";

    
    private static Path getReceiptsPath() {
        String userHome = System.getProperty("user.home");
        return Paths.get(userHome, APP_FOLDER, RECEIPTS_FOLDER);
    }

    
    public static void saveOrder(Order order) {
        try {
            Path receiptsDir = getReceiptsPath();
            Files.createDirectories(receiptsDir);

            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            Path orderFile = receiptsDir.resolve(timestamp + ".txt");

            try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(orderFile))) {
                writer.write(order.generateOrderSummary());
                System.out.println("Order saved successfully to " + orderFile);
            }
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public static List<Path> getSavedOrders() {
        List<Path> orderFiles = new ArrayList<>();
        try {
            Path receiptsDir = getReceiptsPath();
            if (Files.exists(receiptsDir)) {
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(receiptsDir, "*.txt")) {
                    stream.forEach(orderFiles::add);
                }
                orderFiles.sort((p1, p2) -> p2.getFileName().toString().compareTo(p1.getFileName().toString()));
            }
        } catch (IOException e) {
            System.out.println("Error reading orders: " + e.getMessage());
        }
        return orderFiles;
    }

    // For use in future
    public static String readOrder(Path orderFile) {
        try {
            return Files.readString(orderFile);
        } catch (IOException e) {
            System.out.println("Error reading order file: " + e.getMessage());
            return "Error reading order";
        }
    }
}
