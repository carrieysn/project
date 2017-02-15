package com.cifpay.lc.bankadapter.api.helper;

public class GetTradeParamHelper {

	public static final String UNION_PAY = "UNION_PAY";
	private AbsGetTradeParamFactory factory;

	public AbsGetTradeParamFactory getFactory() {
		return factory;
	}

	public void setFactory(AbsGetTradeParamFactory factory) {
		this.factory = factory;
	}

	public static GetTradeParamHelper getInstance(String type) throws Exception {
		GetTradeParamHelper helper = new GetTradeParamHelper();
		if (GetTradeParamHelper.UNION_PAY.equals(type)) {
			helper.factory = new UnionPayTradeParamFactory();
		} else {
			throw new Exception("错误的HELPER类型");
		}
		return helper;
	}

	/**
	 * 
	 * @param clz
	 * @param params
	 *            第一个为银信证类型 cp200 cp300 第二个为卡类型 0信用卡 1储蓄卡
	 *            第三个为是否第一次开通，默认不传为非第一次开通,0第一次开通
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> T getTradeParam(Class<T> clz, String... params) throws InstantiationException, IllegalAccessException {
		return factory.getTradeParam(clz, params);
	}
}
