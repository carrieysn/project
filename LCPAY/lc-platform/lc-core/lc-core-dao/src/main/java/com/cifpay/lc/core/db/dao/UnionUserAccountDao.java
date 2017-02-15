package com.cifpay.lc.core.db.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cifpay.lc.core.db.pojo.UnionUserAccount;

@Repository
public interface UnionUserAccountDao {

    UnionUserAccount selectByPrimaryKey(Map<String, String> map);

    UnionUserAccount selectBySelective(UnionUserAccount unionUserAccount);

    UnionUserAccount selectById(@Param("mid") String mid, @Param("merUserid") String merUserid,@Param("accNoType")int accNoType);

    void deleteByPrimaryKey(String merId, String userId);

    void insert(UnionUserAccount unionUserAccount);

    int insertSelective(UnionUserAccount unionUserAccount);

    void updateByPrimaryKeySelective(UnionUserAccount unionUserAccount);

    void updateByPrimaryKey(UnionUserAccount unionUserAccount);

    void updateTokenResult(UnionUserAccount unionUserAccount);
}
