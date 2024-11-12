package com.pluralsight;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderFileManager {

    private static final String RECEIPTS_FOLDER = "receipts";

    // Save a single order to a file
    public static void saveOrder(Order order) {
        try {
            // Ensure the receipts directory exists
            Files.createDirectories(Paths.get(RECEIPTS_FOLDER));

            // Generate the file name based on the current date and time
            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            String fileName = RECEIPTS_FOLDER + File.separator + timestamp + ".txt";

            // Write the order summary to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(order.generateOrderSummary());
                System.out.println("Order saved successfully to " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }
}
