import  org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mule.util.IOUtils;

public class TestDynamicPropertyLoadingC {
	 final String MY_PROPERTY_FILE = "config/configuration.properties";
	 final File destFile = new File(
			"target/test-classes/config/configuration.properties");
	 Properties properties = null;
	 
	 String filePath;
	 
	 public TestDynamicPropertyLoadingC() throws Exception {
		init();
	}
	public  void init() throws Exception {
		URL fileURL = 
				IOUtils.getResourceAsUrl("config/configuration.properties", TestDynamicPropertyLoadingC.class);
		if (fileURL == null) {
			return;
		}
		System.out.println("File URL: " + fileURL);
		filePath = new File(fileURL.toURI()).getAbsolutePath();
		System.out.println("File path: " + filePath);
		properties = new AutoRefreshingProperties(null, filePath).addListener(
				new FileChangeAdapter() {
					@Override
					public void notifyFileChanged(Properties properties) {
						System.out
								.println("LISTENER1 : Properties have changed...");
						System.out.println("my.property1="
								+ properties.getProperty("my.property1"));
					}
				}).addListener(new FileChangeAdapter() {
			@Override
			public void notifyFileChanged(Properties properties) {
				System.out.println("LISTENER2: Properties have changed...");
				System.out.println("my.property1="
						+ properties.getProperty("my.property1"));
			}
		});
	}

	 
	@BeforeClass
	public  void beforeClass() throws Exception {
		properties = new AutoRefreshingProperties(null,
				destFile.getAbsolutePath()).addListener(
				new FileChangeAdapter() {
					@Override
					public void notifyFileChanged(Properties properties) {
						System.out
								.println("LISTENER1 : Properties have changed...");
						System.out.println("my.property1="
								+ properties.getProperty("my.property1"));
					}
				}).addListener(new FileChangeAdapter() {
			@Override
			public void notifyFileChanged(Properties properties) {
				System.out.println("LISTENER2: Properties have changed...");
				System.out.println("my.property1="
						+ properties.getProperty("my.property1"));
			}
		});
	}


	public void testManualCopyUpdate() throws Exception {
		System.out.println("Checking manual file changes...^C to exit");
		System.out.println("my.property1="
				+ properties.getProperty("my.property1"));
		System.out.println("Change : " + filePath + " to verify whether the property gets loaded");
		while (!properties.getProperty("my.property1").equalsIgnoreCase("amjad")) {
			System.out.println("my.property1="
					+ properties.getProperty("my.property1"));
			Thread.sleep(5000);
		}
	}
	
	public  void main(String args[]) throws Exception {
		new TestDynamicPropertyLoading().testManualCopyUpdate();
	}

}
