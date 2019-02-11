package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.User;
import utils.DBUtils;

public class UserDao {
	//判断用户在数据库中是否存在，存在返回true，不存在返回false
	public boolean userIsExist(String username) {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user where username = ?";
		try{
			//获取PreparedStatement对象
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);//给用户对象属性赋值
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//数据库中存在此用户
				return true;
			}
			//释放资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return false;
	}
	//用户登录，登录成功返回一个含值User对象,如果登录失败返回一个User空对象
	public User login(String username,String password) {
		Connection conn = DBUtils.getConnection();
		User user = null;
		String sql = "select * from user where username = ? and password = ?;";
		
		try {
			//获取PreparedStatement对象
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			//对sql参数进行动态赋值
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();//查询结果集
			
			//判断数据库中是否存在该用户
			if(rs.next()){
				user = new User();//实例化一个user对象
				//给用户对象赋值
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLevel(rs.getString("level"));
			}
			//释放资源
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	//用户注册，注册成功返回一个含值User对象，如果注册失败返回一个User空对象
	public User register(String username,String password,String level) {
		Connection conn = DBUtils.getConnection();
		User user = null;
		
		try {
			//判断数据库中是否存在该用户
			if(!userIsExist(username)){//不存在该用户，可以注册
				user = new User();//实例化一个user对象
				//给用户对象赋值
				user.setUsername(username);
				user.setPassword(password);
				user.setLevel(level);
				//将用户对象写入数据库
				Statement stmt = (Statement) conn.createStatement();
				stmt.executeUpdate("insert into user values('"+username+"','"+password+"','"+level+"');");
				stmt.close();//释放资源
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return user;
	}
	//获取用户的权限级别，若存在则返回级别(管理员，普通用户),若不存在返回空
	public String level(String username){
		Connection conn = DBUtils.getConnection();
		String sql = "select level from user where username = ?;";
		String level = null; 
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){//存在该用户
				level = rs.getString("level");
			}
			//关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return level;
	}
	//获取数据库中所有用户的信息，用ArrayList返回
	public ArrayList<User> query_all_user() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from user order by username;";
		ArrayList<User> results = new ArrayList<User>();
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				User temp = new User();
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setLevel(rs.getString("level"));
				results.add(temp);
			}
			//关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
	//插入用户信息，返回一个int值表示状态,1：成功，0失败
	public int insert_user(String username,String password,String level){
		Connection conn = DBUtils.getConnection();
		String sql = "insert into user values(?,?,?);";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, level);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	//删除用户信息，返回一个int值表示状态,1：成功，0失败
	public int delete_user(String username) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from user where username = ?;";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	//修改用户信息，返回一个int值表示状态,1：成功，0失败
	public int alter_user(String username,String after_username,String after_password,String after_level) {
		Connection conn = DBUtils.getConnection();
		String sql = "update user set username = ?,password = ?,level = ? where username = ?;";
		
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_username);
			ps.setString(2, after_password);
			ps.setString(3, after_level);
			ps.setString(4, username);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
}
