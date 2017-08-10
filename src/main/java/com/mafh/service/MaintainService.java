package com.mafh.service;

import com.mafh.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mafh
 * @create 2017-08-08 17:27
 * Created With Intellij IDEA
 * 维护相关的业务功能
 */
public class MaintainService {
    private MessageDao messageDao = new MessageDao();
    public void deleteOne(String id){
        if(id != null && !"".equals(id.trim())){
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }
    public void deleteBatch(String[] ids){
        List<Integer> idList = new ArrayList<Integer>();
        for(String idStr:ids){
            idList.add(Integer.valueOf(idStr));
        }
        if(!idList.isEmpty()){
            messageDao.deleteBatch(idList);
        }
    }
}
