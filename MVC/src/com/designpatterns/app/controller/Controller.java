package com.designpatterns.app.controller;

import java.sql.SQLException;

import com.designpatterns.app.model.DAOFactory;
import com.designpatterns.app.model.Model;
import com.designpatterns.app.model.Person;
import com.designpatterns.app.model.PersonDAO;
import com.designpatterns.app.view.LoginFormEvent;
import com.designpatterns.app.view.LoginListener;
import com.designpatterns.app.view.View;

public class Controller implements LoginListener{
	private View view; 
	private Model model;
		
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void loginPerformed(LoginFormEvent event) {

		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		PersonDAO personDAO = factory.getPersonDAO();

		System.out.println("Login event received:" + event.getName() +":" + event.getPassword());
		
		String name = event.getName();
		String password = event.getPassword();
		
		try {
			personDAO.addPerson(new Person(name,password));
			personDAO.getPerson(0);
			personDAO.updatePerson(new Person(9,"Guatemala","Absurdistan"));
			personDAO.deletePerson(14);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
