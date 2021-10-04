package com.kh.futsal.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.futsal.alarm.model.service.AlarmService;
import com.kh.futsal.matching.controller.MatchingController;
import com.kh.futsal.matching.model.dto.MatchMaster;
import com.kh.futsal.matching.model.service.MatchingService;
import com.kh.futsal.team.model.service.TeamService;

/**
 * Servlet implementation class indexController
 */
@WebServlet("/index/*")
public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchingController matchController = new MatchingController();
	MatchingService matchingService = new MatchingService();
	AlarmService alarmService = new AlarmService();   
	TeamService teamService = new TeamService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MatchMaster> matchList = matchingService.matchListView(); 	
		for (MatchMaster matchMaster : matchList) {
			matchMaster.setTmRating(teamService.selectTmAvgRating(matchMaster.getTmCode()));
			matchMaster.setCheckMatchTime(matchController.matchTimeCheck(matchMaster.getMatchDate(),matchMaster.getMatchTime()));
			if (matchMaster.isCheckMatchTime()) {
				matchingService.matchRequset(matchMaster.getMmIdx(),"team");
			}
		}
		
		request.setAttribute("matchList", matchList);
		
		
		
		
		request.getRequestDispatcher("/index").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
