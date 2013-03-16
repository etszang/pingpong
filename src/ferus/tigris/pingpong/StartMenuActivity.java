package ferus.tigris.pingpong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StartMenuActivity  extends Activity{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);

	}

	public void onStartButtonClicked(View v) {
		Intent intent = new Intent();
		intent.setClass(this, GameFieldActivity.class);
		startActivity(intent);
	}

	public void onExitButtonClicked(View v) {
		finish();
	}

}
