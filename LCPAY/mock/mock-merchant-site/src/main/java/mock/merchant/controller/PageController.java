package mock.merchant.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 进入页面
	 * @return
	 */
	@RequestMapping(path = "/page/*")
	public String toPage(HttpServletRequest req){
		logger.debug("[PageController]_[toPage]");
		String path = req.getServletPath();
		path = path.substring(path.lastIndexOf("/") + 1,path.length());
		return path;
	}

	
}
