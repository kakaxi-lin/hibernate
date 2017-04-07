package yk.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
//		Kai kai = (Kai) session.get(Kai.class, 3);
//		4.3�汾 ʹ��load�ᱣ��ת�����͵Ĵ��� Ҫ�������ļ��� lazy=false
		Kai kai = (Kai) session.load(Kai.class, 3);
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
	
	public void HQL_query() {
		Session session = HibernateUtil.getSession();
		// ��get��ѯ�⣬����Ҫ��������
		//һ :��ռλ��
//		Query query = session.createQuery("from Kai where id= :c").setInteger("c", 5);
//		1.ʹ��setParameter
//		query.setParameter("c", 3);
//		2.ʹ��setInteger
//		��ʽ���
//		�� ��ռλ��
//		Hibernate�Ǵ�0��ʼ��JDBC�Ǵ�1��ʼ
		Query query = session.createQuery("from Kai where id= ?").setInteger(0, 5);
		List<Kai> list = (List<Kai>)query.list();
		for(Kai k:list){
			System.out.println(k);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//��ҳ
	public void HQL_fenye() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Kai order by id desc");
		//��ѯ�������
		query.setMaxResults(3);
		//�ӵڼ�����ʼ  ��0��
		query.setFirstResult(0);
		List<Kai> list = (List<Kai>)query.list();
		for(Kai k:list){
			System.out.println(k);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//ֻȡ���м�������
	public void HQL_selectObject() {
		Session session = HibernateUtil.getSession();
		//1.�����ѯ���ֶ�ֻ��һ������ List<Object>
//		Query query = session.createQuery("select id from Kai k ");
//		List<Object> list = (List<Object>)query.list();
//		for(Object o:list){
//			System.out.println(o);
//		}
		//2.�����ѯ�ֶζ���һ��������List<Object []>
		Query query = session.createQuery("select id,name from Kai k ");
		List<Object []> list = (List<Object []>)query.list();
		for(Object [] o:list){
			System.out.println(o[0]+">"+o[1]);
		}
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//��ȡ������ 
	public void HQL_count() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select count(id) from Kai where id between 1 and 3 ");
		//uniqueResult ���ֽ�����ص���Long����/Ҳ�ɷ���Ψһ�Ķ���ֵ
		long count = (Long) query.uniqueResult();
		System.out.println(count);
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory(sessionFactory);
	}
	//SQLԭ����ѯ
	public void SQL_select(){
		Session session = HibernateUtil.getSession();
		
		//1.����ѯ���ֶβ����ھ�����ʱ��������Object []����
		/*SQLQuery c = session.createSQLQuery("select k.id ,k.name,t.id xx, t.name ame  from kai k  join tx t on k.id=t.id ");
		List<Object []> list=c.list();
		for (Object[] o : list) {
			System.out.println(o[0]+">"+o[1]+">"+o[2]+">"+o[3]);
		}*/
		//2.����ѯ���ֶζ�Ӧ������ʱ����������ʵ����
		Query c = session.createSQLQuery("select id ,name from kai where id between ? and ?").addEntity(Kai.class).setInteger(0, 3).setInteger(1, 5);
		List<Kai> list=c.list();
		for (Kai k : list) {
			System.out.println(k);
		}
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
		new KaiDao().SQL_select();
	}
}
