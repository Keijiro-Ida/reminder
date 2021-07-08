package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.Goal;
import model.PostGoal;
import model.Users;
public class GoalDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
	//データベースの情報を取得
	
	public int goalCreate(PostGoal postGoal) {
		int result = 0;
		
		try(Connection conn = DriverManager.getConnection(
								bundle.getString("JDBC_URL_MYSQL"),
									bundle.getString("DB_USER2"),
										bundle.getString("DB_PASS2"))){
			
			String sql = "INSERT INTO GOAL(USRID,TEXT,GOALTIME,REMINDTIME) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postGoal.getUsrId());
			pstmt.setString(2, postGoal.getText());
			pstmt.setTimestamp(3,  postGoal.getGoalTime());
			pstmt.setTimestamp(4, postGoal.getRemindTime());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	
	public Goal findGoal(PostGoal postGoal) {
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){

			String sql = "SELECT * FROM GOAL WHERE USRID=? AND TEXT = ? AND GOALTIME = ? AND REMINDTIME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postGoal.getUsrId());
			pstmt.setString(2,  postGoal.getText());
			pstmt.setTimestamp(3, postGoal.getGoalTime());
			pstmt.setTimestamp(4,  postGoal.getRemindTime());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String usrId = rs.getString("USRID");
				String goalId = rs.getString("GOALID");
				String text = rs.getString("TEXT");
				Timestamp goalTime = rs.getTimestamp("GOALTIME");
				Timestamp remindTime = rs.getTimestamp("REMINDTIME");
				
				Goal goal = new Goal(usrId, goalId, text, goalTime, remindTime);
				return goal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public List<Goal> findAll(Users users) {
		List<Goal> goalList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){
			
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
				if(i == 15) break; //目標は15個まで保持
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return goalList;
	}
	
	public int deleteGoal(Goal goal) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){
			
			String sql = "DELETE FROM GOAL WHERE GOALID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goal.getGoalId());
			result = pstmt.executeUpdate();
			return result;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int updateGoal(Goal goal) {
		int result = 0;
		try(Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
					bundle.getString("DB_USER"),
						bundle.getString("DB_PASS"))){
			
			String sql = "UPDATE GOAL SET TEXT = ?, REMINDTIME = ? WHERE GOALID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goal.getText());
			pstmt.setTimestamp(2, goal.getRemindTime());
			pstmt.setString(3, goal.getGoalId());
			
			result = pstmt.executeUpdate();
			return result; //1が登録成功、0が失敗
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1; //-1でデータベースのエラー
		}
	}
}
