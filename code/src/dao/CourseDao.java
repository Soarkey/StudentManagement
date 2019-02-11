package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.Course;
import model.Course_avg;
import model.Course_fail_rate;
import model.Course_ranking;
import utils.DBUtils;

public class CourseDao {
	// 获取所有课程的信息，用ArrayList返回
	public ArrayList<Course> query_all_course() {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from course order by cno;";
		ArrayList<Course> results = new ArrayList<Course>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course temp = new Course();
				temp.setCno(rs.getString("Cno"));
				temp.setCname(rs.getString("Cname"));
				temp.setCteacher(rs.getString("Cteacher"));
				temp.setCcredit(rs.getInt("Ccredit"));
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
	// 插入课程信息，返回一个int值表示状态,1：成功，0失败
	public int insert_course(String Cno, String Cname, String Cteacher, double Ccredit) {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into course values(?,?,?,?);";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Cno);
			ps.setString(2, Cname);
			ps.setString(3, Cteacher);
			ps.setDouble(4, Ccredit);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 删除课程信息，返回一个int值表示状态,1：成功，0失败
	public int delete_course(String Cno) {
		Connection conn = DBUtils.getConnection();
		String sql = "delete from course where Cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, Cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	//修改课程信息，返回一个int值表示状态,1：成功，0失败
	public int alter_course(String cno,String after_cno,String after_cname,String after_cteacher,double after_ccredit) {
		Connection conn = DBUtils.getConnection();
		String sql = "update course set cno = ?,cname = ?,cteacher = ?,ccredit = ? where cno = ?;";
		int flag = 0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, after_cno);
			ps.setString(2, after_cname);
			ps.setString(3, after_cteacher);
			ps.setDouble(4, after_ccredit);
			ps.setString(5, cno);
			flag = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn);
		}
		return flag;
	}
	// 查询课程平均分信息，返回一个ArrayLst集合
	public ArrayList<Course_avg> course_avg() {
		Connection conn = DBUtils.getConnection();
		String sql = "select sc.cno cno,cname,avg(grade) avg from course,sc where course.cno = sc.cno group by cno order by cno;";
		ResultSet result = null;
		ArrayList<Course_avg> course_avg = new ArrayList<Course_avg>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			result = ps.executeQuery();
			while(result.next()){
				Course_avg temp = new Course_avg();
				temp.setCno(result.getString("Cno"));
				temp.setCname(result.getString("Cname"));
				temp.setAvg(result.getDouble("avg"));
				course_avg.add(temp);
			}
			ps.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_avg;
	}
	//查询课程不及格率，返回一个ArrayList集合
	public ArrayList<Course_fail_rate> fail_rate(){
		Connection conn = DBUtils.getConnection();
		String sql = "select cno,(select cname from course where cno = x.cno) cname,cast(100.0*(select count(sno) from sc where grade < 60 and cno = x.cno)/(select count(sno) from sc where cno = x.cno) as decimal(18,2)) rate from sc x group by cno order by cno;";
		ArrayList<Course_fail_rate> fail_rate = new ArrayList<Course_fail_rate>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Course_fail_rate temp = new Course_fail_rate();
				temp.setCno(rs.getString("cno"));
				temp.setCname(rs.getString("cname"));
				temp.setFail_rate(rs.getDouble("rate"));
				fail_rate.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return fail_rate;
	}
	//查询课程排名情况,返回一个ArrayList集合
	public ArrayList<Course_ranking> course_ranking(String cno){
		Connection conn = DBUtils.getConnection();
		String sql = "select student.Sno Sno,Dname,Clname,Sname,Ssex,Sage,Grade from department,class,student,sc where student.sno = sc.sno and class.Clno = student.Clno and department.Dno = class.Dno and cno = '"+cno+"' order by grade desc;";
		ArrayList<Course_ranking> course_ranking = new ArrayList<Course_ranking>();
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Course_ranking temp = new Course_ranking();
				temp.setSno(rs.getString("Sno"));
				temp.setDname(rs.getString("Dname"));
				temp.setClname(rs.getString("Clname"));
				temp.setSname(rs.getString("Sname"));
				temp.setSsex(rs.getString("Ssex"));
				temp.setSage(rs.getInt("Sage"));
				temp.setGrade(rs.getDouble("Grade"));
				course_ranking.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return course_ranking;
	}
}

