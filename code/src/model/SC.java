package model;

import java.io.Serializable;

public class SC implements Serializable {
			
	private static final long serialVersionUID = 1L;

	private String Sno;//学号
	private String Sname;//姓名
	private String Ssex;//性别
	private int Sage;//年龄
	private String Cno;//课程号
	private String Cname;//课程名称
	private double Grade;//成绩
	
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getSname() {
		return Sname;
	}
	public void setSname(String sname) {
		Sname = sname;
	}
	public String getSsex() {
		return Ssex;
	}
	public void setSsex(String ssex) {
		Ssex = ssex;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int i) {
		Sage = i;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setGrade(double grade) {
		Grade = grade;
	}
	public String getSno() {
		return Sno;
	}
	public void setSno(String sno) {
		Sno = sno;
	}
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public double getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
}
