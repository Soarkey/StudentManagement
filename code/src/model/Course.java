package model;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Cno;//课程号
	private String Cname;//课程名称
	private String Cteacher;//执教老师
	private int Ccredit;//学分
	
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
	public String getCteacher() {
		return Cteacher;
	}
	public void setCteacher(String cteacher) {
		Cteacher = cteacher;
	}
	public int getCcredit() {
		return Ccredit;
	}
	public void setCcredit(int ccredit) {
		Ccredit = ccredit;
	}
	
}
