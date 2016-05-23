package cn.com.src;
import cn.com.chenzheng_java.utils.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/** 
 * @author chenzheng_java 
 * handler��message�������� 
 */
public class HanlderMessageTest extends Activity implements OnClickListener{
	Button button ;
	MyHandler handler ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(this);

	}

	// �����Լ���handler  
	private class MyHandler extends Handler{
		/** 
		 * ʹ��Ĭ�ϵĹ��캯�����Ὣhandler�󶨵�ǰUI�̵߳�looper�� 
		 * �����ʹ�ö��߳������ǲ���ʹ��Ĭ�ϵĹ��췽���ġ� 
		 */
		public MyHandler() {
			super();
		}

		public MyHandler(Looper looper){
			super(looper);
		}

		// ��������message,�÷����ɸ����н��м̳�.  
		@Override
		public void handleMessage(Message msg) {
			int whatNumber = msg.what;
			Bundle bundle = (Bundle)msg.obj;
			Log.i("what", whatNumber+"");
			Log.i("����", bundle.getString("name"));
			Log.i("�Ա�", bundle.getString("sex"));
			Log.i("����", bundle.getString("age"));
			super.handleMessage(msg);
		}
	}

	// ���Զ��������,һ�㶼��ʵ��Runnable  
	private class MyThread implements Runnable {
		/** 
		 * �÷������ڲ����о��������ʵ�֣����� ����. 
		 * Message�а��������ui�߳̽��������ݣ�ԭ���ϣ����߳��ڲ��� 
		 * ��ò�Ҫֱ�ӵ���handler�ġ� 
		 * */
		@Override
		public void run() {

			try {
				Thread.sleep(6000);
				Message message = Message.obtain(handler);
				message.what = 10 ;
				Bundle bundle = new Bundle();
				bundle.putString("name", "chenzheng");
				bundle.putString("sex", "��ү��");
				bundle.putString("age", "�����겻��");
				message.obj = bundle ;
				Log.i("֪ͨ", "��ʼ��message��Ŷ");
				Log.i("֪ͨthread_id:", ""+Thread.currentThread().getId());
				message.sendToTarget();
			} catch (Exception e) {
				Log.i("֪ͨ", "�߳�sleepʱ�����ˣ�");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View v) {
		Log.i("֪ͨthread_id:", ""+Thread.currentThread().getId());

		// ����һ������Looper���̣߳��������û��HandlerThread�ĵ��ã���ֱ�ӽ���ߵ�MyThread�ŵ�UI�̶߳���  
		HandlerThread myHandlerThread = new HandlerThread("chenzheng_java");
		// �������߳�  
		myHandlerThread.start();
		// ��handler�󶨵����߳�  
		handler = new MyHandler(myHandlerThread.getLooper());
		// �����߳���ִ������   
		handler.post(new MyThread());
	}
}