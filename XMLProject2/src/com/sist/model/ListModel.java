package com.sist.model;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.StudentDAO;
import com.sist.dao.StudentVO;

public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<StudentVO> list =StudentDAO.studentAllData();
		request.setAttribute("list", list);
		
		return "main/list.jsp";
	}

}
