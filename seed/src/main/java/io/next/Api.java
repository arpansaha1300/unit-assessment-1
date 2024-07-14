package io.next;

import java.net.URI;
import java.net.URL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Api {
  public static final String API_BASE_URL = "https://api.watchmode.com/v1/";

  private Api() {
  }

  public static String call(String apiUrl) {
    try {
      URL url = URI.create(apiUrl).toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      int responseCode = connection.getResponseCode();

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      if (responseCode != HttpURLConnection.HTTP_OK) {
        System.out.println("Something went wrong!");
      }

      return response.toString();

    } catch (Exception e) {
      e.printStackTrace();
      throw new Error(e.getMessage());
    }
  }
}
