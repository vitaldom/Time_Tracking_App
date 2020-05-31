package ua.kpi.model.dao;

import ua.kpi.model.dao.impl.JdbcDaoFactory;

public abstract class DaoFactory {

	public abstract UserDao createUserDao();
	public abstract ActivityDao createActivityDao();
	public abstract DeclarationDao createDeclarationDao();
	public abstract ActivityRequestDao createActivityRequestDao();

	private static class InstanceHolder {
		private static final DaoFactory INSTANCE = new JdbcDaoFactory();
	}

	public static DaoFactory getInstance() {
		return InstanceHolder.INSTANCE;
	}

	protected DaoFactory() {
	}
}
