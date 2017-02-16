package com.cifpay.insurance.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * 类 名 称： MainController
 * 类 描 述： 进入主页面 
 * 创 建 人：yeshengnan
 * 创建时间： 2015-12-4
 *
 */
@Controller
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
    private static final long serialVersionUID = 8079520265767541846L; 
    
	/**
	 * 
	 * 方法描述:  进入main页面
	 * @return 
	 * 返回类型： ModelAndView
	 */
	@RequestMapping(value = "/")
	public ModelAndView main() {
		ModelAndView  mav = new ModelAndView ();		
		mav.setViewName("index");
		return mav;
	} 
	
	
}