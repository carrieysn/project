package com.cifpay.insurance.service;

import java.util.List;

import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.starframework.service.CommonService;

public interface InsSalesOrderItemsService extends CommonService<InsSalesOrderItems> {
	public InsSalesOrderItems get(long id);

	public List<InsSalesOrderItems> getList();

	public int getCount();
	
	/**
	 * 据保险证号获取商品明细信息
	 * 
	 * @param insuranceCertNo
	 * @return
	 */
	public InsSalesOrderItems getInsSalesOrderItemsByCertNo(String insuranceCertNo);
}
