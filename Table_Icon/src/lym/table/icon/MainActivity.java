package lym.table.icon;

import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;

public class MainActivity extends Activity {
	ImageView imageView;
	CreateShortcut createShortcut;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createShortcut = new CreateShortcut(this);
		createShortcut.createShortcut(); 
	}

}
