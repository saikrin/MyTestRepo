import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.mule.util.FileUtils;


public class MyFileChangedReloadingStrategy extends FileChangedReloadingStrategy {
	
	private int hashCode;

	@Override
	protected File getFile() {
		// TODO Auto-generated method stub
		System.out.println(super.getFile());
		return super.getFile();
	}

	@Override
	protected boolean hasChanged() {
		// TODO Auto-generated method stub
		System.out.println(super.hasChanged());
		
		try {
			hashCode = FileUtils.readFileToString(super.getFile()).hashCode();
			System.out.println(hashCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.hasChanged();
	}

	@Override
	protected void updateLastModified() {
		// TODO Auto-generated method stub
		System.out.println("super.updateLastModified()");
		try {
			hashCode = FileUtils.readFileToString(super.getFile()).hashCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.updateLastModified();
	}

}
