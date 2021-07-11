package test;

import java.util.ResourceBundle;

public class propTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ResourceBundle resource = ResourceBundle.getBundle("properties.mail");
		System.out.println(resource.getString("mail"));
		System.out.println(resource.getString("pass"));
		
		ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
		System.out.println(bundle.getString("JDBC_URL"));
		System.out.println(bundle.getString("DB_USER"));
		System.out.println(bundle.getString("DB_PASS"));
		
		/*Properties properties = new Properties();
		String file1 = "/Applications/Eclipse_2021-03.app/Contents/workspace/reminder/src/main/java/test/reminder.properties";
		try {
			FileInputStream fis = new FileInputStream(file1);
			try {
				properties.load(fis);
				String a = properties.getProperty("mail");
				System.out.println(a); // 12345
				
				String b = properties.getProperty("pass");
				System.out.println(b); // testです

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		
	}

}
