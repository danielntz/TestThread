package com.example.testlead;
/**
 * 用来理解后台Service
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
     
	 public  static final String TAG = null;

	    public    TextView   test;
	   private      int i = 0;
	    
	  Handler  hand = new Handler(){
		    public void handleMessage(Message msg) {
		    	  
		     int     i =    	  msg.getData().getInt("shuzi");
		    	test.setText(i +"");
		   
	  };
	  };
	    
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_main);
	   test = (TextView)findViewById(R.id.test_zi);
		//开启后台服务
		Intent  operation1 = new Intent(getApplicationContext(), ServiceOperation.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("one", "你好");
		operation1.putExtras( bundle);
		startService(operation1);
		//开启第二个服务
		Intent operation2  = new Intent(getApplicationContext(),ServiceOperation.class);
		Bundle  bundel1= new Bundle();
		bundel1.putSerializable("one", "谢谢");
		operation2.putExtras(bundel1);
		startService(operation2);
    //   zhuandong();
		new Thread(new TestThread1()).start();
	//	new Thread(new testThread()).start();
		
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
	
	/*public class testThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			       
			         while(i < 5){
			        	   
			        	     runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									    test.setText( i +"");
								}
							});
			        	 try {
							Thread.sleep(1000);
						
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	 i ++ ;
			        	 
			         }
			         Thread.interrupted();
		}

	}*/
	
	public  class TestThread1 implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			        while( i < 5){
			        	 
			        	      Message  msg = new Message();
			        	      Bundle   bundle = new Bundle();
			        	      bundle.putInt("shuzi", i);
			        	      msg.setData(bundle);
			        	      hand.sendMessage(msg);
			        	      try {
								Thread.sleep(1000);
								 i++;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			        	
			        	
			        }
		}
		
	}
}

