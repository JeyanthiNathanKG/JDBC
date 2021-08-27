import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Example {
	public static void main(String []args) throws SQLException,ClassNotFoundException{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter The userName: ");
		String user_name=sc.next();
		System.out.print("Enter The Password : ");
		String password=sc.next();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user_name,password);
		System.out.println("Connected successfully !!");
		Statement st=c.createStatement();
		/*String sql="create table Movie(Name varchar2(20),actor varchar2(20),actress varchar2(20),Director Varchar2(20),ReleaseYear number)";
		st.execute(sql);
		System.out.println("Table Created Successfully");*/
		//Read the Input From the user
		System.out.print("Enter the Number of Movie to be Inserted into Database : ");
		int n=sc.nextInt();
		PreparedStatement p=c.prepareCall("insert into Movie values(?,?,?,?,?)");		
		for(int i=0;i<n;i++)
		{
			System.out.print("Enter the Movie Name (in String): ");
			String movie=sc.next();
			p.setString(1, movie);
			System.out.print("Enter the Actor Name(in characters): ");
			String actor_name=sc.next();
			p.setString(2, actor_name);
		    System.out.print("Enter the Actress Name(in Characters):");
		    String actress_name=sc.next();
		    p.setString(3, actress_name);
		    System.out.print("Enter the Director Name(in Characters):");
		    String director_name=sc.next();
		    p.setString(4, director_name);
			System.out.print("Enter the Release Year(in NUmbers): ");
		    int date=sc.nextInt();
		    p.setInt(5, date);
		    p.execute();
		}
		System.out.println("Inserted Successfully");
		System.out.println("Enter the Actors Name For see the movie (note must be in lowerCase): ");
		String s=sc.next();
		PreparedStatement pst=c.prepareCall("select Name from Movie where actor=?");
		pst.setString(1, s);
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			System.out.println("Movie Name = "+rs.getString(1));
		}
		rs.close();
		pst.close();
	}
}
