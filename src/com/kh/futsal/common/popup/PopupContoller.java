package com.kh.futsal.common.popup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.common.file.FileDTO;
import com.kh.futsal.team.model.dto.Team;
import com.kh.futsal.team.model.service.TeamService;

@WebServlet("/popup/*")
public class PopupContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TeamService ts = new TeamService();
	
    public PopupContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		case "team-info":
			teamInfo(request,response);
			break;

		default:
		}
	}

	private void teamInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Team team = ts.selectTeamByTmCode(request.getParameter("tmCode"));
		FileDTO file = ts.selectFile(request.getParameter("tmCode"));
		System.out.println(team.toString());
		System.out.println(file.toString());
		request.getSession().setAttribute("teamPopup", team);
		request.getSession().setAttribute("filePopup", file);
		
		if(team.getTmCode()!=null && team.getDelDate()==null) {
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
