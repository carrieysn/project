package mock.merchant.controller;

import mock.merchant.bean.MerchantRequest;
import mock.merchant.common.tool.InputStreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sweet on 16-9-22.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(path = "/success", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView success(HttpServletRequest request) {
        Map<String, String> form = new HashMap<String, String>();

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey();
            String value = String.join(",", entry.getValue());
            form.put(key, value);
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("form", form);
        return new ModelAndView("notice/success", data);
    }

    @RequestMapping(path = "/asynchronous", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String asynchronous(HttpServletRequest request) throws Exception {

        String body = InputStreamUtils.InputStreamTOString(request.getInputStream());
        logger.debug("收到asynchronous通知：{}", body);

        //MerchantRequest response = new MerchantRequest();
        //response.setEncodedJsonData("test");
        return "success";
    }

//    public static void main(String[] args) {
//
//        String[] value = new String[]{"123", "456"};
//        String result = String.join(",", value);
//        System.out.println(result);
//        System.out.println("".getClass().getName());
//    }
}
