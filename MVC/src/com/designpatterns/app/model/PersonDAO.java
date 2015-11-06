package com.designpatterns.app.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//CRUD - create, retrieve, update, delete

public class PersonDAO {
	
	public void addPerson(Person person) throws SQLException{
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("Insert into people (name, password) values (?,?)");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		
		p.executeUpdate();
		
		p.close();
	}
	
	public Person getPerson(int id) throws SQLException{
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select id, name, password from people where id=? order by id";
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		
		selectStatement.setInt(1, id);
		
		ResultSet results = selectStatement.executeQuery();
		
		Person person = null;
		
		if(results.next()){
			String name = results.getString("name");
			String password = results.getString("password");
			
			person = new Person(id, name, password);
		}
		
		results.close();
		selectStatement.close();
		
		return person;
	}
	
	public List<Person>getPeople() throws SQLException{
		List<Person> people = new ArrayList();
		
		Connection conn = Database.getInstance().getConnection();

		String sql = "select id, name, password from people order by id";
		Statement selectStatement = conn.createStatement();
				
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()){
			int id = results.getInt("Id");
			String name = results.getString("name");
			String password = results.getString("password");
			
			Person person = new Person(id, name, password);
			people.add(person);
		}
		
		results.close();
		selectStatement.close();
		
		return people;	}
	
	public int updatePerson(Person person) throws SQLException{
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement p = conn.prepareStatement("update people set name=?, password=? where id=?");
				
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		p.setInt(3, person.getId());
		int updated = p.executeUpdate();

		return updated;
	}
	
	public int deletePerson(int id) throws SQLException{
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("delete from people where id=?");

		p.setInt(1, id);
		
		int deleted = p.executeUpdate();
		
		p.close();
		
		return deleted;
	}
}
