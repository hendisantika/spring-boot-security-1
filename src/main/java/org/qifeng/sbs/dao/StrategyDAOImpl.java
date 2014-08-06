/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.qifeng.sbs.model.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jackho
 *
 */
@Repository
public class StrategyDAOImpl implements StrategyDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#addStrategy(org.qifeng.sbs.model.Strategy)
	 */
	public void addStrategy(Strategy strategy) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#getStrategy(int)
	 */
	public Strategy getStrategy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#updateStrategy(org.qifeng.sbs.model.Strategy)
	 */
	public void updateStrategy(Strategy stratgy) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#deleteStrategy(int)
	 */
	public void deleteStrategy(int id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#getStrategies()
	 */
	@SuppressWarnings("unchecked") //抓出來的List沒有轉型warning
	public List<Strategy> getStrategies() {
		Session session = getCurrentSession();
		
		return session.createQuery("from Strategy").list();
	}
}
