package com.mafh.servlet;

import com.mafh.bean.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mafh
 * @create 2017-08-01 15:14
 * Created With Intellij IDEA
 * 列表页面初始化控制
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String command = req.getParameter("command");
            String description = req.getParameter("description");
            req.setAttribute("command",command);
            req.setAttribute("description",description);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message?characterEncoding=utf8","root","123456");
            StringBuilder sql = new StringBuilder("select id,command,description,content from message where 1=1");
            List<String> paramList = new ArrayList<String>();
            if (command != null && !"".equals(command)){
                paramList.add(command);
                sql.append(" and command = ?");
            }
            if (description != null && !"".equals(description)){
                paramList.add(description);
                sql.append(" and description like '%' ? '%'");
            }
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < paramList.size(); i++) {
                ps.setString(i+1,paramList.get(i));
            }
            ResultSet rs = ps.executeQuery();
            List<Message> messageList = new ArrayList<Message>();
            while (rs.next()){
                Message message = new Message();
                messageList.add(message);
                message.setId(rs.getInt("id"));
                message.setCommand(rs.getString("command"));
                message.setDescription(rs.getString("description"));
                message.setContent(rs.getString("content"));
            }
            req.setAttribute("messageList",messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
