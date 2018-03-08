package ooad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBMgr {
	
	private ResultSet rs=null;
	private Statement stmt=null;
	private Connection conn=null;
	
	//private PreparedStatement pstmt=null;
	
	public DBMgr(){
		getConnection();
		try {
			stmt=conn.createStatement();
			stmt.execute("use flyway"); ///
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void getConnection(){
		
		try { 
		       
		       if (conn == null || conn.isClosed()) {
					Class.forName("com.mysql.jdbc.Driver");
					//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyway", "root",
						//	"Lwh*1987");  //(url, user, password)(user is mysql user, not app user) 
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flyway", "root",
										"123789");
					} 
		    }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "JDBC Driver Not Found!");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
		}
	}
	
	
	
    public ResultSet selectAll(String tableName, String conditions) throws SQLException
    { 
    	String sql="select * from "+tableName+" where "+conditions;
    	System.out.print(sql);
    	rs=stmt.executeQuery(sql);
    	return rs;
    	
    }
	public ResultSet select(String[] desiredContent,String tableName, String conditions) throws SQLException
	{
		String sql="select ";
		for(int i=0;i<desiredContent.length-1;i++){
			sql+=desiredContent[i]+",";
		}
		
		sql+=desiredContent[desiredContent.length-1];
		sql+=" from "+tableName+" where "+conditions;
		System.out.println(sql);
		rs=stmt.executeQuery(sql);
		return rs;
		}
	
	public int add( String tableName, String[] attrs, String[] values)
	{
		String sql="insert into "+tableName+" (";
		for(int i=0;i<attrs.length-1;i++){
			sql+=attrs[i]+",";
		}
		sql+=attrs[attrs.length-1]+") values (";
		for(int i=0;i<values.length-1;i++){
			sql+=values[i]+",";
		}
		sql+=values[values.length-1]+")";
		System.out.print(sql); //
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Add operation finished!");
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Add operation Failed!");
			return -1;
		}
	}
	
	////temp add func/////////////////////////////////////
	public int addWithoutMessage(String tableName, String[] attrs, String[] values){
		String sql="insert into "+tableName+" (";
		for(int i=0;i<attrs.length-1;i++){
			sql+=attrs[i]+",";
		}
		sql+=attrs[attrs.length-1]+") values (";
		for(int i=0;i<values.length-1;i++){
			sql+=values[i]+",";
		}
		sql+=values[values.length-1]+")";
		System.out.print(sql); //
		try {
			stmt.executeUpdate(sql);
		//	JOptionPane.showMessageDialog(null, "Add operation finished!");
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, "Add operation Failed!");
			return -1;
		}
	}
	
	public int update(String tableName,String[] attrs, String[] values, String conditions)
	{
		String sql1="update "+tableName+" set ";
		String sql2=" where "+conditions;
		String sql3="";
		for(int i = 0; i < attrs.length-1; ++i) {
			sql3 += attrs[i] + "=" + values[i] + ",";
			
		}
		 sql3+=attrs[attrs.length-1] + "=" + values[attrs.length-1];
		 String sql= sql1 + sql3 + sql2;
		
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Update operation finished!");
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Update operation failed!");
			e.printStackTrace();
			return -1;
		}
	}
	
	public int updateWithoutMessage(String tableName,String[] attrs, String[] values, String conditions) 
	{
		String sql1="update "+tableName+" set ";
		String sql2=" where "+conditions;
		String sql3="";
		for(int i = 0; i < attrs.length-1; ++i) {
			sql3 += attrs[i] + "=" + values[i] + ",";
			
		}
		 sql3+=attrs[attrs.length-1] + "=" + values[attrs.length-1];
		 String sql= sql1 + sql3 + sql2;
		
		try {
			stmt.executeUpdate(sql);
			//JOptionPane.showMessageDialog(null, "Update operation finished!");
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, "Update operation failed!");
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	public int delete(String tableName,String conditions){
		
		String sql="delete from "+tableName+" where "+conditions;
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "Delete operation finished!");
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Delete operation Failed!");
			e.printStackTrace();
			return -1;
		}
	}
	
	public void destruction(){
	 try {
		if(stmt!=null) stmt.close();
		//if(pstmt!=null) pstmt.close();
		if(rs!=null) rs.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
