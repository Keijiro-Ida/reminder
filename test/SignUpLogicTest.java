package test;

import java.time.LocalTime;

import model.SignUp;
import model.SignUpLogic;

public class SignUpLogicTest {

	public static void main(String[] args) {
		testExecute1();
		testExecute2();

	}
	public static void testExecute1() {
		LocalTime now = LocalTime.now();
		SignUp signUp = new SignUp("keijiro", "11223344", now);
		SignUpLogic bo = new SignUpLogic();
		int result = bo.execute(signUp);
		if(result == 1) {
			System.out.println("testExecute1:成功");
		} else {
			System.out.println("testExecute2:失敗");
		}
	}
	public static void testExecute2() {
		LocalTime now = LocalTime.now();
		SignUp signUp = new SignUp("keijiro", "11223344", now);
		SignUpLogic bo = new SignUpLogic();
		int result = bo.execute(signUp);
		if(result != 1) {
			System.out.println("testExecute2:成功");
		} else {
			System.out.println("testExecute2:失敗");
		}
	}

}
