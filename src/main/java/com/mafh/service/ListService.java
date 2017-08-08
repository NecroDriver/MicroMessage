package com.mafh.service;

import com.mafh.bean.Message;
import com.mafh.dao.MessageDao;

import java.util.List;

/**
 * @author mafh
 * @create 2017-08-08 14:41
 * Created With Intellij IDEA
 * service层处理dao层返回的数据
 */
public class ListService {
    private MessageDao messageDao = new MessageDao();
    public List<Message> searchMessage(String command,String description){
        return messageDao.searchMessage(command,description);
    }
    public List<Message> queryMessage(String command,String description){
        return messageDao.queryMessage(command,description);
    }
}
