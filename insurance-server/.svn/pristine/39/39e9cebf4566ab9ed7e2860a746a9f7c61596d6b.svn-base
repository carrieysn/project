package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsGearingRule;
import com.cifpay.starframework.service.CommonService;

public interface InsGearingRuleService extends CommonService<InsGearingRule> {
public InsGearingRule get(int id);
public List<InsGearingRule> getList();
public int getCount();

/**
 * 根据ID调整杠杆规则
 * @param id
 * @param creditScore
 * @param gearing
 * @return
 */
public boolean adjustGearingRule(Long insUserId, Integer id, Integer creditScore, Integer gearing);

/**
 * 获取默认
 * @return
 */
public InsGearingRule getDefault();
}
