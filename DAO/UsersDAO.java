package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.SignUp;
import model.Users;

public class UsersDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/reminder";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Users findByLogin(Login login) {
		Users users = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT USRID, MAIL, PASS FROM USERS WHERE MAIL = ? AND PASS = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getMail());
			pstmt.setString(2, login.getPass());
		
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String usrId = rs.getString("USRID");
				String mail = rs.getString("MAIL");
				String pass = rs.getString("PASS");
				
				users = new Users(usrId, mail, pass);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}
	
	public int createUsers(SignUp signUp) {
		
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "INSERT INTO USERS(MAIL, PASS) VALUES ( ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signUp.getMail());
			pstmt.setString(2, signUp.getPass());
			
			result = pstmt.executeUpdate();
			
			
			}catch(SQLException e) {
				e.printStackTrace();
				return result;
		}
		return result;
		
	}
	
	public int updateUsers(Users users) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "UPDATE USERS SET MAIL = ? , PASS = ? WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getMail());
			pstmt.setString(2, users.getPass());
			pstmt.setString(3, users.getUsrId());
			result = pstmt.executeUpdate();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	} 

}
