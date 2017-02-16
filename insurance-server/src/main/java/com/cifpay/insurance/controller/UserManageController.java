package com.cifpay.insurance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cifpay.insurance.base.BaseController;
import com.cifpay.insurance.base.Page;
import com.cifpay.insurance.config.WebConstant;
import com.cifpay.insurance.model.InsUser;
import com.cifpay.insurance.param.DataResponseInfo;
import com.cifpay.insurance.param.PageResponseInfo;
import com.cifpay.insurance.param.ResponseInfo;
import com.cifpay.insurance.param.user.GetUserAccountInfo;
import com.cifpay.insurance.param.user.GetUserAccountListResult;
import com.cifpay.insurance.param.user.UserAccountOperateInfo;
import com.cifpay.insurance.param.user.UserAccountSaveInfo;
import com.cifpay.insurance.param.user.UserAccountUpdateInfo;
import com.cifpay.insurance.service.InsUserService;
import com.cifpay.insurance.util.JacksonUtil;
import com.cifpay.insurance.util.StringUtils;
import com.cifpay.starframework.security.HashUtil;
import com.cifpay.starframework.util.JsonUtil;
/**
 * 描述：用户管理业务类
 * 类名：UserManageController
 * @author 叶胜南
 *
 */
@Controller
@RequestMapping("/inner/insurance/user/manage")
public class UserManageController  extends BaseController {
	
	private final static String VALIDATION_GETUSERACCOUNTINFO= "com.cifpay.ins.user.GetUserAccountInfoSet";
	private final static String VALIDATION_USERACCOUNTSAVEINFO= "com.cifpay.ins.user.UserAccountSaveInfoSet";
	private final static String VALIDATION_USERACCOUNTUPDATEINFO= "com.cifpay.ins.user.UserAccountUpdateInfoSet";
	private final static String VALIDATION_USERACCOUNTDELETEINFO= "com.cifpay.ins.user.UserAccountDeleteInfoSet";
	@Autowired
	private InsUserService insUserService;

	/**
	 * 查询用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getUserList(HttpServletRequest request) {
		
		PageResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			GetUserAccountInfo param = populateBizData(request, GetUserAccountInfo.class,VALIDATION_GETUSERACCOUNTINFO);
			Page<InsUser> page = new Page<InsUser>();
			page.setPageNo(param.getPageNo());
			page.setPageSize(param.getPageSize());
			insUserService.getInsUserList(param, page);
		    List<GetUserAccountListResult> listResult = new ArrayList<GetUserAccountListResult>();
		    for(InsUser user: page.getResult()){
		    	GetUserAccountListResult result = new GetUserAccountListResult();
		    	BeanUtils.copyProperties(user, result);
				listResult.add(result);
			}
			ri = new PageResponseInfo();
			ri.setRecordCount(page.getRecordCount());
			ri.setData(listResult);
		} catch (Exception e) {
			ResponseInfo res = handleRespException(e);
			return JacksonUtil.toJson(res);
		}
		return JacksonUtil.toJson(ri);
		
	}
	/**
	 * 查询单个用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String getUserInfo(HttpServletRequest request) {
		
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			UserAccountOperateInfo selectUser = populateBizData(request, UserAccountOperateInfo.class,VALIDATION_USERACCOUNTDELETEINFO);
			InsUser getUser = insUserService.getInsUserById(selectUser.getId());
			if (getUser == null) {
				return JsonUtil.getResultJsonString(resultCode.get("common.framework.user.not.exist"), "用户不存在！");
			}
			 ri = new DataResponseInfo(getUser);
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	   
		
	}
	/**
	 * 添加用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String saveUser(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			UserAccountSaveInfo operateUser = populateBizData(request, UserAccountSaveInfo.class,VALIDATION_USERACCOUNTSAVEINFO);
			InsUser user = new InsUser();
			BeanUtils.copyProperties(operateUser, user);
			if(StringUtils.isNotEmpty(user.getPassword())){
				user.setPassword(HashUtil.md5(user.getPassword()));
			}else{
				user.setPassword(HashUtil.md5(WebConstant.DEFAULT_PWD));
			}
			InsUser getUser = insUserService.getInsUserByUserAccount(user.getUserAccount());
			if(getUser != null){
				ri = new ResponseInfo(resultCode.get("biz.vendor.change.operate.exits"),"用户账号已存在！");
				return JacksonUtil.toJson(ri); 
			}
			user.setUserAccount(user.getUserAccount().toLowerCase());//小写
			insUserService.saveUserBySelect(user);
			ri = new ResponseInfo("0","添加用户成功");
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	/**
	 * 更新用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String updateUser(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			UserAccountUpdateInfo updateUser = populateBizData(request, UserAccountUpdateInfo.class,VALIDATION_USERACCOUNTUPDATEINFO);
			InsUser user = new InsUser();
			BeanUtils.copyProperties(updateUser, user);
			if(StringUtils.isNotEmpty(user.getPassword())){user.setPassword(HashUtil.md5(user.getPassword()));}
			insUserService.updateUserBySelect(user);
			ri = new ResponseInfo("0","更新用户成功");
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}
	
	/**
	 * 删除用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "text/json;charset=UTF-8" })
	public @ResponseBody String deleteUser(HttpServletRequest request) {
		ResponseInfo ri = null;
		try {
			//SystemParamReqInfo spri = populateSystemParamReq(request);
			UserAccountOperateInfo deleteUser = populateBizData(request, UserAccountOperateInfo.class,VALIDATION_USERACCOUNTDELETEINFO);
			InsUser user = new InsUser();
			user.setId(deleteUser.getId());
			insUserService.delUserById(user);
			ri = new ResponseInfo("0","删除用户成功");
		} catch (Exception e) {
			ri = handleRespException(e);
		}
		return JacksonUtil.toJson(ri);
	}

}
