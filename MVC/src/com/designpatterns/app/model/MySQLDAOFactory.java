package com.designpatterns.app.model;

public class MySQLDAOFactory extends DAOFactory {
	/* (non-Javadoc)
	 * @see com.designpatterns.app.model.DAOFactory#getPersonDAO()
	 */
	@Override
	public PersonDAO getPersonDAO(){
		return new MySQLPersonDAO();
	}
	
	/* (non-Javadoc)
	 * @see com.designpatterns.app.model.DAOFactory#getLogDAO()
	 */
	@Override
	public LogDAO getLogDAO(){
		return new MySQLLogDAO();
	}
}
