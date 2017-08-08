package com.mafh.servlet;

import com.mafh.service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mafh
 * @create 2017-08-01 15:14
 * Created With Intellij IDEA
 * 列表页面初始化控制
 */
public class ListServlet extends HttpServlet {
    private ListService listService = new ListService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在没有过滤器的情况下使用字节转码
        req.setCharacterEncoding("UTF-8");
        //接收参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        //出入返回值
        req.setAttribute("command",command);
        req.setAttribute("description",description);
        req.setAttribute("messageList", listService.queryMessage(command,description));
        //转发地址
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
