/**
 * File: PageResponseInfo.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月25日 下午7:40:42
 */
package com.cifpay.insurance.param;

import java.io.Serializable;

/**
 * 接口返回分页结果定义
 * 
 * @author 张均锋
 *
 */
public class PageResponseInfo extends DataResponseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 返回查询记录数
	 */
	private long recordCount;

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	
}
