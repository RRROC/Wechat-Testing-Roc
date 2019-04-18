package com.roc.servlet;

import com.roc.service.WechatService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/wechat")

public class WechatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //乱码问题
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        //处理消息事件推送
        Map<String, String> requestMap = WechatService.parseRequest(request.getInputStream());
        System.out.println(requestMap);

        //准备回复数据包
        String respXml = WechatService.getResponse(requestMap);
        System.out.println(respXml);
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(WechatService.check(timestamp,nonce,signature)) {
            System.out.println("Successfully get in");
            PrintWriter out = response.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
        }
        else {
            System.out.println("fail to get in");
        }
        //System.out.println("get");
    }
}
