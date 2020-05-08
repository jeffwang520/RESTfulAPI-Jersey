package com.cs.jeff.restapi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class HTTPMethodHandler
 */
public class HTTPMethodHandler extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HTTPMethodHandler() {

        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        String unitCode = request.getParameter("C_UNIT_CODE");
        String mainRef = request.getParameter("C_MAIN_REF");
        if (unitCode == null) {
            String reqBody = getRequestBodyWithJSON(request, response);
            JSONObject object = JSON.parseObject(reqBody);
            unitCode = object.getString("C_UNIT_CODE");
            mainRef = object.getString("C_MAIN_REF");
        }
        String objectToReturn = "{ \"C_UNIT_CODE\": \"" + unitCode + "\", \"C_MAIN_REF\": \"" + mainRef + "\",\"LC_CCY\":\"USD\",\"LC_AMT\":\"100\",\"APPL_NAME\":\"SHARP\" }";
        sendResponse(response, objectToReturn);
    }


    public String getRequestBodyWithJSON(final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        String content_type = request.getHeader("Content-Type");
        System.out.println(content_type);

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        System.out.println(responseStrBuilder.toString());
        return responseStrBuilder.toString();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        String unitCode = request.getParameter("C_UNIT_CODE");
        String mainRef = request.getParameter("C_MAIN_REF");
        String CCY = request.getParameter("C_UNIT_CODE");
        String AMT = request.getParameter("C_MAIN_REF");
        String APPL_NAME = request.getParameter("C_UNIT_CODE");
        String objectToReturn = "{ \"C_UNIT_CODE\": \"" + unitCode + "\", \"C_MAIN_REF\": \"" + mainRef + "\",\"LC_CCY\":\"" + CCY + "\",\"LC_AMT\":\"" + AMT
            + "\",\"APPL_NAME\":\"" + APPL_NAME + "\",\"CHARGE_CCY\":\"USD\",\"CHARGE_AMT\":\"100\" }";
        sendResponse(response, objectToReturn);
    }

    private void sendResponse(final HttpServletResponse response, final String jsonString) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.setStatus(200);
        response.getWriter().write(jsonString);
        response.getWriter().close();
    }

    protected void doList(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        String jsonString =
            "[{\"C_UNIT_CODE\": \"MHBK\",\"C_MAIN_REF\": \"IPLC_A123\",\"ANOUNT\": \"123123\"},{\"C_UNIT_CODE\": \"MHBK\",\"C_MAIN_REF\": \"IMCO_A123\",\"VALUE_DTAE\": \"2020-05-04\"}, {\"C_UNIT_CODE\": \"MHBK\",\"C_MAIN_REF\": \"EPLC_A123\",\"LC_AMT\": \"12311\"}]";
        sendResponse(response, jsonString);
    }


    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getMethod();
        System.out.println("Get HTTP Method is:" + method);
        if ("LIST".equals(method)) {
            doList(req, resp);
        } else if ("GET".equals(method)) {
            doGet(req, resp);
        } else if ("POST".equals(method)) {
            doPost(req, resp);
        } else {
            String errMsg = "http.method_not_implemented";
            Object[] errArgs = new Object[1];
            errArgs[0] = method;
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }


}
