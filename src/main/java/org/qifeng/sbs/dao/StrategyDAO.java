/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.qifeng.sbs.model.Strategy;

/**
 * @author jackho
 *
 */
public interface StrategyDAO {
	
	public void addStrategy(Strategy strategy);
	public Strategy getStrategy(int id);
	public void updateStrategy(Strategy stratgy);
	public void deleteStrategy(int id);
	public List<Strategy> getStrategies();
}
