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
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message","root","123456");
            String sql = "select id,command,description,content from message";
            PreparedStatement ps = conn.prepareStatement(sql);
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
