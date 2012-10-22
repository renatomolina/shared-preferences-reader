package br.com.fiatreader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class ReaderActivity extends Activity {

	private final String PREF_COL  = "PREF_FIAT";
	private final String PREF_VAR  = "PREF_VAR";
	private final String PACK_NAME = "br.com.fiatwriter";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        handler.sendEmptyMessage(0);
    }

    private void read(){
    	String str = "empty";
    	try {
			Context context = createPackageContext(PACK_NAME, 0);
			SharedPreferences pref = context.getSharedPreferences(PREF_COL, Context.MODE_WORLD_READABLE);
			str = pref.getString(PREF_VAR, "default");
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
    	
    	((TextView) findViewById(R.id.tw_read)).setText(str);
    }
    
    private Handler handler = new Handler(){
    	
    	@Override
    	public void handleMessage(Message m){
    		
    		handler.postDelayed(new Runnable() {
				
				public void run() {
					read();
					handler.sendEmptyMessage(0);
				}
			}, 2000);
    	}
    	
    };
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reader, menu);
        return true;
    }
}
