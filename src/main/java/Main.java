import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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


    get("/all-assets", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "assets.ftl");
        }, new FreeMarkerEngine());


    get("/all-users", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "users.ftl");
        }, new FreeMarkerEngine());


    Gson gson = new Gson();

    get("/assets", (req, res) -> {
          List<Object> data = new ArrayList<>();
          Connection connection = null;
          try {
          connection = DatabaseUrl.extract().getConnection();
          Statement stmt = connection.createStatement();

          ResultSet rs = stmt.executeQuery("SELECT * FROM assets");

          while (rs.next()) {
            Map<String, Object> assets = new HashMap<>();
          
          assets.put("assetName", rs.getString("assetName"));
          assets.put("type", rs.getString("type"));
          assets.put("serialNumber", rs.getString("serialNumber"));
          data.add(assets);        
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


get("/reports", (request, response) -> {
     Map<String, Object> attributes = new HashMap<>();
     attributes.put("title", "Reports");
     attributes.put("description", "Select a Report to View Details");
     attributes.put("inventoryactive", true);

     return new ModelAndView(attributes, "reports.ftl");
   }, new FreeMarkerEngine());


get("/reports/:report", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM reports");
        
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";                                                        
        xml += "<reports>";
        while (rs.next()) {
          xml += "<report>";
            xml += "<reportName>" + rs.getString("reportName") + "</reportName>";
            xml += "<department>" + rs.getString("department") + "</department>"; 
            xml += "<deptManager>" + rs.getString("deptManager") + "</deptManager>";
            xml += "<contactEmail>" + rs.getString("contactEmail") + "</contactEmail>";    
            xml += "<reportFrequency>" + rs.getString("reportFrequency") + "</reportFrequency>";
            xml += "<reportType>" + rs.getString("reportType") + "</reportType>";   
            xml += "<reportPriority>" + rs.getString("reportPriority") + "</reportPriority>";
            xml += "<generatedDay>" + rs.getString("generatedDay") + "</generatedDay>";  
            xml += "<generatedDayOfMonth>" + rs.getString("generatedDayOfMonth") + "</generatedDayOfMonth>";
            xml += "<generatedYear>" + rs.getString("generatedYear") + "</generatedYear>";  
          xml += "</report>";        
        }
        xml += "</reports>";
        System.out.println(xml);
        res.type("text/xml");
        return xml;
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return attributes;
      } finally {
        if (connection != null)
          try {
            connection.close();
          } catch (SQLException e) {
          }
      }
      
    });


post("/all-users", (req, res) -> {
      List<Object> data = new ArrayList<>();
      Connection connection = null;
      try {
        connection = DatabaseUrl.extract().getConnection();
        Statement stmt = connection.createStatement();
        JSONObject obj = new JSONObject(req.body());
        String users = obj.getString("Name");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users where name ='" + users + "'");

        while (rs.next()) {
          Map<String, Object> Name = new HashMap<>();
          
          Name.put("name", rs.getString("name"));
          Name.put("role", rs.getString("role"));
          Name.put("email", rs.getString("email"));
          data.add(Name);        
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
