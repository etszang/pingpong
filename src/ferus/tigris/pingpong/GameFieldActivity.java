package ferus.tigris.pingpong;

import data.Config;
import data.DB;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class GameFieldActivity extends Activity {

	@SuppressLint("ShowToast") @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		
		setContentView(new GameView(this, metrics.xdpi / 192));
		
		Config conf = new Config(getPreferences(MODE_PRIVATE));
		DB db = new DB(this);

		String lev = db.loadLevel();
		Toast.makeText(this, lev, 10).show();
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_freeman_field, menu);
		return true;
	}
	
	public void menu() {
		finish();
	}
}
