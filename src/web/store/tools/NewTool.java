package web.store.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NewTool {
	
	public String getValue(String key){
		Properties p = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/news.properties");
		try {
			p.load(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
