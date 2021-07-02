package model;
import DAO.UsersDAO;

public class LoginLogic {
	public Users execute(Login login) {
		UsersDAO dao = new UsersDAO();
		Users users = dao.findByLogin(login);
		return users;
	}
}
