package mafei.hed.nfcapplication;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;
import mafei.hed.nfcapplication.NFCMsgCode;

public class NFCApplication {
	
	
	IsoDep isoDep;
	int ret;

	
	public int isSupportNFC(Context context){
		
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
	
	public int isConnectTag(Intent intent){
		
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
					ret = NFCMsgCode.nTAG_NOT_CONNECT;
				}
			}
			ret = NFCMsgCode.nTAG_CONNECT;
		}else{
			ret = NFCMsgCode.nTAG_NOT_SUPPORT;
		}
		return ret;
	}
	
	public byte[] DataTransfer(byte[] apdu){
		byte[] retvalue;
		this.isoDep.setTimeout(10000);
		try {
			retvalue = this.isoDep.transceive(apdu);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			retvalue = null;
		}
		
		return retvalue;
	}
}
