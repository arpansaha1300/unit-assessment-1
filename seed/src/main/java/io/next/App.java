package io.next;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    static Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {
        refresh();
        // Runnable refreshRunnable = App::refresh;
        // ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        // exec.scheduleAtFixedRate(refreshRunnable, 0, 1, TimeUnit.MINUTES);
    }

    public static void refresh() {
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        final List<Release.Response> releases = Release.fetchReleases();
        ArrayList<TitleDetails.Response> titleDetails = new ArrayList<>();
        ArrayList<List<TitleSources.Response>> titleSources = new ArrayList<>();

        releases.forEach(release -> {
            titleDetails.add(TitleDetails.fetchTitleDetails(release.getId()));
            titleSources.add(TitleSources.fetchTitleSources(release.getId()));
        });

        try (final Connection connection = DriverManager.getConnection(url, username, password)) {
            for (int i = 0; i < releases.size(); i++) {
                Release.insert(connection, releases.get(i));

                final TitleDetails.Response titleDetail = titleDetails.get(i);
                TitleDetails.insert(connection, titleDetail);
                TitleSources.insert(connection, titleDetail.getId(), titleSources.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
