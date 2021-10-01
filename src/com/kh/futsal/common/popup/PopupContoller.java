package com.kh.futsal.common.popup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
		if(team.getTmCode()!=null) {
			String tmName = team.getTmName();
			String tmGrade = team.getTmGrade();
			String localCode = team.getLocalCode();
			String tmInfo = (team.getTmInfo()!=null)?team.getTmInfo():"";
			String filePath = (file.getFlIdx()!=null)?request.getContextPath()+"/img/team/"+file.getSavePath()+file.getRenameFileName():request.getContextPath()+"/img/team/no-img.jpg";
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("cache-control", "no-cache, no-store");
			response.getWriter().print(tmName+"[=dividing line=]"+tmGrade+"[=dividing line=]"+localCode+"[=dividing line=]"+tmInfo+"[=dividing line=]"+filePath);
		} else {
			response.getWriter().print("disable");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
