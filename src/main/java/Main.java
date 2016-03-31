import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;

import com.heroku.sdk.jdbc.DatabaseUrl;
import com.google.gson.Gson;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

  public static void main(String[] args) {


    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

//    get("/hello", (req, res) -> {
//      RelativisticModel.select();

//      String energy = System.getenv().get("ENERGY");

//      Amount<Mass> m = Amount.valueOf(energy).to(KILOGRAM);
//      return "E=mc^2: " + energy + " = " + m.toString();
//    });

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());




    get("/assets", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM assets");
        
        ArrayList<String> output = new ArrayList<String>();
          while (rs.next()) { 
           output.add(rs.getString("assetName"));
           output.add(rs.getString("type"));
           output.add(rs.getString("serialNumber"));
          }
        
        attributes.put("assets", output);
        return new ModelAndView(attributes, "assets.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine()
    ); 

Gson gson = new Gson();

get("/users", (req, res) -> {
      List<Object> data = new ArrayList<>();
      Connection connection = null;
      try {
        connection = DatabaseUrl.extract().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        while (rs.next()) {
          Map<String, Object> user = new HashMap<>();
          
          user.put("name", rs.getString("name"));
          user.put("role", rs.getString("role"));
          user.put("email", rs.getString("email"));
          data.add(user);        
        }
      } catch (Exception e) {
        data.add("There was an error: " + e);
      } finally {
        if (connection != null)
          try {
            connection.close();
          } catch (SQLException e) {
          }
      }
      return data;
    }, gson::toJson);


post("/reports", (req, res) -> {
      List<Object> data = new ArrayList<>();
      Connection connection = null;
      try {
        connection = DatabaseUrl.extract().getConnection();
        Statement stmt = connection.createStatement();
        JSONObject obj = new JSONObject(req.body());
        String reports = obj.getString("report");
        ResultSet rs = stmt.executeQuery("SELECT * FROM reports where report ='" + reports + "'");

        while (rs.next()) {
          Map<String, Object> report = new HashMap<>();
          
          report.put("report", rs.getString("report"));
          report.put("type", rs.getString("type"));
          report.put("frequency", rs.getString("frequency"));
          data.add(report);        
        }
      } catch (Exception e) {
        data.add("There was an error: " + e);
      } finally {
        if (connection != null)
          try {
            connection.close();
          } catch (SQLException e) {
          }
      }
      return data;
    }, gson::toJson);


  }

}
