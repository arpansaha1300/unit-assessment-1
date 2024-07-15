package io.next;

import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    static Dotenv dotenv = Dotenv.load();

    public static void main(String[] args) {
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        List<Long> releaseIds = Release.refresh(url, username, password);
        releaseIds.forEach(id -> {
            TitleDetails.refresh(url, username, password, id);
            TitleSources.refresh(url, username, password, id);
        });
    }
}
