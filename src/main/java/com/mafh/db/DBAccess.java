package com.mafh.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author mafh
 * @create 2017-08-08 15:06
 * Created With Intellij IDEA
 * 访问数据库类
 */
public class DBAccess {
    public SqlSession getSqlSession() throws IOException {
        //通过配置文件过去数据库信息
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //通过配置信息构建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //通过SqlSessionFactory构建一个通往数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
