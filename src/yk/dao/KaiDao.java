package yk.dao;

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

	public static void main(String[] args) {
		// new KaiDao().add();
		// new KaiDao().query();
		// new KaiDao().update();
		new KaiDao().delete();
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
		Kai kai = (Kai) session.get(Kai.class, 2);
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

}
