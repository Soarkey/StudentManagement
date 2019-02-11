package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String level = null;
		//实例化UserDao对象
		UserDao userDao = new UserDao();
		User user = userDao.login(username, password);
		//判断是否登录成功
		if(user != null){//成功
			level = user.getLevel();
			if(level.equals("用户")){
				request.getSession().setAttribute("user", user);//将用户对象放到session中
				//转发到user.jsp中
				request.getRequestDispatcher("user.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("admin", user);//将管理员对象放到session中
				//转发到admin.jsp中
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			}	
		}else {//失败
			request.setAttribute("info"," 错误:用户名或密码错误！");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}

}
