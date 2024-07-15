package io.next;

import io.github.cdimascio.dotenv.Dotenv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Release {
  private Release() {
  }

  public static List<Long> refresh(String url, String username, String password) {
    ArrayList<Response> releases = getReleases();

    String sql = "INSERT INTO releases (title, type, imdb_id, season_number, " +
        "poster_url, source_release_date, source_id, source_name, is_original, id) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    ArrayList<Long> releaseIds = new ArrayList<>();

    releases.forEach(release -> {
      try (Connection connection = DriverManager.getConnection(url, username,
          password);
          PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setObject(1, release.getTitle());
        statement.setObject(2, release.getType());
        statement.setObject(3, release.getImdbId());
        statement.setObject(4, release.getSeasonNumber());
        statement.setObject(5, release.getPosterUrl());
        statement.setObject(6, release.getSourceReleaseDate());
        statement.setObject(7, release.getSourceId());
        statement.setObject(8, release.getSourceName());
        statement.setObject(9, release.getIsOriginal());
        statement.setObject(10, release.getId());

        releaseIds.add(release.id);
        int rowsAffected = statement.executeUpdate();
        System.out.println("Inserted " + rowsAffected + " row(s) into the releases table.");

      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    return releaseIds;
  }

  private static ArrayList<Response> getReleases() {
    Dotenv dotenv = Dotenv.load();

    String url = Api.API_BASE_URL + "releases/?limit=20&apiKey=" + dotenv.get("API_KEY");
    String stringifiedJson = Api.call(url);
    JSONObject obj = new JSONObject(stringifiedJson);

    JSONArray releasesJsonArray = obj.getJSONArray("releases");
    ArrayList<Response> releases = new ArrayList<>();

    for (int i = 0; i < releasesJsonArray.length(); i++) {
      JSONObject releaseJson = releasesJsonArray.getJSONObject(i);

      ObjectMapper objectMapper = new ObjectMapper();

      try {
        Response release = objectMapper.readValue(releaseJson.toString(), Response.class);
        releases.add(release);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        throw new Error(e.getMessage());
      }
    }

    return releases;
  }

  @Getter()
  @ToString()
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("season_number")
    private Integer seasonNumber;

    @JsonProperty("poster_url")
    private String posterUrl;

    @JsonProperty("source_release_date")
    private Date sourceReleaseDate;

    @JsonProperty("source_id")
    private Integer sourceId;

    @JsonProperty("source_name")
    private String sourceName;

    @JsonProperty("is_original")
    private Integer isOriginal;
  }
}
