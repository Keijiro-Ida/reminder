package test;

import model.UpdateUsersLogic;
import model.Users;

public class UpdateUsersTest {

	public static void main(String[] args) {
		testExecute1();
		testExecute2();

	}
	public static void testExecute1() {
		Users users = new Users("5","idatt1122@me.com", "11221118");
		UpdateUsersLogic bo = new UpdateUsersLogic();
		boolean result = bo.execute(users);
		
		if(result) {
			System.out.println("test1成功");
		} else {
			System.out.println("test1失敗");
		}
	}
	public static void testExecute2() {
		Users users = new Users("1","idatt1122@gmail.com", "11221117");
		UpdateUsersLogic bo = new UpdateUsersLogic();
		boolean result = bo.execute(users);
		if(!result) {
			System.out.println("test2成功");
		} else {
			System.out.println("test2失敗");
		}
	}

}