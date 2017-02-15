package com.cifpay.lc.std.mapper;

import java.util.ArrayList;
import java.util.List;

import com.cifpay.lc.core.db.pojo.Lc;
import com.cifpay.lc.domain.batch.OpenLcOutputBean;

public class LcMapper {

	public static OpenLcOutputBean ToOpenLcOutputBean(Lc lc) {
		if (lc == null) {
			return new OpenLcOutputBean();
		}

		OpenLcOutputBean result = new OpenLcOutputBean();
		result.setLcId(lc.getLcId());
		result.setOrderId(lc.getOrderId());
		result.setLcAmount(lc.getLcAmount());
		result.setLcStatus(lc.getLcStatus());

		return result;
	}

	public static List<OpenLcOutputBean> ToOpenLcOutputBean(List<Lc> lcList) {
		if (lcList == null || lcList.isEmpty()) {
			return new ArrayList<OpenLcOutputBean>();
		}

		List<OpenLcOutputBean> result = new ArrayList<OpenLcOutputBean>();

		for (Lc lc : lcList) {
			OpenLcOutputBean openLcOutputBean = ToOpenLcOutputBean(lc);
			result.add(openLcOutputBean);
		}

		return result;
	}
}
