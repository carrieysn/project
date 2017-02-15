package com.cifpay.lc.bankadapter.universal.tool;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.cifpay.lc.bankadapter.universal.tool.MapCvtUtil;
import com.cifpay.lc.bankadapter.universal.tool.XmlCvtTool;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;


public class XmlCvtTool {

    @SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(XmlCvtTool.class);

    /**
     * 将BEAN转换成XML
     * */
    public static String transBean2Xml(Object bean) {

        return transBean2Xml(null, null, bean);
    }

    /**
     * 根据传入的根节点名称生成XML
     * @param beanname 节点名称
     * @param clazz  	需要更名的类
     * @param bean 	   	要转化的bean
     * */
    public static String transBean2Xml(String beanname, Class<?> clazz, Object bean) {
        XStream xstream = new XStream();

        if (clazz != null && StringUtils.hasText(beanname)) {
            xstream.alias(beanname, clazz);
        }

        return xstream.toXML(bean);
    }

    /**
     * 将XML转换成BEAN
     * */
    public static <T extends Serializable> T transXml2Bean(String xml) {

        return transXml2Bean(null, xml);
    }

    /**
     * 将XML转换成BEAN
     * @param clazz  	需要转换的类
     * @param xml 	   	xml串
     * */
    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T transXml2Bean(Class<T> clazz, String xml) {
        XStream xstream = new XStream();

        if (clazz != null) {
            xstream.alias(clazz.getSimpleName(), clazz);
        }

        return (T) xstream.fromXML(xml);
    }

    /**
     * 将XML转换成BEAN
     * @param rootname  根节点名
     * @param clazz  	需要转换的类
     * @param xml 	   	xml串
     * */
    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T transXml2Bean(String rootname, Class<T> clazz, String xml) {
        XStream xstream = new XStream();

        if (clazz != null && StringUtils.hasText(rootname)) {
            xstream.alias(rootname, clazz);
        }
        xstream.ignoreUnknownElements();//忽略不晓得的
        return (T) xstream.fromXML(xml);

    }

	/**
	 * 将bean转为xml<br/>
	 * 
	 * @param rootname
	 *            根节点的名称
	 * @param obj
	 *            待转化的对象
	 * @param typeClazzs
	 *            对象中其他类型的节点名与其类型的映射关系
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String transBean2Xml(String rootname, Object obj,
			Map<String, Class> typeClasses) {
		XStream xstream = new XStream();

		if (StringUtils.hasText(rootname)) {
			xstream.alias(rootname, obj.getClass());
		}
		if (typeClasses != null && typeClasses.size() > 0) {
			for (Entry<String, Class> entry : typeClasses.entrySet()) {
				xstream.aliasType(entry.getKey(), entry.getValue());
			}
		}
		return xstream.toXML(obj);
	}
	
	/**
	 * 将bean转为xml<br/>
	 * 
	 * @param rootname
	 *            根节点的名称
	 * @param obj
	 *            待转化的对象
	 * @param typeClazzs
	 *            对象中其他类型的节点名与其类型的映射关系
	 * @param superClasses
	 *            对象中其他类型的节点名与其父类型的映射关系
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String transBean2Xml(String rootname, Object obj,
			Map<String, Class> typeClasses, Map<String, Class> superClasses) {
		XStream xstream = new XStream();

		if (StringUtils.hasText(rootname)) {
			xstream.alias(rootname, obj.getClass());
		}
		if (typeClasses != null && typeClasses.size() > 0) {
			for (Entry<String, Class> entry : typeClasses.entrySet()) {
				xstream.aliasField(entry.getKey(),  superClasses.get(entry.getKey()), entry.getValue().getSimpleName());
			}
		}
		return xstream.toXML(obj).replaceAll("__", "_");
	}

	/**
	 * 将xml转化为bean
	 * 
	 * @param rootname
	 *            根节点名称
	 * @param clazz
	 *            待转化的对象类型
	 * @param xml
	 * @param typeClasses
	 *            对象中其他类型的节点名与其类型的映射关系
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T transXml2Bean(String rootname, Class<T> clazz,
			String xml, Map<String, Class> typeClasses) {
		XStream xstream = new XStream();

		if (StringUtils.hasText(rootname)) {
			xstream.alias(rootname, clazz);
		}
		if (typeClasses != null && typeClasses.size() > 0) {
			for (Entry<String, Class> entry : typeClasses.entrySet()) {
				xstream.aliasType(entry.getKey(), entry.getValue());
			}
		}
		return (T) xstream.fromXML(xml);
	}
	/**
	 * 将xml转化为bean
	 * 
	 * @param rootname
	 *            根节点名称
	 * @param clazz
	 *            待转化的对象类型
	 * @param xml
	 * @param typeClasses
	 *            对象中其他类型的节点名与其类型的映射关系
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T transXml2Bean(String rootname, Class<T> clazz,
			String xml, Map<String, Class> typeClasses,Map<String,Class> superClasses) {
		XStream xstream = new XStream();

		if (StringUtils.hasText(rootname)) {
			xstream.alias(rootname, clazz);
		}
		if (typeClasses != null && typeClasses.size() > 0) {
			for (Entry<String, Class> entry : typeClasses.entrySet()) {
				xstream.aliasField(entry.getKey(),  superClasses.get(entry.getKey()), entry.getValue().getSimpleName());
			}
		}
		return (T) xstream.fromXML(xml);
	}
    /**
     * 将MAP转换成XML
     * */
    public static String transMap2Xml(Map<?, ?> map) {

        return transMap2Xml("root", null, map);
    }

    /**
     * 根据传入的根节点名称生成XML
     * @param rootname 	根节点名称
     * @param entryname map中的Entry节点名称
     * @param Map 	   	要转化的MAP
     * */
    public static String transMap2Xml(String rootname, String entryname, Map<?, ?> map) {
        XStream xstream = new XStream();
        xstream.registerConverter(new MapEntryConverter());

        if (StringUtils.hasText(rootname)) {

            if (map.getClass().getName().contains("HashMap")) {
                xstream.alias(rootname, Map.class);
            } else {
                xstream.alias(rootname, map.getClass());
            }

        }

        if (StringUtils.hasText(entryname)) {
            xstream.alias(entryname, Map.Entry.class);
        }

        return xstream.toXML(map);
    }

    /**
     * 将XML转换成Map
     * */
    @SuppressWarnings("rawtypes")
	public static Map transXml2Map(String xml) {

        return transXml2Map("root", null, new HashMap(), xml);
    }

    /**
     * 将XML转换成Map
     * @param rootname 	根节点名称
     * @param entryname map中的Entry节点名称
     * @param Map 	   	要转化的MAP
     * @param xml 	   	xml字符串
     * */
    public static Map<?, ?> transXml2Map(String rootname, String entryname, Map<?, ?> map, String xml) {
        XStream xstream = new XStream();

        xstream.registerConverter(new MapEntryConverter());

        if (StringUtils.hasText(rootname) && map != null) {
            xstream.alias(rootname, map.getClass());
        }

        if (StringUtils.hasText(entryname)) {
            xstream.alias(entryname, Map.Entry.class);
        }

        return (Map<?, ?>) xstream.fromXML(xml);

    }

    /**
     * 将LIST转换成XML
     * */
    public static String transList2Xml(List<?> list) {

        return transList2Xml(null, null, list);
    }

    /**
     * 根据传入的根节点名称生成XML
     * @param clazzname 节点名称
     * @param clazz  	需要更名的类
     * @param list 	   	要转化的List
     * */
    @SuppressWarnings("rawtypes")
	public static String transList2Xml(String clazzname, Class clazz, List list) {
        XStream xstream = new XStream();

        if (clazz != null && StringUtils.hasText(clazzname)) {
            xstream.alias(clazzname, clazz);
        }

        return xstream.toXML(list);

    }

    /**
     * 将LIST转换成XML
     * @param clazz  		需要更名的类
     * @param elementname  	每个元素的名称
     * @param xml  			xml字串
     * */
    @SuppressWarnings("rawtypes")
    public static List transXML2List(String elementname, Class clazz, String xml) {
        XStream xstream = new XStream();

        if (clazz != null && StringUtils.hasText(elementname)) {
            xstream.alias(elementname, clazz);
        }

        return (List) xstream.fromXML(xml);

    }

    /**
     * 将LIST转换成XML
     * @param clazz  	需要更名的类
     * @param xml  		xml字串
     * */
    @SuppressWarnings("rawtypes")
    public static List transXML2List(Class clazz, String xml) {
        XStream xstream = new XStream();

        if (clazz != null) {
            xstream.alias(clazz.getSimpleName(), clazz);
        }

        return (List) xstream.fromXML(xml);

    }

    /**
     * 将LIST转换成XML
     * */
    @SuppressWarnings("rawtypes")
    public static List transXML2List(String xml) {

        return transXML2List(null, xml);
    }

    /**
     * MAP转XML --> XML格式变化
     * */
    @SuppressWarnings("rawtypes")
    private static class MapEntryConverter implements Converter {

        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;

                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue() == null ? "" : entry.getValue().toString());
                writer.endNode();

            }

        }

        @SuppressWarnings("unchecked")
		public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

            Map<String, Object> map = new HashMap<String, Object>();

            while (reader.hasMoreChildren()) {
                reader.moveDown();

                String key = reader.getNodeName(); // nodeName aka element's name
                if ("list".equalsIgnoreCase(key)  || "items".equalsIgnoreCase(key)) {
                    List<Object> list = new ArrayList<Object>();
                    
                    while (reader.hasMoreChildren()) {
                        reader.moveDown();
                        String nodeName = reader.getNodeName();
                        String value = reader.getValue();
                        //Pair pair = new Pair(nodeName, value);
                        list.add(value);
                        reader.moveUp();
                    }

                    map.put("list", list);
                } else if("CifSettleResponse".equalsIgnoreCase(key) 
                		|| "Order".equalsIgnoreCase(key) 
                		|| "page".equalsIgnoreCase(key)
                		|| "PaymentLog".equalsIgnoreCase(key)){
                	continue;
                } else {
                    String value = reader.getValue();
                    map.put(key, value);
                }

                reader.moveUp();
            }

            return map;
        }

    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E extends Serializable> List<E> analyzeXmlToolList(Map map,Class<E> clazz){
    	
    	List<E> returnList = new ArrayList<E>();
    	
    	List<Map> mapList = (List<Map>)map.get("params");
		for(Map param : mapList){
			E element = (E)MapCvtUtil.toJavaBean(clazz, param);
			returnList.add(element);
		}
    	
    	return returnList;
    }

}
