import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class AutoRefreshingProperties extends Properties{
        private long LAST_LOADED = System.currentTimeMillis();
        private String currentHashValue;
        
        private List<FileChangeListener> listeners = Collections.synchronizedList(new ArrayList<FileChangeListener>());

        
        public AutoRefreshingProperties(String absolutePathToPriopertiesFile) throws IOException{
                this(null, absolutePathToPriopertiesFile);
        }
        
        public AutoRefreshingProperties(URL url) throws IOException{
        	this(null, url.toExternalForm());
        }
        public AutoRefreshingProperties(Properties defaults, String absolutePathToPriopertiesFile) throws IOException{
                super(defaults);
                load(absolutePathToPriopertiesFile);
        }
        public AutoRefreshingProperties removeListener(FileChangeListener listener){
                listeners.remove(listener);
                return this;
        }
        public AutoRefreshingProperties addListener(FileChangeListener listener){
                listeners.add(listener);
                return this;
        }
        public synchronized void load(String absoluteFilePath) throws IOException {
                InputStream is = new FileInputStream(new File(absoluteFilePath));
                this.load(is);
                LAST_LOADED = System.currentTimeMillis();
                keepUpdated(absoluteFilePath);
        }
        abstract class FileWatcher implements Runnable{}
        private void keepUpdated(final String absoluteFilePath){
                Executors.newSingleThreadExecutor().submit(
                                new FileWatcher(){
                                        @Override
                                        public void run() {
                                                try {
                                                        while(true){
                                                                if (new File(absoluteFilePath).lastModified() > LAST_LOADED){
                                                                        InputStream is =  new FileInputStream(new File(absoluteFilePath));
                                                                        load(is);
                                                                        LAST_LOADED = System.currentTimeMillis();
                                                                        notifyListeners();
                                                                }
                                                                Thread.yield();
                                                                Thread.sleep(2000);
                                                        }
                                                } catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                );
        }
        private void notifyListeners(){
                final Properties properties = new Properties(this);
                for (FileChangeListener aListener : listeners){
                        aListener.notifyFileChanged(properties);
                }
        }
}

