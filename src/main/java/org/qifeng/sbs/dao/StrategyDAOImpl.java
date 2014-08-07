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
		sessionFactory.getCurrentSession().save(strategy);
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#getStrategy(int)
	 */
	public Strategy getStrategy(int id) {
		Strategy strategy = (Strategy)sessionFactory.getCurrentSession().get(Strategy.class, id);
		return strategy;
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#updateStrategy(org.qifeng.sbs.model.Strategy)
	 */
	public void updateStrategy(Strategy strategy) {
		Strategy strategyToUpdate = getStrategy(strategy.getId());
		strategyToUpdate.setName(strategy.getName());
		strategyToUpdate.setType(strategy.getType());
		sessionFactory.getCurrentSession().update(strategyToUpdate);
	}

	/* (non-Javadoc)
	 * @see org.qifeng.sbs.dao.StrategyDAO#deleteStrategy(int)
	 */
	public void deleteStrategy(int id) {
		Strategy straetgy = getStrategy(id);
		sessionFactory.getCurrentSession().delete(straetgy);
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
