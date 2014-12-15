package me.egois.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {

	protected Calculator mEngine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		int[] buttons = { R.id.btnZero, R.id.btnOne, R.id.btnTwo,
				R.id.btnThree, R.id.btnFour, R.id.btnFive, R.id.btnSix,
				R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnMultiply,
				R.id.btnDivide, R.id.btnPlus, R.id.btnMinus, R.id.btnClear,
				R.id.btnPercent, R.id.btnDot, R.id.btnEqual, R.id.btnPlusMinus,
				R.id.btnSqrt };
		for (int i : buttons) {
			((Button) findViewById(i)).setOnClickListener(this);
		}

		TextView displays = (TextView) findViewById(R.id.strDisplay);

		mEngine = new Calculator(new MathOperation(), displays);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnZero:
		case R.id.btnOne:
		case R.id.btnTwo:
		case R.id.btnThree:
		case R.id.btnFour:
		case R.id.btnFive:
		case R.id.btnSix:
		case R.id.btnSeven:
		case R.id.btnEight:
		case R.id.btnNine:
		case R.id.btnDot:
			mEngine.operand(((Button) v).getText().toString());
			break;
		case R.id.btnPlusMinus:
			mEngine.polarize();
			break;
		case R.id.btnMultiply:
		case R.id.btnDivide:
		case R.id.btnPlus:
		case R.id.btnMinus:
			mEngine.math(((Button) v).getText().toString());
			break;
		case R.id.btnClear:
			mEngine.reset();
			break;
		case R.id.btnPercent:
			mEngine.percent();
			break;
		case R.id.btnSqrt:
			mEngine.sqrt();
			break;
		case R.id.btnEqual:
			mEngine.evaluate();
			break;

		default:
			break;
		}
	}
}
