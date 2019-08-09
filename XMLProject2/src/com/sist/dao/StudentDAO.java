package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class StudentDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   try
	   {
		   // XML�� �о� �´� => SRC 
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
		   /*
		    *   map.put("studentAllData","SELECT * FROM student_view")
		    */
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   	public static List<StudentVO> studentAllData()
   	{
   		return ssf.openSession().selectList("studentAllData");
   	}
   
}
