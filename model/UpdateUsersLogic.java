package model;

import DAO.UsersDAO;

public class UpdateUsersLogic { //ユーザー情報の更新
	public int execute(Users users) {
		UsersDAO dao = new UsersDAO();
		int result = dao.updateUsers(users);
		return  result;
	}

}
