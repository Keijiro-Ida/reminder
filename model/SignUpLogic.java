package model;
import DAO.UsersDAO;

public class SignUpLogic { //新規登録入力からUsersを作成
	public int execute(SignUp signIn) {
		UsersDAO dao = new UsersDAO();
		int result = dao.createUsers(signIn);
		return result;
	}
}
