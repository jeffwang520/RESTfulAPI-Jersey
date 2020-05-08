package com.cs.jeff.restapi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class HttpRequestHandler
 */
public class HttpRequestHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HttpRequestHandler() {

        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub
        try {
            preHandle(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.getWriter().append("Served at: ").append(request.getContextPath());


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public void preHandle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        String content_type = request.getHeader("Content-Type");
        System.out.println(content_type);

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        System.out.println(responseStrBuilder.toString());
        testJson2(responseStrBuilder.toString());

    }

    public void testJson2(final String JsonString) {

        JSONObject object = JSON.parseObject(JsonString);
        String s = object.getString("string");
        System.out.println(s);
        int i = object.getIntValue("int");
        System.out.println(i);
        boolean b = object.getBooleanValue("boolean");
        System.out.println(b);
        List<Integer> integers = JSON.parseArray(object.getJSONArray("list").toJSONString(), Integer.class);
        integers.forEach(System.out::println);
        System.out.println(object.getString("null"));
    }
}