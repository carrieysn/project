package com.cifpay.lc.gateway.controller.cache;

import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.cache.CacheBusinessService;
import com.cifpay.lc.constant.ReturnCode;
import com.cifpay.lc.constant.enums.CacheEnum;
import com.cifpay.lc.domain.cache.BankCacheInputBean;
import com.cifpay.lc.domain.cache.CacheInput;
import com.cifpay.lc.domain.cache.CacheOutput;
import com.cifpay.lc.domain.cache.MerchantInputBean;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/cache")
public class CacheBusinessController extends GatewayBaseController {

    @Autowired
    private CacheBusinessService cacheBusinessService;

    @RequestMapping(path = "/removeCache", method = RequestMethod.POST)
    @ResponseBody
    public CacheOutput removeCache(@RequestBody Map<String, String> param) {

        String cacheKey = param.get("cacheKey");
        if (StringUtils.isEmpty(cacheKey)) {
        	CacheOutput cacheOutput =new CacheOutput();
            cacheOutput.setReturnCode(ReturnCode.CORE_LC_PARAMETER_INVALID);
            cacheOutput.setReturnMsg("不支持的参数");
            return cacheOutput;
        }

        ObjectMapper mapper = new ObjectMapper();
        CacheInput cacheInput;

        switch (cacheKey) {
            case CacheEnum.CACHE_TYPE_BANK: {
                cacheInput = mapper.convertValue(param, BankCacheInputBean.class);
                break;

            }
            case CacheEnum.CACHE_TYPE_MERCHANT: {
                cacheInput = mapper.convertValue(param, MerchantInputBean.class);
                break;
            }
            default: {
                 CacheOutput cacheOutput =new CacheOutput();
                 cacheOutput.setReturnCode(ReturnCode.CORE_LC_PARAMETER_INVALID);
                 cacheOutput.setReturnMsg("不支持的参数");
                 return cacheOutput;
            }
        }

        BusinessOutput<CacheOutput> updateCache = cacheBusinessService.removeCache(cacheInput);
        CacheOutput output =new CacheOutput();
        output.setReturnCode(updateCache.getReturnCode());
        output.setReturnMsg(updateCache.getReturnMsg());
        return output;
    }
}
