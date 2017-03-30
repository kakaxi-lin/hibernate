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
		k.setName("����111");
		Session session = HibernateUtil.getSession();
		// ��get��ѯ�⣬����Ҫ��������
		Transaction tx = session.beginTransaction();
		session.save(k);
		// �ύ����
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
		// ��get��ѯ�⣬����Ҫ��������
		Transaction tx = session.beginTransaction();
		// ��get�Ȳ�ѯ
		// Kai kai=(Kai) session.get(Kai.class, 2);
		// Ҳ����ֱ���ö������ã����������ٷ���һ��select���
		Kai kai = new Kai();
		kai.setId(2);
		kai.setName("888");
		session.update(kai);
		// �ύ����
		tx.commit();
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}

	public void delete() {
		Session session = HibernateUtil.getSession();
		// ��get��ѯ�⣬����Ҫ��������
		Transaction tx = session.beginTransaction();
		Kai kai = new Kai();
		kai.setId(2);
		//�����������һ��ֵ�����ǲ���Ϊ��
		kai.setName("ddd");
		session.delete(kai);
		// �ύ����
		tx.commit();
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}

}
