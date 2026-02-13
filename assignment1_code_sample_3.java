import java.sql.*;
import java.util.Scanner;

public class SampleApp {
  private static final String DB_URL = "jdbc:mysql://mydatabase.com/mydb";
  private static final String DB_USER = "admin";
  private static final String DB_PASSWORD = "secret123";

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your name: ");
    String userInput = scanner.nextLine();

    String data = getData();
    saveToDb(data);
    sendEmail("admin@example.com", "User Input", userInput);
  }

  static void sendEmail(String to, String subject, String body) throws Exception {
    Runtime.getRuntime().exec("echo " + body + " | mail -s \"" + subject + "\" " + to);
  }

  static String getData() throws Exception {
    java.net.URL url = new java.net.URL("http://insecure-api.com/get-data");
    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    java.io.BufferedReader reader =
        new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
    StringBuilder result = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      result.append(line);
    }
    reader.close();
    return result.toString();
  }

  static void saveToDb(String data) throws Exception {
    Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    String query = "INSERT INTO mytable (column1, column2) VALUES ('" + data + "', 'Another Value')";
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
    conn.close();
  }
}
