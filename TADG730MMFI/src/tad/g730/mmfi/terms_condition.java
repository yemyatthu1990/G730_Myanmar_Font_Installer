package tad.g730.mmfi;
//terms and condition activity
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class terms_condition extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.license_agreement);
		
		Button agree,disagree;
		
		//agree button
		agree=(Button)findViewById(R.id.license_agreement_agree);
		agree.setOnClickListener(this);
		
		//disagree button
		disagree=(Button)findViewById(R.id.license_agreement_disagree);
		disagree.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		Editor edit = sp.edit();
		switch (v.getId()) {
		//when agree button clicked
		case R.id.license_agreement_agree:
			/*if agree button is clicked. value of is_agreed in shared preferences become true.
			 * so that next time the app starts, terms and condition activity won't be initiated*/
			edit.putBoolean("is_agreed", true);
			edit.commit();
			Intent i=new Intent(this,main_activity.class);
			startActivity(i);
			overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
			finish();
			break;
		//when disagree button is clicked
		case R.id.license_agreement_disagree:
			edit.putBoolean("is_agreed", false);
			edit.commit();
			System.exit(-1);
			break;
		default:
			break;
		}
		
		
	}

}
