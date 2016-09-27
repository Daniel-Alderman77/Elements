package comp2452.elements;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_element_view);
		
		//Retrieves strings from the last intent
		Intent intent = getIntent();
		String kelvinValue = intent.getStringExtra(MainActivity.kelvinValue);
		String celsiusValue = intent.getStringExtra(MainActivity.celsiusValue);
		String stateValue = intent.getStringExtra(MainActivity.stateValue);
		String elementName = intent.getStringExtra(MainActivity.elementName);
		String imageName = intent.getStringExtra(MainActivity.imageName);
		
		//Checks string imageName to decide which image to use
		int x = 0;
		if (imageName.equals("aluminium")) {
			x = R.drawable.aluminium;
		}
		else if (imageName.equals("carbon")) {
			x = R.drawable.carbon;
		}
		else if (imageName.equals("copper")) {
			x = R.drawable.copper;
		}
		else if (imageName.equals("iron")) {
			x = R.drawable.iron;
		}
		else if (imageName.equals("sulfur")) {
			x = R.drawable.sulfur;
		}
		
		//Converts the image name to Int so that the image can be diplayed
		String str = Integer.toString(x);		
		int resId = getResources().getIdentifier(str, "drawable", getPackageName());		
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setImageResource(resId);
		
		//Displays strings from the first activity
		TextView name = (TextView) findViewById(R.id.elementName);
		name.setText(elementName);
		TextView kelvinTemp = (TextView) findViewById(R.id.tempValueK);
		kelvinTemp.setText(kelvinValue);
		TextView celsiusTemp = (TextView) findViewById(R.id.tempValueC);
		celsiusTemp.setText(celsiusValue);
		TextView state = (TextView) findViewById(R.id.stateValue);
		state.setText(stateValue);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.element_view, menu);
		return true;
	}	
}