package io.next;

import java.util.List;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.DriverManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    static Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {
        Runnable refreshRunnable = App::refresh;
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(refreshRunnable, 0, 24, TimeUnit.HOURS);
        // exec.scheduleAtFixedRate(refreshRunnable, 0, 10, TimeUnit.SECONDS);
    }

    private static void refresh() {
        final String dirPath = "./seed/src/main/java/io/next/data";
        final Path path = Paths.get(dirPath);

        try {
            if (!Files.isDirectory(path)) {
                System.out.println(dirPath + " is not a directory.");
                return;
            }
            try (final DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (final Path subPath : directoryStream) {
                    if (!Files.isDirectory(subPath) || subPath.toString().endsWith("-done")) {
                        continue;
                    }

                    final List<Movie.Seed> movieSeeds = Movie.readCSV(subPath);
                    final List<Poster.Seed> posterSeeds = Poster.readCSV(subPath);
                    final List<MovieVendor.Seed> movieVendorSeeds = MovieVendor.readCSV(subPath);

                    doInsert(movieSeeds, posterSeeds, movieVendorSeeds);

                    Files.move(subPath, Paths.get(subPath.toString() + "-done"));

                    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                    System.out.println("Insertion completed: " + LocalDateTime.now().format(formatter));

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doInsert(
            final List<Movie.Seed> movieSeeds,
            final List<Poster.Seed> posterSeeds,
            final List<MovieVendor.Seed> movieVendorSeeds) {

        final String url = dotenv.get("DB_URL");
        final String username = dotenv.get("DB_USER");
        final String password = dotenv.get("DB_PASSWORD");

        try (final Connection connection = DriverManager.getConnection(url, username,
                password)) {
            for (int i = 0; i < movieSeeds.size(); i++) {
                Movie.insert(connection, movieSeeds.get(i));
            }
            for (int i = 0; i < posterSeeds.size(); i++) {
                Poster.insert(connection, posterSeeds.get(i));
            }
            for (int i = 0; i < movieVendorSeeds.size(); i++) {
                MovieVendor.insert(connection, movieVendorSeeds.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
