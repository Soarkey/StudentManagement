package model;

import java.io.Serializable;

public class Course_fail_rate implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Cno;//课程号
	private String Cname;//课程名称
	//private String Cteacher;//执教老师
	//private int Ccredit;//学分
	private double fail_rate;//不及格率
	
	public String getCno() {
		return Cno;
	}
	public void setCno(String cno) {
		Cno = cno;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public double getFail_rate() {
		return fail_rate;
	}
	public void setFail_rate(double fail_rate) {
		this.fail_rate = fail_rate;
	}
	
}
