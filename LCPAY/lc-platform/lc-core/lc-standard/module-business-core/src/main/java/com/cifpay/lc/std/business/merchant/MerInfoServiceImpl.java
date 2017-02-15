package com.cifpay.lc.std.business.merchant;

import com.cifpay.lc.util.security.ThreeDESUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cifpay.lc.api.gateway.union.MerInfoService;
import com.cifpay.lc.core.db.dao.UnionUserAccountDao;
import com.cifpay.lc.core.db.pojo.UnionUserAccount;
import com.cifpay.lc.domain.query.UnionUserAccountDto;

@Service("merInfoService")
public class MerInfoServiceImpl implements MerInfoService {

    @Autowired
    private UnionUserAccountDao userAccountDao;

    @Override
    public UnionUserAccountDto findById(String mid, String merUserid, int accNoType) {

        UnionUserAccount obj = userAccountDao.selectById(mid, merUserid, accNoType);

        UnionUserAccountDto dto = null;

        if (obj != null) {

            dto = new UnionUserAccountDto();

            BeanUtils.copyProperties(obj, dto);

            try {
                String rawAccNo = ThreeDESUtil.decDecrypt(obj.getPayerAccno());
                String rawPhoneNo = ThreeDESUtil.decDecrypt(obj.getUserMobile());

                dto.setPayerAccno(rawAccNo);
                dto.setUserMobile(rawPhoneNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dto;
    }
}
