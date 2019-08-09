package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import com.sist.model.Model;
// DOM / SAX 

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map map=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// config => web.xml������ (ȯ�漳�� ���� => ServletConfig)
		String path=config.getInitParameter("contextConfigLocation");
		//System.out.println(path);
		try
		{
			// XML�� �����͸� �б� ���� => �Ľ�
			// 1. XML�ļ��� ���� 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// 2. �ļ���
			DocumentBuilder db=dbf.newDocumentBuilder();
			// XML���� ���� ������ ���� 
			Document doc=db.parse(new File(path));
			
			// XML => root�� Ȯ�� 
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName());//beans ==> table
			
			NodeList list=root.getElementsByTagName("bean");
			
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i);
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				
				//System.out.println("id:"+id+",class="+cls);
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				
				map.put(id, obj);
			}
		}catch(Exception ex){}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			// ����� ��û => Model ã�� 
			String cmd=request.getRequestURI();
			cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
			/*
			 *  list
			 */
			Model model=(Model)map.get(cmd);
			
			//System.out.println("model="+model);
			String jsp=model.handlerRequest(request);
			if(jsp.startsWith("redirect"))
			{
				// ȭ�� �̵�
				response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
				// return "rediect:list.do"
			}
			else
				
				
				
				
				
				
				
				
				
				
				
			{
				// ȭ�� ��� 
				RequestDispatcher rd=request.getRequestDispatcher(jsp);
				rd.forward(request, response);
				
			}
			
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
















