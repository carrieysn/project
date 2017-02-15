package mock.merchant.common.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * properties加载
 * 
 * @author hufeng
 * 
 */
public class PropertiesTool {

	/**
	 * 加载文件
	 * 
	 * @param file
	 *            文件路径
	 * @return
	 */
	public static Properties load(String file) {
		Properties prop = new Properties();
		InputStream is = PropertiesTool.class.getResourceAsStream(file);
		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				prop.load(reader);
			} catch (IOException e) {
				throw new RuntimeException("load properties file error, file=" + file, e);
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new RuntimeException("properties file not exist, file="+file);
		}
		return prop;
	}

}
