package model;

import DAO.UsersDAO;

public class UpdateUsersLogic {
	public boolean execute(Users users) {
		UsersDAO dao = new UsersDAO();
		int result = dao.updateUsers(users);
		return  result == 1;
	}

}
