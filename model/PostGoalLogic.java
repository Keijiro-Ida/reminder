package model;
import DAO.GoalDAO;
public class PostGoalLogic {
	public  Goal execute(PostGoal postGoal) { //目標入力からGoal IDを生成し、Goalインスタンスを作成
		GoalDAO dao = new GoalDAO();
		int result =  dao.goalCreate(postGoal);
		if(result == 1) { //データベース登録成功時
			Goal goal = dao.findGoal(postGoal);
			return goal;
		}else { //登録失敗時
			return null;
		}
	}

}
