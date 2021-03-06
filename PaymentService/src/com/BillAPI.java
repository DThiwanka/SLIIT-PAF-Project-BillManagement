package com;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bill;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		Bill billObj = new Bill();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillAPI() {
        //super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("done insert");
		String output = billObj.insertBill(
				request.getParameter("billName"), 
				request.getParameter("billAmount"),
				request.getParameter("billDate"), 
				request.getParameter("NoOfTunits"),
				request.getParameter("BillAr"));
		response.getWriter().write(output);
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("got here");
		Map paras = getParasMap(request); 
		 String output = billObj.updateBill(
		paras.get("hidBillIDSave").toString(), 
		paras.get("billName").toString(), 
		paras.get("billAmount").toString(), 
		paras.get("billDate").toString(), 
		paras.get("NoOfTunits").toString(), 
		paras.get("BillAr").toString()); 
		response.getWriter().write(output); 
	}

	private Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		 Map<String, String> map = new HashMap<String, String>(); 
			try
			 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			 String queryString = scanner.hasNext() ? 
			 scanner.useDelimiter("\\A").next() : ""; 
			 scanner.close(); 
			 String[] params = queryString.split("&"); 
			 for (String param : params) 
			 { 
			String[] p = param.split("=");
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = billObj.deleteBill(paras.get("billID").toString());
		response.getWriter().write(output);
	}

}
