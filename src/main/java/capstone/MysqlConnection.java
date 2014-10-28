package capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MysqlConnection {
// the database is named db_cap
// the table is named `pics` and has 4 cols
// all string/text: id, name, picpath, txtpath
	private Connection connect = null;
	private Statement statement = null;
	//private PreparedStatement preparedStatement = null;
	//private ResultSet resultSet = null;
	
	MysqlConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connect = DriverManager.getConnection("jdbc:mysql://:localhost:3306/db_cap" + "user=root&password=123456");
			statement = connect.createStatement();
			
		}	catch (Exception e) {
			System.out.println("Erro: "+e);
		} /*finally {
			closeall();
		}*/
	} 
}
