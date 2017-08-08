package com.mafh.servlet;

import com.mafh.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mafh
 * @create 2017-08-08 17:34
 * Created With Intellij IDEA
 * 删除一条数据
 */
public class deleteOneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //接收页面传参
        String id = req.getParameter("id");
        //删除数据
        MaintainService maintainService = new MaintainService();
        maintainService.deleteOne(id);
        //向页面跳转
        req.getRequestDispatcher("/list.action").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
