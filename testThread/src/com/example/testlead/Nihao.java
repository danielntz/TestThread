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
 * handler和message测试用例 
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

	// 声明自己的handler  
	private class MyHandler extends Handler{
		/** 
		 * 使用默认的构造函数，会将handler绑定当前UI线程的looper。 
		 * 如果想使用多线程这里是不能使用默认的构造方法的。 
		 */
		public MyHandler() {
			super();
		}

		public MyHandler(Looper looper){
			super(looper);
		}

		// 处理具体的message,该方法由父类中进行继承.  
		@Override
		public void handleMessage(Message msg) {
			int whatNumber = msg.what;
			Bundle bundle = (Bundle)msg.obj;
			Log.i("what", whatNumber+"");
			Log.i("名称", bundle.getString("name"));
			Log.i("性别", bundle.getString("sex"));
			Log.i("年龄", bundle.getString("age"));
			super.handleMessage(msg);
		}
	}

	// 我自定义的任务,一般都会实现Runnable  
	private class MyThread implements Runnable {
		/** 
		 * 该方法的内部进行具体的任务实现，比如 下载. 
		 * Message中包含着想和ui线程交互的数据，原则上，在线程内部是 
		 * 最好不要直接调用handler的。 
		 * */
		@Override
		public void run() {

			try {
				Thread.sleep(6000);
				Message message = Message.obtain(handler);
				message.what = 10 ;
				Bundle bundle = new Bundle();
				bundle.putString("name", "chenzheng");
				bundle.putString("sex", "纯爷们");
				bundle.putString("age", "生卒年不详");
				message.obj = bundle ;
				Log.i("通知", "开始发message了哦");
				Log.i("通知thread_id:", ""+Thread.currentThread().getId());
				message.sendToTarget();
			} catch (Exception e) {
				Log.i("通知", "线程sleep时出错了！");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View v) {
		Log.i("通知thread_id:", ""+Thread.currentThread().getId());

		// 创建一个包含Looper的线程，这里如果没有HandlerThread的调用，会直接将后边的MyThread放到UI线程队列  
		HandlerThread myHandlerThread = new HandlerThread("chenzheng_java");
		// 启动新线程  
		myHandlerThread.start();
		// 将handler绑定到新线程  
		handler = new MyHandler(myHandlerThread.getLooper());
		// 在新线程中执行任务   
		handler.post(new MyThread());
	}
}