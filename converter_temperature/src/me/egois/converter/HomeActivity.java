package me.egois.converter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnClickListener {
	
	// The temperature measure units
	private CharSequence[] mTemperatureUnits;
	
	// The selected index of input measure unit
	private int mSelectedInputUnit = 0;
	
	// The selected index of output measure unit
	private int mSelectedOutputUnit = 0;
	
	// The flag whether the current dialog aimed for input/output
	private boolean mSelectedInputUnitMode = true;
	
	// TextView reference of input/output unit selector
	private TextView mInputSelector, mOutputSelector;
	
	/**
	 * Launch dialog for input/output measure unit selection
	 */
	private void actionChoiceDialog() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		Resources r = getResources();
		String title; int selected;
		if (mSelectedInputUnitMode) {
			title = r.getString(R.string.str_dialog_input_title);
			selected = mSelectedInputUnit;
		} else {
			title = r.getString(R.string.str_dialog_output_title);
			selected = mSelectedOutputUnit;			
		}
		
		builder.setTitle(title); 
		builder.setSingleChoiceItems(mTemperatureUnits, selected, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				choiceSelectedCallback(which);
			}
		})
		.setCancelable(false)
	    .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
		
		builder.create().show();
	}

	/**
	 * Handle onClick event of convert button
	 */
	private void handleConvertButtonClick() {
		
		EditText fromText = (EditText) findViewById(R.id.input_num);
		String fromValue = fromText.getText().toString();
		if (fromValue.isEmpty())  return;
		
		EditText toText = (EditText) findViewById(R.id.output_num);
		
		double from = Double.valueOf(fromValue);
		double result = convert(from, mTemperatureUnits[mSelectedInputUnit], mTemperatureUnits[mSelectedOutputUnit]);
		toText.setText(String.valueOf(result));
	}

	/**
	 * Handle dialog unit choice selection
	 * 
	 * @param which index of selected item
	 */
	private void choiceSelectedCallback(int which) {
		if (mSelectedInputUnitMode) {
			mSelectedInputUnit = which;
			mInputSelector.setText(mTemperatureUnits[which]);
		} else {
			mSelectedOutputUnit = which;
			mOutputSelector.setText(mTemperatureUnits[which]);
		}
	}
	
	/**
	 * Perform unit conversion
	 * 
	 * @param num the temperature value to be converted
	 * @param from the temperature unit of source
	 * @param to the temperature unit of target
	 * 
	 * @return
	 */
	private double convert(double num, CharSequence from, CharSequence to) {
		if (from == to) return num;
		
		Toast.makeText(this, "From "+from+" to "+to, Toast.LENGTH_SHORT).show();
		
		Resources r = getResources();
		
		if (from.equals(r.getString(R.string.str_fahrenheit))) {
			num = Celcius.fromFahrenheit(num);
		} else if (from.equals(r.getString(R.string.str_reaumur))) {
			num = Celcius.fromReaumur(num);
		} else if (from.equals(r.getString(R.string.str_kelvin))) {
			num = Celcius.fromKelvin(num);
		} else if (from.equals(r.getString(R.string.str_romer))) {
			num = Celcius.fromRomer(num);
		} else if (from.equals(r.getString(R.string.str_rankine))) {
			num = Celcius.fromRankine(num);
		} else if (from.equals(r.getString(R.string.str_newton))) {
			num = Celcius.fromNewton(num);
		}
		
		if (to.equals(r.getString(R.string.str_fahrenheit))) {
			num = Celcius.toFahrenheit(num);
		} else if (to.equals(r.getString(R.string.str_reaumur))) {
			num = Celcius.toReaumur(num);			
		} else if (to.equals(r.getString(R.string.str_kelvin))) {
			num = Celcius.toKelvin(num);			
		} else if (to.equals(r.getString(R.string.str_romer))) {
			num = Celcius.toRomer(num);			
		} else if (to.equals(r.getString(R.string.str_rankine))) {
			num = Celcius.toRankine(num);			
		} else if (to.equals(r.getString(R.string.str_newton))) {
			num = Celcius.toNewton(num);			
		}
		
		return num;
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_convert:
			handleConvertButtonClick();
			break;
		case R.id.input_type:
			mSelectedInputUnitMode = true;
			actionChoiceDialog();
			break;
		case R.id.output_type:
			mSelectedInputUnitMode = false;
			actionChoiceDialog();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Button convert = (Button) findViewById(R.id.btn_convert);
		convert.setOnClickListener(this);
		
		mInputSelector = (TextView) findViewById(R.id.input_type);
		mInputSelector.setOnClickListener(this);
		
		mOutputSelector = (TextView) findViewById(R.id.output_type);
		mOutputSelector.setOnClickListener(this);
		
		mTemperatureUnits = getResources().getStringArray(R.array.temperatures);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
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
	
	
}
