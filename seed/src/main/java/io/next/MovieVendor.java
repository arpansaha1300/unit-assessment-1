package io.next;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MovieVendor {
  private MovieVendor() {
  }

  public static void insert(Connection connection, MovieVendor.Seed movieVendor) {
    String sql = "INSERT INTO movie_vendors (movie_id, vendor_id, price) VALUES (?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, movieVendor.getMovieId());
      statement.setInt(2, movieVendor.getVendorId());
      statement.setDouble(3, movieVendor.getPrice());

      int rowsAffected = statement.executeUpdate();
      System.out.println("Inserted " + rowsAffected + " row(s) into the movie_vendors table.");
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Duplicate entry: " + e.getMessage());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<MovieVendor.Seed> readCSV(Path dirPath) {
    Path csvFile = dirPath.resolve("movie_vendors.csv");
    ArrayList<MovieVendor.Seed> movieVendorSeeds = new ArrayList<>();

    if (!Files.exists(csvFile)) {
      System.out.println("File does not exist: " + csvFile.toString());
      return movieVendorSeeds;
    }

    try (CSVReader csvReader = new CSVReader(new FileReader(csvFile.toString()))) {
      String[] nextLine;

      // Skip the first line (header)
      csvReader.readNext();

      while ((nextLine = csvReader.readNext()) != null) {
        movieVendorSeeds.add(new MovieVendor.Seed(
            Integer.parseInt(nextLine[0]),
            Integer.parseInt(nextLine[1]),
            Double.parseDouble(nextLine[2])));
      }
    } catch (CsvValidationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return movieVendorSeeds;
  }

  @Getter
  @ToString
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Seed {
    @JsonProperty("movie_id")
    private int movieId;

    @JsonProperty("vendor_id")
    private int vendorId;

    @JsonProperty("price")
    private double price;
  }
}
