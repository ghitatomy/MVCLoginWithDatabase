package com.designpatterns.app.controller;

import com.designpatterns.app.model.Database;
import com.designpatterns.app.model.Model;
import com.designpatterns.app.model.Person;
import com.designpatterns.app.view.AppListener;
import com.designpatterns.app.view.CreateUserEvent;
import com.designpatterns.app.view.CreateUserListener;
import com.designpatterns.app.view.SaveListener;
import com.designpatterns.app.view.View;

public class Controller implements CreateUserListener, SaveListener,AppListener {
	private View view;
	private Model model;

	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	@Override
	public void onUserCreated(CreateUserEvent event) {
		model.addPerson(new Person(event.getName(), event.getPassword()));
	}
	@Override
	public void onSave() {
		try {
			model.save();
		} catch (Exception e) {
			view.showError("Error saving to database.");
		}
	}
	@Override
	public void onOpen() {
		try {
			Database.getInstance().connect();
		} catch (Exception e) {
			view.showError("Cannot connect to database.");
		}
		try {
			model.load();
		} catch (Exception e) {
			view.showError("Error loading data from database.");
		}
	}
	@Override
	public void onClose() {
		Database.getInstance().disconnect();
	}
}
