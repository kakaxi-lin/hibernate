package yk.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import yk.po.Kai;
import yk.util.HibernateUtil;

public class KaiDao {
	SessionFactory sessionFactory = null;

	public KaiDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	

	public void add() {
		Kai k = new Kai();
		k.setName("凯哥111");
		Session session = HibernateUtil.getSession();
		// 除get查询外，都需要开启事务
		Transaction tx = session.beginTransaction();
		session.save(k);
		// 提交事务
		tx.commit();
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);

	}

	public void query() {
		Session session = HibernateUtil.getSession();
//		Kai kai = (Kai) session.get(Kai.class, 3);
//		4.3版本 使用load会保存转换类型的错误 要把配置文件中 lazy=false
		Kai kai = (Kai) session.load(Kai.class, 3);
		System.out.println(kai);
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}

	public void update() {
		Session session = HibernateUtil.getSession();
		// 除get查询外，都需要开启事务
		Transaction tx = session.beginTransaction();
		// 用get先查询
		// Kai kai=(Kai) session.get(Kai.class, 2);
		// 也可以直接用对象设置，这样可以少发送一个select语句
		Kai kai = new Kai();
		kai.setId(2);
		kai.setName("888");
		session.update(kai);
		// 提交事务
		tx.commit();
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}

	public void delete() {
		Session session = HibernateUtil.getSession();
		// 除get查询外，都需要开启事务
		Transaction tx = session.beginTransaction();
		Kai kai = new Kai();
		kai.setId(2);
		//可以随便设置一个值，但是不能为空
		kai.setName("ddd");
		session.delete(kai);
		// 提交事务
		tx.commit();
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	
	public void HQL_query() {
		Session session = HibernateUtil.getSession();
		// 除get查询外，都需要开启事务
		//一 :是占位符
//		Query query = session.createQuery("from Kai where id= :c").setInteger("c", 5);
//		1.使用setParameter
//		query.setParameter("c", 3);
//		2.使用setInteger
//		链式编程
//		二 ？占位符
//		Hibernate是从0开始，JDBC是从1开始
		Query query = session.createQuery("from Kai where id= ?").setInteger(0, 5);
		List<Kai> list = (List<Kai>)query.list();
		for(Kai k:list){
			System.out.println(k);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//分页
	public void HQL_fenye() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Kai order by id desc");
		//查询最大条数
		query.setMaxResults(3);
		//从第几条开始  从0开
		query.setFirstResult(0);
		List<Kai> list = (List<Kai>)query.list();
		for(Kai k:list){
			System.out.println(k);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//只取其中几个属性
	public void HQL_selectObject() {
		Session session = HibernateUtil.getSession();
		//1.如果查询的字段只有一个则用 List<Object>
//		Query query = session.createQuery("select id from Kai k ");
//		List<Object> list = (List<Object>)query.list();
//		for(Object o:list){
//			System.out.println(o);
//		}
		//2.如果查询字段多与一个，则用List<Object []>
		Query query = session.createQuery("select id,name from Kai k ");
		List<Object []> list = (List<Object []>)query.list();
		for(Object [] o:list){
			System.out.println(o[0]+">"+o[1]);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//获取总条数 
	public void HQL_count() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select count(id) from Kai where id between 1 and 3 ");
		//uniqueResult 数字结果返回的是Long类型/也可返回唯一的对象值
		long count = (Long) query.uniqueResult();
		System.out.println(count);
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	
	public static void main(String[] args) {
		// new KaiDao().add();
//		 new KaiDao().query();
		// new KaiDao().update();
//		new KaiDao().delete();
//		new KaiDao().HQL_query();
//		new KaiDao().HQL_fenye();
//		new KaiDao().HQL_selectObject();
		new KaiDao().HQL_count();
	}
}
