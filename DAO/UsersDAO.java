package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;

import model.Login;
import model.SignUp;
import model.Users;

public class UsersDAO {
	
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
	//データベースの情報を取得
	
	public Users findByLogin(Login login) {
		Users users = null;
		
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){

			String sql = "SELECT USRID, MAIL, PASS, DEFTIME FROM USERS WHERE MAIL = ? AND PASS = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getMail());
			pstmt.setString(2, login.getPass());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String usrId = rs.getString("USRID");
				String mail = rs.getString("MAIL");
				String pass = rs.getString("PASS");
				LocalTime defTime = rs.getTime("DEFTIME").toLocalTime();
				users = new Users(usrId, mail, pass, defTime);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}
	
	public int createUsers(SignUp signUp) {
		
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){

			String sql = "INSERT INTO USERS(MAIL, PASS, DEFTIME) VALUES ( ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signUp.getMail());
			pstmt.setString(2, signUp.getPass());
			pstmt.setTime(3, Time.valueOf(signUp.getDefTime()));
			result = pstmt.executeUpdate();
			
			return result;
					
			}catch(SQLException e) {
				e.printStackTrace();
				return -1;
		}
		
	}
	
	public int updateUsers(Users users) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){
			
			String sql = "UPDATE USERS SET MAIL = ? , PASS = ?, DEFTIME = ? WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getMail());
			pstmt.setString(2, users.getPass());
			pstmt.setTime(3, Time.valueOf(users.getDefTime()));
			pstmt.setString(4, users.getUsrId());
			result = pstmt.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	} 

}
