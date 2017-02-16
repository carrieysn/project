package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.SysCodeRule;
import com.cifpay.starframework.dao.CommonDao;

public interface SysCodeRuleDao extends CommonDao<SysCodeRule> {
	public SysCodeRule get(long id);

	public List<SysCodeRule> getList();

	public int getCount();

	/**
	 * 据编码规则名称获取编码规则
	 * 
	 * @param codeName
	 * @return
	 */
	public SysCodeRule getSysCodeRule(String codeName);
	
	/**
	 * 据编码规则名称获取默认编码规则（未分配cu的）
	 * 
	 * @param codeName
	 * @return
	 */
	public SysCodeRule getDefaultSysCodeRule(String codeName);

	/**
	 * 据控制单元和编码规则名称获取编码规则
	 * 
	 * @param cu 控制单元
	 * @param codeName
	 * @return
	 */
	public SysCodeRule getSysCodeRule(String cu, String codeName);
}
