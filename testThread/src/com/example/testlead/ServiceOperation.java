package com.example.testlead;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/**
 *IntentService�����Service�кܶ�ô�
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
      //   Log.i(TAG, "��������");
		//Inetent�Ǵ�Activity�������ģ�Я��ʶ����������ݲ�����ͬ������ͬ����Ϊ
		String data =intent.getExtras().getString("one");
	   if(data.equals("���")){
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
		Log.i(TAG,"����ʼ");
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
		Log.i(TAG, "������");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "��������");
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
