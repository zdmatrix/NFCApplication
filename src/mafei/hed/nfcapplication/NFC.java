package mafei.hed.nfcapplication;

import java.security.PublicKey;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class NFC extends ActionBarActivity {

	
	Toast toast;
	int ret;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
			.add(new PlaceholderFragment(), "backworker").commit();
		}
		
		NFCApplication nfcApplication = new NFCApplication();
		ret = nfcApplication.isSupportNFC(this);
		
		new showErr().start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public class showErr extends Thread{
		@Override
		public void run(){
			switch(ret){
			case 1:
				toastShow(toast, "Support NFC!");
				break;
			case 0:
				toastShow(toast, "Open NFC Function!");
				break;
			case -1:
				toastShow(toast, "No Support NFC!");
				break;
			default:
				toastShow(toast, "Unknown Err!");
				break;
			}			
		}
	}
	
	public void toastShow(Toast tst, String str){
//		tst = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
//		tst.setGravity(Gravity.CENTER, 0, 0);
//		tst.show();
		Log.i("NFCnfc", str);
	}

}
