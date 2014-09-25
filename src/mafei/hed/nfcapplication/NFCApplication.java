package mafei.hed.nfcapplication;

import android.content.Context;
import android.nfc.NfcAdapter;

public class NFCApplication {
	NfcAdapter nfcAdapter;
	
	public int isSupportNFC(Context context){
		int ret = 0;
		nfcAdapter = NfcAdapter.getDefaultAdapter(context);
		if(nfcAdapter == null){
			ret = -1;
			
		}else{
			if(!nfcAdapter.isEnabled()){
				ret = 0;
				
			}else{
				ret = 1;
			}
		}
		return ret;
	}

}
