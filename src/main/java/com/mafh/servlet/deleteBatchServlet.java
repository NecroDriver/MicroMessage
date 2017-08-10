package com.mafh.servlet;

import com.mafh.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mafh
 * @create 2017-08-09 15:26
 * Created With Intellij IDEA
 * 批量删除操作
 */
public class deleteBatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置返回参数编码
        req.setCharacterEncoding("UTF-8");
        //接口参数
        String[] ids = req.getParameterValues("id");
        //调用方法
        MaintainService maintainService = new MaintainService();
        maintainService.deleteBatch(ids);
        //跳转
        req.getRequestDispatcher("/list.action").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
