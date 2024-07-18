package io.next;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class Movie {
  private Movie() {
  }

  public static void insert(Connection connection, Movie.Seed movie) {
    String sql = "INSERT INTO movies (title, plot, trailer, rating) VALUES (?, ?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, movie.getTitle());
      statement.setString(2, movie.getPlot());
      statement.setString(3, movie.getTrailer());
      statement.setInt(4, movie.getRating());

      int rowsAffected = statement.executeUpdate();
      System.out.println("Inserted " + rowsAffected + " row(s) into the movies table.");
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Duplicate entry: " + e.getMessage());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<Movie.Seed> readCSV(Path dirPath) {
    Path csvFile = dirPath.resolve("movies.csv");
    ArrayList<Movie.Seed> movieSeeds = new ArrayList<>();

    if (!Files.exists(csvFile)) {
      System.out.println("File does not exist: " + csvFile.toString());
      return movieSeeds;
    }

    try (CSVReader csvReader = new CSVReader(new FileReader(csvFile.toString()))) {
      String[] nextLine;

      // Skip the first line (header)
      csvReader.readNext();

      while ((nextLine = csvReader.readNext()) != null) {
        movieSeeds.add(new Movie.Seed(
            nextLine[0],
            nextLine[1],
            nextLine[2],
            Integer.parseInt(nextLine[3])));
      }
    } catch (CsvValidationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return movieSeeds;
  }

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Seed {

    @JsonProperty("title")
    private String title;

    @JsonProperty("plot")
    private String plot;

    @JsonProperty("trailer")
    private String trailer;

    @JsonProperty("rating")
    private int rating;

  }
}
