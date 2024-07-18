package io.next;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

public class Vendor {
  private Vendor() {
  }

  public static void insert(Connection connection, Vendor.Seed vendor) {
    String sql = "INSERT INTO vendors (name) VALUES (?)";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, vendor.getName());

      int rowsAffected = statement.executeUpdate();
      System.out.println("Inserted " + rowsAffected + " row(s) into the vendors table.");
    } catch (SQLIntegrityConstraintViolationException e) {
      System.out.println("Duplicate entry: " + e.getMessage());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Getter()
  @ToString()
  @JsonIgnoreProperties(ignoreUnknown = true)
  public class Seed {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;
  }
}
