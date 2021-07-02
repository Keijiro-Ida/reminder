package test;

import model.SignUp;
import model.SignUpLogic;

public class SignUpLogicTest {

	public static void main(String[] args) {
		testExecute1();
		testExecute2();

	}
	public static void testExecute1() {
		SignUp signUp = new SignUp("keijiro", "11223344");
		SignUpLogic bo = new SignUpLogic();
		boolean result = bo.execute(signUp);
		if(result) {
			System.out.println("testExecute1:成功");
		} else {
			System.out.println("testExecute2:失敗");
		}
	}
	public static void testExecute2() {
		SignUp signUp = new SignUp("keijiro", "11223344");
		SignUpLogic bo = new SignUpLogic();
		boolean result = bo.execute(signUp);
		if(!result) {
			System.out.println("testExecute2:成功");
		} else {
			System.out.println("testExecute2:失敗");
		}
	}

}
