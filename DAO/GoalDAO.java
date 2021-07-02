package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Goal;
import model.PostGoal;
import model.Users;
public class GoalDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/reminder";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public int goalCreate(PostGoal postGoal) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
			String sql = "INSERT INTO GOAL(USRID,TEXT,GOALTIME,REMINDTIME) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postGoal.getUsrId());
			pstmt.setString(2, postGoal.getText());
			pstmt.setTimestamp(3,  postGoal.getGoalTime());
			pstmt.setTimestamp(4, postGoal.getRemindTime());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	
	public List<Goal> findAll(Users users) {
		List<Goal> goalList = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "SELECT * FROM GOAL WHERE USRID=? ORDER BY GOALTIME DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getUsrId());
			ResultSet rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				String usrId = rs.getString("USRID");
				String goalId = rs.getString("GOALID");
				String text = rs.getString("TEXT");
				Timestamp goalTime = rs.getTimestamp("GOALTIME");
				Timestamp remindTime = rs.getTimestamp("REMINDTIME");
				
				Goal goal = new Goal(usrId, goalId, text, goalTime, remindTime);
				goalList.add(goal);
				i++;
				if(i == 10) break;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return goalList;
	}
	
	public int deleteGoal(Goal goal) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			String sql = "DELETE FROM GOAL WHERE GOALID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goal.getGoalId());
			result = pstmt.executeUpdate();
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
	
	public int updateGoal(Goal goal) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			String sql = "UPDATE GOAL SET TEXT = ?, REMINDTIME = ? WHERE GOALID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goal.getText());
			pstmt.setTimestamp(2, goal.getRemindTime());
			pstmt.setString(3, goal.getGoalId());
			
			result = pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}
}
