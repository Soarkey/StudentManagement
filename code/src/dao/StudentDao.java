package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Student;
import utils.DBUtils;

public class StudentDao {
	// 获取所有学生的信息，用ArrayList返回
	public ArrayList<Student> query_all_student() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from student order by sno;";
		ArrayList<Student> results = new ArrayList<Student>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Student temp = new Student();
				temp.setSno(rs.getString("sno"));
				temp.setSname(rs.getString("sname"));
				temp.setSsex(rs.getString("ssex"));
				temp.setSage(rs.getInt("sage"));
				temp.setClno(rs.getString("clno"));
				results.add(temp);
			}
			// 关闭资源
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return results;
	}
	// 插入学生信息，返回一个int值表示状态,1：成功，0失败
	public int insert_student(String Sno,String Sname,String Ssex,int Sage,String Clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into student values(?,?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Sno);
			ps.setString(2, Sname);
			ps.setString(3, Ssex);
			ps.setInt(4, Sage);
			ps.setString(5, Clno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除学生信息，返回一个int值表示状态,1：成功，0失败
	public int delete_student(String sno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from student where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 修改学生信息，返回一个int值表示状态,1：成功，0失败
	public int alter_class(String sno, String after_sno,String after_sname,String after_ssex,int after_sage,String after_clno) {
		Connection conn = DBUtils.getConnection();
		String sql = "update student set sno = ?,sname = ?,ssex = ?,sage = ?,clno = ? where sno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_sno);
			ps.setString(2, after_sname);
			ps.setString(3, after_ssex);
			ps.setInt(4, after_sage);
			ps.setString(5, after_clno);
			ps.setString(6, sno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}

}
