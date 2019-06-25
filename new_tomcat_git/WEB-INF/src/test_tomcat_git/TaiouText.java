package test_tomcat_git;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiouText extends CardText
{
	DataBaseConnectRead DBCR = new DataBaseConnectRead();

	DataBaseConnectCard DBCC = new DataBaseConnectCard();
	ResultSet rs;

	void taioucreate(int room)
	{
		file = new File("var/www/html/"+room+"/taiou.txt");//room_idを使用してファイルを作成
		/*データベースにアクセスし対応表を確保する*/
		rs = DBCC.cardinfo();
		/*取得したデータをもとにテキストファイルに出力する*/
		cardlist = new int[20][6];
		line = new String[20];

		for(int i = 0;i<cardlist.length;i++)
		{
			for(int j = 0;j<cardlist[i].length;j++)
			{
				cardlist[i][j]=-1;
			}
		}

		try
		{
			rs.next();
			for(int i =0;i<cardlist.length;i++)
			{
				cardlist[i][0] = rs.getInt("card_id");
				String[] array = rs.getString("taio_id").split(",");

				for(int j = 0,k=1;j<array.length;j++,k++)
				{
					cardlist[i][k] = Integer.parseInt(array[j]);
				}
				rs.next();
			}
		}

		catch (SQLException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		for(int i =0;i<line.length;i++)
		{
			for(int j = 0;j<cardlist[i].length;i++)
			{
				line[i] += cardlist[i][j];
				if((i+1)<cardlist[i].length)
				{
					line[i] += ",";
				}
			}
		}

		for(int i =0;i<line.length;i++)
		{
			writetext += line[i];
			if((i+1)<line.length)
			{
				writetext += ",s,";
			}
		}

		try
		{
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
			pw.println(writetext);
		}
		catch(Exception e)
		{

		}
		finally
		{
			bwclose();
		}
	}
}