package com.politech.ajax1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sqlite.SQLiteConfig;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> info(Locale locale, Model model) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("version", "1.0");
		return data;
	}

	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public @ResponseBody ArrayList<HashMap> userList(Locale locale, Model model) {
		Connection connection = null;
		ArrayList<HashMap> data = new ArrayList<HashMap>();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			connection = DriverManager.getConnection("jdbc:sqlite:/c:\\tomcat\\ajax.db", config.toProperties());
			
			String query = "SELECT * FROM users WHERE 1"; // name LIKE '%" + name + "%'
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String birthday = resultSet.getString("birthday");
				HashMap<String, String> row = new HashMap<String, String>();
				row.put("idx", "" + idx);
				row.put("name", name);
				row.put("address", address);
				row.put("birthday", birthday);
				data.add(row);
			}
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			
		}
		return data;
	}

	@RequestMapping(value = "/user_mod", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> userMod(Locale locale, Model model
			, @RequestParam String idx, @RequestParam String name, @RequestParam String address) {
		Connection connection = null;
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			connection = DriverManager.getConnection("jdbc:sqlite:/c:\\tomcat\\ajax.db", config.toProperties());
			
			String query = "UPDATE users SET name='" + name + "', address='" + address + "' WHERE idx=" + idx;
			Statement statement = connection.createStatement();
			int q = statement.executeUpdate(query);
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("result", "success");
		return result;
	}

	@RequestMapping(value = "/user_insert", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> userInsert(Locale locale, Model model
			, @RequestParam String name, @RequestParam String address) {
		Connection connection = null;
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			connection = DriverManager.getConnection("jdbc:sqlite:/c:\\tomcat\\ajax.db", config.toProperties());
			
			String query = "INSERT INTO users (name, address) VALUES ('" + name + "', '" + address + "')";
			Statement statement = connection.createStatement();
			int q = statement.executeUpdate(query);
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("result", "success");
		return result;
	}
}
