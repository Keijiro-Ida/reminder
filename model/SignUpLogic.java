package model;
import DAO.UsersDAO;

public class SignUpLogic {
	public boolean execute(SignUp signIn) {
		UsersDAO dao = new UsersDAO();
		int result = dao.createUsers(signIn);
		return result == 1;
	}
}
