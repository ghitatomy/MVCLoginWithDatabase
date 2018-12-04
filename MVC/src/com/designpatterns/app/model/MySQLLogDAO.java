package com.designpatterns.app.model;

import java.util.List;

public class MySQLLogDAO implements LogDAO {
	@Override
	public void addEntry(String message){ }
	
	@Override
	public List<Log> getEntries(int number){
		return null;
	}
}
