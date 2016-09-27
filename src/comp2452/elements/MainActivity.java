package comp2452.elements;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button ViewButton;
	private EditText text;
	boolean isKelvin = false;
	int kelvinStr;
	int celsiusStr;
	static String kelvinValue;
	static String celsiusValue;
	static String stateValue;
	static String elementName;
	static String imageName;
	int tempStr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onRadioButtonClicked(View view) {
		//Assigns the user selction to the isKelvin boolean
	    boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()) {
	        case R.id.radioKelvin:
	            if (checked)
	            	isKelvin = true;
	            break;
	        case R.id.radioCelsius:
	            if (checked)
	                isKelvin = false;
	            break;
	    }
		return isKelvin;
	}
	
	public void showElement(View view) {
		//Listner for the View Button
		ViewButton = (Button)findViewById(R.id.viewButton);
		ViewButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            public void onClick(View view)
		            {  
		            	//Gets text from the temperature input field
		            	text = (EditText)findViewById(R.id.temperature);
		            	String tempString = text.getText().toString();
		            	if (tempString.matches("")) {
		            		//Displays toast if temperature field is blank
		            		Context context = getApplicationContext();
		            		CharSequence text = "Please enter a temperature";
		            		int duration = Toast.LENGTH_SHORT;

		            		Toast toast = Toast.makeText(context, text, duration);
		            		toast.show();
		            	    return;
		            	}
		            	tempStr = Integer.parseInt(tempString);
		            	//Depending on the value isKelvin the temperature is either converted
		            	//to Celsius or Kelvin
		            	if (isKelvin == true) {
		            		kelvinStr = tempStr;
		            		celsiusStr = kelvinStr - 273;

		            	}
		            	else {
		            		celsiusStr = tempStr;
		            		kelvinStr = celsiusStr + 273;
		            	}
		            	
		            	Spinner spinner = (Spinner) findViewById(R.id.elemSpinner);	
		            	//Turns spinner selection to string
		                String spin = spinner.getSelectedItem().toString();
		            	
		                if (spin.equals("Aluminium")) {
		                	//If the spinner equals Aluminium assign this to each string
		                	elementName = "Aluminium";
		                	imageName = "aluminium";
		                	if (celsiusStr <= 660) {
		                		//Calculates whether temperature is high for the state to be a liquid
		                		stateValue = "Solid";
		                	}
		                	else {
		                		stateValue = "Liquid";
		                	}
		                }
		                else if (spin.equals("Carbon")) {
		                	elementName = "Carbon";
		                	imageName = "carbon";
		                	if (celsiusStr <= 4827) {
		                		stateValue = "Solid";
		                	}
		                	else {
		                		stateValue = "Liquid";
		                	}
		                }
		                else if (spin.equals("Copper")) {
		                	elementName = "Copper";
		                	imageName = "copper";
		                	if (celsiusStr <= 1085) {
		                		stateValue = "Solid";
		                	}
		                	else {
		                		stateValue = "Liquid";
		                	}
		                }  
		                else if (spin.equals("Iron")) {
		                	elementName = "Iron";
		                	imageName = "iron";
		                	if (celsiusStr <= 1538) {
		                		stateValue = "Solid";
		                	}
		                	else {
		                		stateValue = "Liquid";
		                	}
		                }
		                else {
		                	if (celsiusStr <= 115) {
		                		elementName = "Sulfur";
			                	imageName = "sulfur";
		                		stateValue = "Solid";
		                	}
		                	else {
		                		stateValue = "Liquid";
		                	}
		                } 
		                
		            	Intent intent = new Intent(MainActivity.this, ElementView.class);
		            	//Creates the intent for the second activity
		            	kelvinValue = Integer.toString(kelvinStr);
		            	celsiusValue = Integer.toString(celsiusStr);
		            	//Converts the calculated temperatures to strings
		            	
		            	//Passes strings to the next intent
						intent.putExtra(celsiusValue, celsiusValue);
						intent.putExtra(kelvinValue, kelvinValue);
						intent.putExtra(stateValue, stateValue);
						intent.putExtra(elementName, elementName);
						intent.putExtra(imageName, imageName);
		            	startActivity(intent);  
		            }
		        });
	}
}