package com.baidu.study;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import models.IUserOperation;
import models.User;

/**
 * Hello world!
 *
 */
public class App {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static InputStream reader;
	static {
		//读取配置
		reader = ClassLoader.getSystemResourceAsStream("models/Configuration.xml");
		//从配置中创建sqlSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}
	
	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	
	public static void Demo1() {
		SqlSession session = getSession().openSession();
		User user = (User)session.selectOne("models.UserMapper.selectUserByID", 1);
		System.out.println(user.toString());
	}
	
	public static void Demo2() {
		SqlSession session = getSession().openSession();
		IUserOperation userOpreation = session.getMapper(IUserOperation.class);
		User user = userOpreation.selectUserByID(1);
        System.out.println(user.getUserAddress());
        System.out.println(user.getUserName());
	}
	
	public static void Demo3() {
		SqlSession session = getSession().openSession();
		IUserOperation userOpreation = session.getMapper(IUserOperation.class);
		List<User> rt = userOpreation.selectUsers("jim");
		for(User user : rt) {
			System.out.println(user.toString());
		}
	}
	
	public static void Demo4() {
		SqlSession session = getSession().openSession();
		IUserOperation userOpreation = session.getMapper(IUserOperation.class);
		User us = new User();
		us.setUserAddress("南京邮电大学");
		us.setUserAge("23");
		us.setUserName("张洪斌");
		userOpreation.addUser(us);
		session.commit();
	}
	
	public static void main(String[] args) {
		Demo4();
	}
}
