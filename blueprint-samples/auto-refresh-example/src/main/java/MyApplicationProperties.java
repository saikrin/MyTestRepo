import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class MyApplicationProperties {
	private PropertiesConfiguration configuration = null;
	
	public MyApplicationProperties() {
		System.out.println("MyApplicationProperties ...");
	}

	public MyApplicationProperties(String location)
	{
		System.out.println("MyApplicationProperties ..." + location);
		
		try {
			configuration = new PropertiesConfiguration(location);
			configuration
					.setReloadingStrategy(new MyFileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(final String key) {
		return (String) configuration.getProperty(key);
	}
}