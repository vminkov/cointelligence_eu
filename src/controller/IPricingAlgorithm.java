package controller;

import java.util.List;
import java.util.Map;

import entity.LogBean;
import entity.StatementBean;

public interface IPricingAlgorithm {
	public Map<StatementBean, Double> recalculate(Map<StatementBean, Double> oldPrices, List<LogBean> logs);
}
