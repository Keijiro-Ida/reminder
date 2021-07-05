package test;
import java.time.LocalTime;

import DAO.UsersDAO;
import model.SignUp;;

public class UsersDAOTest2 {
	public static void main(String[] args) {
		testSignUpUsers1();
		testSignUpUsers2();
	}
	
	public static void testSignUpUsers1() {
		LocalTime now = LocalTime.now();
		SignUp signUp = new SignUp("idatt1122@yahoo.co.jp", "11221118", now);
		UsersDAO dao = new UsersDAO();
		int result = dao.createUsers(signUp);
		if(result == 1) {
			System.out.println("testSignIn1:成功");
		}else {
			System.out.println("testSignIn2:失敗");
		}
	}
	
	public static void testSignUpUsers2() {
		LocalTime now = LocalTime.now();
		SignUp signIn = new SignUp("idatt1122@gmail.com", "11221118", now);
		UsersDAO dao = new UsersDAO();
		int result = dao.createUsers(signIn);
		if(result != 1) {
			System.out.println("testSignIn1:成功");
		}else {
			System.out.println("testSignIn2:失敗");
		}
	}
}
