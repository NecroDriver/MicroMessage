package com.mafh.dao;

import com.mafh.bean.Message;
import com.mafh.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mafh
 * @create 2017-08-08 14:38
 * Created With Intellij IDEA
 * dao层处理bean对象与数据库交互的相关事务操作
 */
public class MessageDao {
    private DBAccess dbAccess = new DBAccess();
    //通过jdbc操作数据库
    public List<Message> searchMessage(String command,String description){
        List<Message> messageList = new ArrayList<Message>();
        try {
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
            while (rs.next()){
                Message message = new Message();
                messageList.add(message);
                message.setId(rs.getInt("id"));
                message.setCommand(rs.getString("command"));
                message.setDescription(rs.getString("description"));
                message.setContent(rs.getString("content"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
    //通过mybatis访问数据库
    public List<Message> queryMessage(String command,String description){
        List<Message> messageList = new ArrayList<Message>();
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行sql语句
            messageList = sqlSession.selectList("Message.queryMessage",message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return messageList;
    }

    //删除数据库数据
    public void deleteOne(Integer id){
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne",id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
    //批量删除数据库数据
    public void deleteBatch(List<Integer> idList){
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("deleteBatch",idList);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
