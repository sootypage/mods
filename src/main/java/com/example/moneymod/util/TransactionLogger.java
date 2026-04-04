package com.example.moneymod.util;

import java.nio.file.*;
import java.time.LocalDateTime;

public class TransactionLogger {

    private static final Path FILE = Paths.get("logs/moneymod-transactions.log");

    public static void log(String msg) {
        try {
            Files.createDirectories(FILE.getParent());

            String line = "[" + LocalDateTime.now() + "] " + msg + "\n";

            Files.writeString(FILE, line,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}