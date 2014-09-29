package mafei.hed.nfcapplication;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.R.integer;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NFC extends ActionBarActivity {

/*	
	Toast toast;
	int ret;
	int clickcount;	
	Button button1;
	*/
	NFCApplication nfcApplication;
	NfcAdapter nfcAdapter;
	PendingIntent pendingIntent;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nfcAdapter = NfcAdapter.getDefaultAdapter();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
			.add(new PlaceholderFragment(), "backworker").commit();
		}
		nfcApplication = new NFCApplication();
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		
/*		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(new ClickEvent());	
		clickcount = 0;
	*/	
	}
	
	@Override
	public void onResume(){
		super.onResume();
		nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
	}
	
	@Override
	public void onNewIntent(Intent intent){
		
		setIntent(intent);
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
/*	
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

	public class ClickEvent implements View.OnClickListener{
		@Override
		public void onClick(View v){
			clickcount += 8;
			clickcount %= 100;
			String write = "80bf0100";
//			ret = nfcApplication.isSupportNFC(this);
			Intent intent = getIntent();
			ret = nfcApplication.isConnectTag(intent);
			if(ret == NFCMsgCode.nTAG_CONNECT){
				String dataString = String.format("%1$d", clickcount);
				String apduString = write + String.format("%1$02x", dataString.length());
				for(int i = 0; i < dataString.length(); i ++){
					int n = clickcount % (int)Math.pow(10,(i + 1));						
					apduString = apduString + Integer.toString(n + 48, 16);
				}
				char[] ch = apduString.toCharArray();
				byte[] apdu = new byte[apduString.length() / 2];
				for(int k = 0, j = 0; j < apduString.length(); k++){
					String string = String.valueOf(ch, j, 2);
					int n = Integer.parseInt(string, 16);
					apdu[k] = (byte)n;
					j += 2;
				}
				byte[] retvalue = nfcApplication.DataTransfer(apdu);
				Log.i("Stop", "test");
			}
			new showErr().start();
		}
	}
*/
}
