package com.cifpay.insurance.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.insurance.CodeRuleRuleTypeEnum;
import com.cifpay.insurance.dao.SysCodeRuleDao;
import com.cifpay.insurance.dao.SysCodeRuleDtDao;
import com.cifpay.insurance.exception.InsuranceBizRuntimeException;
import com.cifpay.insurance.model.SysCodeRule;
import com.cifpay.insurance.model.SysCodeRuleDt;
import com.cifpay.insurance.service.SysCodeRuleService;
import com.cifpay.insurance.util.ReflectUtil;
import com.cifpay.starframework.cache.ServiceResultCodeCache;
import com.cifpay.starframework.model.ServiceResult;

@Service("sysCodeRuleService")
public class SysCodeRuleServiceImpl implements SysCodeRuleService {
	private static final Logger logger = LogManager.getLogger(SysCodeRuleServiceImpl.class);
	private static ServiceResultCodeCache resultCode = ServiceResultCodeCache.getInstance();
	@Autowired
	private SysCodeRuleDao sysCodeRuleDao;
	@Autowired
	private SysCodeRuleDtDao sysCodeRuleDtDao;

	@Override
	public SysCodeRule get(long id) {
		return sysCodeRuleDao.get(id);
	}

	@Override
	public ServiceResult<String> add(SysCodeRule sysCodeRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = sysCodeRuleDao.add(sysCodeRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> addSelective(SysCodeRule sysCodeRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = sysCodeRuleDao.addSelective(sysCodeRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> update(SysCodeRule sysCodeRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = sysCodeRuleDao.update(sysCodeRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> updateSelective(SysCodeRule sysCodeRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = sysCodeRuleDao.updateSelective(sysCodeRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<String> delete(SysCodeRule sysCodeRule) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
		int result = sysCodeRuleDao.delete(sysCodeRule);
		if (result == 1) {
			serviceResult.setCode(resultCode.get("common.sucess"));
		} else {
			serviceResult.setCode(resultCode.get("common.fail"));
		}
		return serviceResult;
	}

	@Override
	public List<SysCodeRule> getList() {
		return sysCodeRuleDao.getList();
	}

	@Override
	public int getCount() {
		return sysCodeRuleDao.getCount();
	}

	public static ServiceResultCodeCache getResultCode() {
		return resultCode;
	}

	public static void setResultCode(ServiceResultCodeCache resultCode) {
		SysCodeRuleServiceImpl.resultCode = resultCode;
	}

	public SysCodeRuleDao getSysCodeRuleDao() {
		return sysCodeRuleDao;
	}

	public void setSysCodeRuleDao(SysCodeRuleDao sysCodeRuleDao) {
		this.sysCodeRuleDao = sysCodeRuleDao;
	}

	@Override
	public SysCodeRule getSysCodeRule(String codeName) {
		return sysCodeRuleDao.getSysCodeRule(codeName);
	}

	@Override
	public SysCodeRule getSysCodeRule(String cu, String codeName) {
		return sysCodeRuleDao.getSysCodeRule(cu, codeName);
	}
	
	@Override
	public SysCodeRule getDefaultSysCodeRule(String codeName) {
		return sysCodeRuleDao.getDefaultSysCodeRule(codeName);
	}
	
	@Override
	public String generateCodeNumber(String codeName) {
		return generateCodeNumber(codeName, null, null);
	}

	@Override
	public String generateCodeNumber(String codeName, Object billObj) {
		return generateCodeNumber(codeName, billObj, null);
	}
	
	@Override
	public String generateCodeNumber(String codeName, Object billObj, String cu) {
		int ret = 0;
		int count = 0;
		String nextNo = null;//下一编号
		do {//1.修改保额
			count++;
			if (count > 15) {//最多重试15次 ,不限制？
				throw new InsuranceBizRuntimeException(resultCode.get("policy.real.insured.amount.deduct.fail"), "保单保额扣减失败！");
			}
			SysCodeRule cr = getSysCodeRule(cu, codeName);
			if (cr == null) {
				throw new InsuranceBizRuntimeException(resultCode.get("biz.sys.coderule.notfound"), "编码规则不存在！");
			}
			List<SysCodeRuleDt> crds = cr.getSysCodeRuleDt();
			if (crds == null || crds.isEmpty()) {
				throw new InsuranceBizRuntimeException(resultCode.get("sys.coderule.detail.notfound"), "编码规则不存在！");
			}
			String curNo = cr.getCurValue();// 当前编号
			// 规则，流水号必须在最后。26字母表组合与流水同属流水号。
			SysCodeRuleDt backOne = crds.get(crds.size() -1);//最后一个。
			if (backOne.getRuleType() != CodeRuleRuleTypeEnum.SERIAL_NO.val) {//必须存在流水号
				throw new IllegalArgumentException("必须存在流水号，且流水号必须排在最后");//TODO 暂时
			}
			SysCodeRuleDt backSecond = crds.get(crds.size()-2);//倒数第二
			String prefix = getPrefix(crds, billObj);
			//分解
			String endVal = null;//后缀值
			if (curNo != null && curNo.startsWith(prefix)) {
				endVal = curNo.substring(prefix.length());
			}
			if (backSecond.getRuleType() == CodeRuleRuleTypeEnum.ALPHABET.val) {//字母组合
				nextNo = generateNextNo(prefix, endVal, backOne, backSecond);
			} else {
				int factor = 0;
				if (endVal != null) {//直接为流水号值
					factor = Integer.parseInt(endVal);
				}
				nextNo = String.format(prefix+"%0"+backOne.getRule()+"d", ++factor);
			}
			//更新至db
			cr.setCurValue(nextNo);//
			ret = sysCodeRuleDao.update(cr);
			//停顿
			if (count > 1) {
				logger.warn(String.format("第%d次尝试扣减保额！", count));
			    try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
				}
			}
		} while (ret == 0);//并发情况下，尝试10次
		if (ret > 0) {
			return nextNo;
		}
		throw new RuntimeException("产生编码失败！");
	}
	
	/** 获取前缀值  **/
	private String getPrefix(List<SysCodeRuleDt> crds, Object billObj) {
		StringBuilder prefixVal = new StringBuilder();//前缀值。
		for (SysCodeRuleDt crd: crds) {
			if (crd.getRuleType() == CodeRuleRuleTypeEnum.TIME.val) {//时间
				String val = new SimpleDateFormat(crd.getRule()).format(new Date());
				prefixVal.append(val);
			} else if (crd.getRuleType() == CodeRuleRuleTypeEnum.CONSTANT.val) {//常量
				String val = new SimpleDateFormat(crd.getRule()).format(new Date());
				prefixVal.append(val);
			} else if (crd.getRuleType() == CodeRuleRuleTypeEnum.PROPERTY.val) {//属性
				if (billObj == null) {
					throw new IllegalArgumentException("必须输入属性对象[property=" + crd.getRule() + "]");
				}
				String val = (String)ReflectUtil.getFieldValue(billObj, crd.getRule());
				if (val == null) {
					throw new IllegalArgumentException("订单属性性为空[property=" + crd.getRule() + "]");
				}
				prefixVal.append(val);
			} else;//other..
		}
		if (prefixVal.length() == 0) {
			throw new IllegalArgumentException("规则属性设置不全！");
		}
		return prefixVal.toString();
	}
	
	private String generateNextNo(String prefix, String endVal, SysCodeRuleDt serialCR, SysCodeRuleDt alphabetCR) {
		String alphVal = alphabetCR.getRule();//字母，可能一位，多位
		if (endVal == null) {
			return String.format(prefix + alphVal + "%0" + serialCR.getRule() + "d", 1);
		}
		String preSearialNo = endVal.substring(0, alphVal.length());//前流水
		if (preSearialNo.length() != alphVal.length()) {//如果存在号码与规则表不匹配，按规则表重新产生编号
			return String.format(prefix + alphVal + "%0" + serialCR.getRule() + "d", 1);
		}
		int searialNo = Integer.valueOf(endVal.substring(alphVal.length()));// 后流水
		String searialLength = serialCR.getRule();//流水号位数
		int maxSearialNo = Integer.valueOf(String.format("1%0" + searialLength + "d", 0)) - 1;
		if (searialNo < maxSearialNo) {// 流水号未到最大值
			return String.format(prefix + preSearialNo + "%0"+searialLength+"d", searialNo + 1);
		}
		StringBuilder lostAlph = new StringBuilder();
		for (int i = preSearialNo.length()-1; i >= 0; i--) {
			char c = preSearialNo.charAt(i);
			if (c != 'Z') {// 倒数字母没到最大
				//前缀+未变字母（可能为空）+变动字母+重新开始字母+开始流水号
				return String.format(prefix + preSearialNo.substring(0, i) + ((char) (c + 1)) + lostAlph.toString() + "%0"+ searialLength + "d", 1);
			} else {//丢失1个Z
				lostAlph.append('A');
			}
		}
		throw new RuntimeException("保险证编号已超出最大值！");// impossible?
	}
	
	@Override
	public void addSysCodeRule(SysCodeRule sysCodeRule) {
		List<SysCodeRuleDt> list = sysCodeRule.getSysCodeRuleDt();
		if (list.isEmpty()) {
			throw new InsuranceBizRuntimeException(resultCode.get("sys.coderule.detail.notfound"), "编码规则不存在！"); 
		}
		sysCodeRuleDao.add(sysCodeRule);
		for (SysCodeRuleDt dt: list) {
			dt.setId(null);
			dt.setCodeRuleId(sysCodeRule.getId());
		}
		sysCodeRuleDtDao.addBatch(list);
	}

}
