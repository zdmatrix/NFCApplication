package mafei.hed.nfcapplication;

import java.util.Set;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import mafei.hed.nfcapplication.NFCMsgCode;

public class NFCApplication {	
	static IsoDep isoDep;
		
	public static int isSupportNFC(Context context){
		int ret;
		NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
		if(nfcAdapter == null){
			ret = NFCMsgCode.nNOT_SUPPORT_NFC;		
		}else{
			if(!nfcAdapter.isEnabled()){
				ret = NFCMsgCode.nNOT_OPEN_NFC;
			}else{
				ret = NFCMsgCode.nSUPPORT_NFC;
			}
		}
		return ret;
	}
	
	public static int isConnectTag(Intent intent){
		int ret;
		Set<String> strings = intent.getCategories();
		if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
			Tag tagfromintentTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			isoDep = IsoDep.get(tagfromintentTag);
			if(!isoDep.isConnected()){
				try {
					isoDep.connect();
					ret = NFCMsgCode.nTAG_CONNECT;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					StackTraceElement [] messages=e.getStackTrace();
					ret = NFCMsgCode.nTAG_CONNECT_FAILED;
				}
			}
			ret = NFCMsgCode.nTAG_CONNECT;
		}else{
			ret = NFCMsgCode.nNO_NFC_INTENT;
		}
		return ret;
	}
	
	public static byte[] DataTransfer(byte[] apdu){
		byte[] retvalue;
		isoDep.setTimeout(30000);
		try {
			retvalue = isoDep.transceive(apdu);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			retvalue = null;
		}
		
		return retvalue;
	}
}
