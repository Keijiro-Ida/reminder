package model;
import DAO.UsersDAO;

public class LoginLogic {
	public Users execute(Login login) { //ログイン情報から照合
		UsersDAO dao = new UsersDAO();
		Users users = dao.findByLogin(login); 
		return users;
	}
}
