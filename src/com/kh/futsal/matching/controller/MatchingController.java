package com.kh.futsal.matching.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MatchingController
 */
@WebServlet("/matching/*")
public class MatchingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MatchingController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		System.out.println(uriArr[uriArr.length-1]);
		switch (uriArr[uriArr.length-1]) {
		
		case "team-list":
			teamList(request,response);
			break;
		case "team-match-form":
			teamMathForm(request,response);
			break;
		case "team-modify":
			teamModify(request,response);
			break;
		case "mercenary-list":
			mercenaryList(request,response);
			break;
		case "mercenary-match-form":
			mercenaryMathForm(request,response);
			break;
		case "mercenary-modify":
			mercenaryModify(request,response);
			break;


		default:
		}
		
	}


	private void mercenaryModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-modify").forward(request, response);
	}
	private void mercenaryMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-match-form").forward(request, response);
	}
	private void mercenaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/mercenary/mercenary-list").forward(request, response);
	}
	
	private void teamModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-modify").forward(request, response);
	}
	private void teamMathForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-match-form").forward(request, response);
	}
	private void teamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/matching/team/team-list").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
