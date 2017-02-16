package com.cifpay.insurance.dao;

import java.util.List;

import com.cifpay.insurance.model.InsSalesOrderItems;
import com.cifpay.starframework.dao.CommonDao;

public interface InsSalesOrderItemsDao extends CommonDao<InsSalesOrderItems> {
	public InsSalesOrderItems get(long id);

	public List<InsSalesOrderItems> getList();

	public int getCount();

	/**
	 * 批量增加
	 * 
	 * @param insSalesOrderItemsList
	 * @return
	 */
	public int addBatch(List<InsSalesOrderItems> insSalesOrderItemsList);
	
	/**
	 * 据保险证号获取商品明细信息
	 * 
	 * @param insuranceCertNo
	 * @return
	 */
	public InsSalesOrderItems getInsSalesOrderItemsByCertNo(String insuranceCertNo);
}
