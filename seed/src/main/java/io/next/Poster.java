package io.next;

import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class Poster {
  private Poster() {
  }

  public static void insert(Connection connection, Poster.Seed poster) {
    String sql = "INSERT INTO posters (horizontal, vertical, movie_id) VALUES (?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, poster.getHorizontal());
      statement.setString(2, poster.getVertical());
      statement.setInt(3, poster.getMovieId());

      int rowsAffected = statement.executeUpdate();
      System.out.println("Inserted " + rowsAffected + " row(s) into the posters table.");
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Duplicate entry: " + e.getMessage());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<Poster.Seed> readCSV(Path dirPath) {
    Path csvFile = dirPath.resolve("posters.csv");
    ArrayList<Poster.Seed> posterSeeds = new ArrayList<>();

    if (!Files.exists(csvFile)) {
      System.out.println("File does not exist: " + csvFile.toString());
      return posterSeeds;
    }

    try (CSVReader csvReader = new CSVReader(new FileReader(csvFile.toString()))) {
      String[] nextLine;

      // Skip the first line (header)
      csvReader.readNext();

      while ((nextLine = csvReader.readNext()) != null) {
        posterSeeds.add(new Poster.Seed(
            nextLine[0],
            nextLine[1],
            Integer.parseInt(nextLine[2])));
      }
    } catch (CsvValidationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return posterSeeds;
  }

  @Getter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Seed {
    @JsonProperty("horizontal")
    private String horizontal;

    @JsonProperty("vertical")
    private String vertical;

    @JsonProperty("movie_id")
    private int movieId;
  }
}
