package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.SysCodeRuleDt;
import com.cifpay.starframework.dao.CommonDao;

public interface SysCodeRuleDtDao extends CommonDao<SysCodeRuleDt> {
	public SysCodeRuleDt get(long id);

	public List<SysCodeRuleDt> getList();

	public int getCount();

	/**
	 * 批量增加
	 * 
	 * @param SysCodeRuleDt
	 * @return
	 */
	public int addBatch(List<SysCodeRuleDt> sysCodeRuleDtList);
}
