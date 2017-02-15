package com.cifpay.lc.core.db.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.LcProduct;

@Repository
public interface LcProductDao {

	LcProduct selectByPrimaryKey(Long productId);

	void deleteByPrimaryKey(Long productId);

	void insert(LcProduct lcProduct);

	void insertSelective(LcProduct lcProduct);

	void updateByPrimaryKeySelective(LcProduct lcProduct);

	void updateByPrimaryKey(LcProduct lcProduct);

	List<LcProduct> selectBatchByProductCode(@Param("productCodes") String... productCodes);
	
	LcProduct selectByProductCode(@Param("productCode") String productCode);

	List<LcProduct> selectAll();

}
