package io.next;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import lombok.ToString;

public class TitleSources {
  private TitleSources() {
  }

  public static void refresh(String url, String username, String password, Long titleId) {
    ArrayList<Response> titleSources = getTitleSources(titleId);

    String sql = "INSERT INTO title_sources (source_id, name, type, region, " +
        "web_url, format, price, seasons, episodes, title_id) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    titleSources.forEach(titleSource -> {
      try (Connection connection = DriverManager.getConnection(url, username,
          password);
          PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setObject(1, titleSource.getSourceId());
        statement.setObject(2, titleSource.getName());
        statement.setObject(3, titleSource.getType());
        statement.setObject(4, titleSource.getRegion());
        statement.setObject(5, titleSource.getWebUrl());
        statement.setObject(6, titleSource.getFormat());
        statement.setObject(7, titleSource.getPrice());
        statement.setObject(8, titleSource.getSeasons());
        statement.setObject(9, titleSource.getEpisodes());
        statement.setObject(10, titleId);

        int rowsAffected = statement.executeUpdate();
        System.out.println("Inserted " + rowsAffected + " row(s) into the title_sources table.");

      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  private static ArrayList<Response> getTitleSources(Long titleId) {
    Dotenv dotenv = Dotenv.load();

    String url = Api.API_BASE_URL + "title/" + titleId + "/sources/?apiKey=" + dotenv.get("API_KEY");
    String stringifiedJson = Api.call(url);
    JSONArray titleSourcesJsonArray = new JSONArray(stringifiedJson);
    ArrayList<Response> titleSources = new ArrayList<>();

    for (int i = 0; i < titleSourcesJsonArray.length(); i++) {
      JSONObject releaseJson = titleSourcesJsonArray.getJSONObject(i);

      ObjectMapper objectMapper = new ObjectMapper();

      try {
        Response release = objectMapper.readValue(releaseJson.toString(), Response.class);
        titleSources.add(release);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
        throw new Error(e.getMessage());
      }
    }

    return titleSources;
  }

  @Getter()
  @ToString()
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("source_id")
    private Integer sourceId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("region")
    private String region;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("format")
    private String format;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("seasons")
    private Integer seasons;

    @JsonProperty("episodes")
    private Integer episodes;
  }
}
