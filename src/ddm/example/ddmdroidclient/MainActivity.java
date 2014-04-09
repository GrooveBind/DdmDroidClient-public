package ddm.example.ddmdroidclient;
//test commit
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import models.ddm.User;
import ddm.controllers.JsonFetcher;
import ddm.controllers.WebserviceException;
import ddm.controllers.WebserviceRequestor;
import ddm.example.ddmdroidclient.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements WebserviceRequestor<User[]> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

    /**
     * Called when a menu item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case id.action_settings:
            //finish();
        	//fetch network data asynchronously
        	JsonFetcher<User[]> asyncTask = new JsonFetcher<User[]>(this, User[].class, "/users");
        	asyncTask.execute(new Object[0]);//pass no arguments
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    /**
     * implementation of WebServiceRequestor for rest/json 
     */
	public void onNetworkSuccess(User[] retval) {
		List<User> persons = Arrays.asList(retval);
		for (Iterator<User> iterator = persons.iterator(); iterator.hasNext();) {
			User person = iterator.next();
			Log.d(getLocalClassName(), "Fetched Json object: " + person.getClass() + " First Name: " + person.getFirstName());
		}
	}
    
    /**
     * implementation of WebServiceRequestor
     */
	public void onNetworkFail(WebserviceException e) {
		Log.e(getLocalClassName(), "onNetworkFail. Exception: " + e.getMessage());
	}

}
