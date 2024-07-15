package io.next;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;
import io.next.Release.Response;
import lombok.Getter;
import lombok.ToString;

public class TitleDetails {
  private TitleDetails() {
  }

  public static void insert(String url, String username, String password, TitleDetails.Response titleDetails) {

    String sql = "INSERT INTO title_details (title, original_title, plot_overview, type, " +
        "runtime_minutes, year, end_year, release_date, imdb_id, user_rating, critic_score, " +
        "poster, backdrop, original_language, relevance_percentile, popularity_percentile, " +
        "trailer, trailer_thumbnail, genres, genre_names, id) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, username,
        password);
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setObject(1, titleDetails.getTitle());
      statement.setObject(2, titleDetails.getOriginalTitle());
      statement.setObject(3, titleDetails.getPlotOverview());
      statement.setObject(4, titleDetails.getType());
      statement.setObject(5, titleDetails.getRuntimeMinutes());
      statement.setObject(6, titleDetails.getYear());
      statement.setObject(7, titleDetails.getEndYear());
      statement.setObject(8, titleDetails.getReleaseDate());
      statement.setObject(9, titleDetails.getImdbId());
      statement.setObject(10, titleDetails.getUserRating());
      statement.setObject(11, titleDetails.getCriticScore());
      statement.setObject(12, titleDetails.getPoster());
      statement.setObject(13, titleDetails.getBackdrop());
      statement.setObject(14, titleDetails.getOriginalLanguage());
      statement.setObject(15, titleDetails.getRelevancePercentile());
      statement.setObject(16, titleDetails.getPopularityPercentile());
      statement.setObject(17, titleDetails.getTrailer());
      statement.setObject(18, titleDetails.getTrailerThumbnail());
      statement.setObject(19, titleDetails.getGenresJson());
      statement.setObject(20, titleDetails.getGenreNamesJson());
      statement.setObject(21, titleDetails.getId());

      int rowsAffected = statement.executeUpdate();
      System.out.println("Inserted " + rowsAffected + " row(s) into the title_details table.");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static Response fetchTitleDetails(Long titleId) {
    Dotenv dotenv = Dotenv.load();

    String url = Api.API_BASE_URL + "title/" + titleId + "/details/?apiKey=" + dotenv.get("API_KEY");
    String stringifiedJson = Api.call(url);
    JSONObject obj = new JSONObject(stringifiedJson);
    JSONArray genres = obj.getJSONArray("genres");
    JSONArray genreNames = obj.getJSONArray("genre_names");

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      Response titleDetails = objectMapper.readValue(obj.toString(), Response.class);
      titleDetails.genresJson = genres.toString();
      titleDetails.genreNamesJson = genreNames.toString();
      return titleDetails;
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new Error(e.getMessage());
    }
  }

  @Getter()
  @ToString()
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Response {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    @JsonProperty("plot_overview")
    private String plotOverview;

    @JsonProperty("type")
    private String type;

    @JsonProperty("runtime_minutes")
    private Integer runtimeMinutes;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("end_year")
    private Integer endYear;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("imdb_id")
    private String imdbId;

    @JsonProperty("user_rating")
    private Double userRating;

    @JsonProperty("critic_score")
    private Double criticScore;

    @JsonProperty("poster")
    private String poster;

    @JsonProperty("backdrop")
    private String backdrop;

    @JsonProperty("original_language")
    private String originalLanguage;

    @JsonProperty("relevance_percentile")
    private Double relevancePercentile;

    @JsonProperty("popularity_percentile")
    private Double popularityPercentile;

    @JsonProperty("trailer")
    private String trailer;

    @JsonProperty("trailer_thumbnail")
    private String trailerThumbnail;

    // @JsonProperty("genres")
    // private ArrayList<Integer> genres;

    // @JsonProperty("genre_names")
    // private ArrayList<String> genreNames;

    @JsonIgnore
    public String genresJson;

    @JsonIgnore
    public String genreNamesJson;
  }
}
