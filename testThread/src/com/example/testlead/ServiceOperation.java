package com.example.testlead;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/**
 *IntentService相对于Service有很多好处
 * @author jsjxy
 *
 */
public class ServiceOperation extends IntentService {

	private static final String TAG = null;
	public ServiceOperation(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public ServiceOperation() {
		super("ServiceOperation");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
      //   Log.i(TAG, "哈哈哈哈");
		//Inetent是从Activity发过来的，携带识别参数，根据参数不同来做不同的行为
		String data =intent.getExtras().getString("one");
	   if(data.equals("你好")){
		    Log.i(TAG, "Operation1");    
	   }
	   else{
		   Log.i(TAG, "Operation2");
	   }
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i(TAG,"服务开始");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "服务建立");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "服务销毁");
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onBind(intent);
	}
	@Override
	public void setIntentRedelivery(boolean enabled) {
		// TODO Auto-generated method stub
		super.setIntentRedelivery(enabled);
	}

}
