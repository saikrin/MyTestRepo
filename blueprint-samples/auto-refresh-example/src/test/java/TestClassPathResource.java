import java.net.URL;

import org.mule.util.IOUtils;


public class TestClassPathResource {
	public static void main(String args[]) {
		URL url = IOUtils.getResourceAsUrl("config/configuration.properties", TestClassPathResource.class);
		System.out.println(url);
	}

}
