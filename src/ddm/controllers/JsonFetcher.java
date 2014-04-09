package ddm.controllers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import android.os.AsyncTask;
import android.util.Log;

/**
 * Provides ws data asynchronously via WebServiceRequestor callbacks. 
 * 
 * @author aaronjevans
 *
 * @param <T>
 */
public class JsonFetcher<T> extends AsyncTask<Object, Void, T> {
	protected WebserviceRequestor<T> requestor;
	protected WebserviceException caughtException;
	HttpClient client;
	HttpGet post;
	private T retval;
	private Class<T> clazz;

	private static final String TAG = "AsyncRestService";
		//emulator
		//service.setBaseUrl("http://10.0.2.2:9000");
	//local device
	//public static final String SERVER_BASE_URL = "http://192.168.1.4:9000";
	
	public static final String SERVER_BASE_URL = "http://demo.deltadatamodel.com:9000";
	
	public JsonFetcher(WebserviceRequestor<T> requestor,  Class<T> clazz, String path) {
		super();
		this.requestor = requestor;
		this.clazz = clazz;
		//Create an HTTP client
		client = new DefaultHttpClient();
		//orig
		//HttpPost post = new HttpPost(SERVER_URL);
		post = new HttpGet(SERVER_BASE_URL.concat(path));
	}
	
	/** 
	 * Eventually called by service.excecute; it runs as a background task.
	 * @param params
	 * @return
	 * @throws Exception
	 */
	//protected abstract T doExecute(Object... params) throws Exception;

	/*** Hooks provided by AsyncTask ***/
	protected T doInBackground(Object... params){
		try {
			//Perform the request and check the status code
			HttpResponse response = client.execute(post);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				
				try {
					//Read the server response and attempt to parse it as JSON
					Reader reader = new InputStreamReader(content);
					GsonBuilder gsonBuilder = new GsonBuilder();
					//gsonBuilder.registerTypeAdapterFactory(new GsonTypeFactory()).create();
                                            //gsonBuilder.setDateFormat("M/d/yy hh:mm a");
					Gson gson = gsonBuilder.create();
					retval = gson.fromJson(reader, clazz);
					content.close();

				} catch (Exception ex) {
					Log.e(TAG, "Failed to parse JSON due to: " + ex);
					handleException(ex);
				}
			} else {
				Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());
				handleException(caughtException);
			}
		} catch(Exception ex) {
			Log.e(TAG, "Failed to send HTTP POST request due to: " + ex);
			handleException(ex);
		}
		return retval;
	}
	
	private void handleException(Exception e) {
		caughtException = new WebserviceException(e);
		 this.cancel(false);
	}
	
	protected void onPostExecute(T result) {
		Log.i(this.getClass().getName(), "Rest request succeeded for " + this.toString());
		requestor.onNetworkSuccess(result);
	}
	
    protected void onCancelled() {
        super.onCancelled();
        Log.e(this.getClass().getName(), "Exception caught in " + this.toString() + ". Calling requestor's onNetworkFail.");
        if(caughtException != null){
        	caughtException.printStackTrace(); 
        	requestor.onNetworkFail(caughtException);
        }
        // Perform error post processing here...
    }	
}
