package com.kh.futsal.team.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TeamController
 */
@WebServlet("/team/*")
public class TeamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length-1]) {
		
		case "main":
			teamMain(request,response);
			break;
		case "join-team":
			joinTeam(request,response);
			break;
		case "create-form":
			createForm(request,response);
			break;
			
		case "modify":
			teamModify(request,response);
			break;
		case "manage":
			teamManage(request,response);
			break;
		case "total-score":
			totalScore(request,response);
			break;
		case "team-board":
			teamBoard(request,response);
			break;
		case "delete-team":
			deleteTeam(request,response);
			break;

		default:
		}
	}

	private void deleteTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/delete-team").forward(request, response);
		
	}
	private void teamBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/team-board").forward(request, response);
		
	}
	private void totalScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/total-score").forward(request, response);
		
	}
	private void teamManage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/manage").forward(request, response);
		
	}
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/managing/modify").forward(request, response);
		
	}
	private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/create-form").forward(request, response);
		
	}
	private void joinTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/join-team").forward(request, response);
		
	}
	private void teamMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/team/main").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
