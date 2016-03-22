package com.designpatterns.app.model;

import java.util.List;

public class MySQLLogDAO implements LogDAO {

	/* (non-Javadoc)/
	 * @see com.designpatterns.app.model.LogDAO#addEntry(java.lang.String)
	 */
	@Override
	public void addEntry(String message){
		
	}
	
	/* (non-Javadoc)
	 * @see com.designpatterns.app.model.LogDAO#getEntries(int)
	 */
	@Override
	public List<Log> getEntries(int number){
		return null;
	}
}
