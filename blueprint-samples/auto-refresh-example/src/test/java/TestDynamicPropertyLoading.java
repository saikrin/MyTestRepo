import static org.junit.Assert.*;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDynamicPropertyLoading {
	static final String MY_PROPERTY_FILE = "config/configuration.properties";
	static final File destFile = new File(
			"target/test-classes/config/configuration.properties");
	static Properties properties = null;

	@BeforeClass
	public static void beforeClass() throws Exception {
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

	@Test
	public void testPropertyDynamicLoading() throws Exception {
		testManualCopyUpdate();
		assertEquals("foo", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration1.properties");
		Thread.sleep(2000);
		assertEquals("foo1", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration2.properties");
		Thread.sleep(2000);
		assertEquals("foo2", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration3.properties");
		Thread.sleep(2000);
		assertEquals("foo3", properties.getProperty("my.property1"));

		copyNewPropertiesFile("src/test/resources/config/configuration.properties");
		Thread.sleep(2000);
		assertEquals("foo", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration1.properties");
		Thread.sleep(2000);
		assertEquals("foo1", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration2.properties");
		Thread.sleep(2000);
		assertEquals("foo2", properties.getProperty("my.property1"));

		copyNewPropertiesFile("target/test-classes/master-config/configuration3.properties");
		Thread.sleep(2000);
		assertEquals("foo3", properties.getProperty("my.property1"));

		copyNewPropertiesFile("src/test/resources/config/configuration.properties");
		Thread.sleep(2000);
		assertEquals("foo", properties.getProperty("my.property1"));

	}

	private synchronized void copyNewPropertiesFile(String fromPath)
			throws Exception {
		FileUtils.copyFile(new File(fromPath), destFile);
	}

	public void testManualCopyUpdate() throws Exception {
		System.out.println("Checking manual file changes...^C to exit");
		System.out.println("my.property1="
				+ properties.getProperty("my.property1"));
		System.out.println("Change : " + destFile.getAbsolutePath()
				+ " to verify whether the property gets loaded");
		while (true) {
			System.out.println("my.property1="
					+ properties.getProperty("my.property1"));
			Thread.sleep(5000);
		}
	}
	
	public static void main(String args[]) throws Exception {
		new TestDynamicPropertyLoading().testManualCopyUpdate();
	}

}
