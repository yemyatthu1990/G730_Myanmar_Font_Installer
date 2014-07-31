/*
 * Copyright © 2014 TAD
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package tad.g730.mmfi;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class main_activity extends Activity{
	
	private TextView txtDeviceModel,centreTipText;
	private View AboutApp,AboutTad,Acknoledgement;
	private Button func_btn;
	private TextView stateText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
			
		//about developer button
		AboutApp=findViewById(R.id.abt_app_btn);
		AboutApp.setOnClickListener(new abt_app(this));
		
		//about tad button
		AboutTad=findViewById(R.id.abt_tad_btn);
		AboutTad.setOnClickListener(new abt_tad(this));
		
		//Acknowledgment button
		Acknoledgement=findViewById(R.id.ackn);
		Acknoledgement.setOnClickListener(new ack(this));
		
		//device model text
		txtDeviceModel=(TextView)findViewById(R.id.device_model);
		txtDeviceModel.setText(Build.MODEL);
		txtDeviceModel.setVisibility(8);
		
		//processing info text
		centreTipText=(TextView)findViewById(R.id.centre_tip_txt);
		centreTipText.setVisibility(8);
		
		//state text
		stateText=(TextView)findViewById(R.id.state_text);
		stateText.setVisibility(8);
		
		//function button
		func_btn=(Button)findViewById(R.id.function_btn);
		func_btn.setVisibility(8);
		
		SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
		boolean is_installed=sp.getBoolean("is_installed", false);
		if(is_installed)//to check if installed
		{			
			//set function button
			func_btn.setText(R.string.uninstall);
			func_btn.setOnClickListener(new uninstall_exec(this));	
			
			//set state text
			stateText.setText(R.string.status_has_myanmar);
			
		}else{
			
			//set function button
			func_btn.setText(R.string.install);
			func_btn.setOnClickListener(new install_exec(this));
			
			//set state text
			stateText.setText(R.string.status_has_no_myanmar);			
		}
		new Handler().postDelayed(new Runnable() {
            
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
            	stateText.setVisibility(0);
        		YoYo.with(Techniques.FadeIn)
                .duration(700)
                .playOn(stateText);	
        		txtDeviceModel.setVisibility(0);
        		YoYo.with(Techniques.SlideInLeft)
                .duration(700)
                .playOn(txtDeviceModel);
        		func_btn.setVisibility(0);				  
        		  YoYo.with(Techniques.SlideInRight)
                .duration(700)
                .playOn(func_btn);
            }
        }, 500); 
		
		  
		 
}
}
