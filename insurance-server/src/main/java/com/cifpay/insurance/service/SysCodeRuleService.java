package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.SysCodeRule;
import com.cifpay.starframework.service.CommonService;

public interface SysCodeRuleService extends CommonService<SysCodeRule> {
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
	
	/**
	 * 产生指定编码名称的编码
	 * 
	 * @param codeName
	 *            编码名称
	 * @return
	 */
	public String generateCodeNumber(String codeName);

	/**
	 * 产生指定编码名称的编码
	 * 
	 * @param codeName
	 *            编码名称
	 * @param billObj
	 *            单据对象，适用于编码中有应用到单据对象属性值的规则。
	 * @param cu 
	 *          控制单元，一般为商户标识
	 * @return
	 */
	public String generateCodeNumber(String codeName, Object billObj);

	/**
	 * 产生指定编码名称的编码
	 * 
	 * @param codeName
	 *            编码名称
	 * @param billObj
	 *            单据对象，适用于编码中有应用到单据对象属性值的规则。
	 * @param cu 
	 *          控制单元，一般为商户标识
	 * @return
	 */
	public String generateCodeNumber(String codeName, Object billObj, String cu);
	
	/**
	 * 增加编码规则。
	 * 
	 * @param sysCodeRule
	 */
	public void addSysCodeRule(SysCodeRule sysCodeRule);
}
