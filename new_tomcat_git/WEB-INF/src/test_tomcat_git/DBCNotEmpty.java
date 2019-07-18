package test_tomcat_git;

import java.sql.SQLException;
import java.sql.Statement;

public class DBCNotEmpty extends DataBaseConnectRead
{

	int user_id;
	int[] roominfo = new int[2];

	int userIDNotempty()
	{
		Statement stmt = CC.createstatement(conn = CC.createconnection());

		try
		{
			rs = stmt.executeQuery("SELECT MAX(user_id) FROM user;");
			//結果の挿入
			rs.next();
			user_id = rs.getInt("user_id");
			rs.close();
			user_id++;
			stmt.executeUpdate("INSERT INTO user VALUES("+user_id+",NULL); ");

		}
		catch(SQLException e)
		{
			System.out.println("ユーザーIDが追加できなかったお");
		}
		finally
		{
			CC.close();
		}
		System.out.print("追加されたuser_idだお"+user_id);

		return user_id;
	}

	int[] RoomNotempty(String sql)
	{
		Statement stmt = CC.createstatement(conn = CC.createconnection());
		Result = new int[2];

		try
		{
			rs = stmt.executeQuery("SELECT MAX(room_id) FROM room;");
			//結果の挿入
			rs.next();
			Result[0] = rs.getInt("room_id");
			rs.close();
			Result[0]++;
			stmt.executeUpdate("INSERT INTO room VALUES("+Result[0]+",1,0);");
			stmt.executeUpdate("INSERT INTO room VALUES("+Result[0]+",2,0);");

			rs = stmt.executeQuery(sql);
			rs.next();
			Result[1] = rs.getInt("player_number");

		}
		catch(SQLException e)
		{
			System.out.println("ルームが追加できなかったお");
		}
		finally
		{
			CC.close();
		}
		System.out.print("追加されたroomだお"+Result[0]);

		return Result;
	}
}