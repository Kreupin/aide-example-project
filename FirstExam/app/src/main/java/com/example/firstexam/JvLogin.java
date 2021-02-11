package com.example.firstexam;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.text.*;
import android.text.style.*;
import android.text.method.*;
import android.content.*;

public class JvLogin extends Activity
{
	DbAccount db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_login);
		
		db = new DbAccount(this);
		
		final EditText eUser = findViewById(R.id.edit_user);
		final EditText ePass = findViewById(R.id.edit_pass);
		Button bLogin = findViewById(R.id.button_login);
		TextView tRegis = findViewById(R.id.text_regis);
		
		bLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String user = eUser.getText().toString();
				String pass = ePass.getText().toString();
				
				if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass))
				{
					boolean chkLogin = db.CheckLogin(user,pass);
					if(chkLogin == true)
					{
						Intent i = new Intent(JvLogin.this,MainActivity.class);
						startActivity(i);
						JvLogin.this.finish();
					}
						
				}
			}
		});
		
		String regis =">> REGISTER <<";
		SpannableStringBuilder span = new SpannableStringBuilder(regis);
		ClickableSpan click = new ClickableSpan()
		{
			@Override
			public void onClick(View v)
			{
				Intent i = new Intent(JvLogin.this,JvRegister.class);
				startActivity(i);
				JvLogin.this.finish();
			}
		};
		span.setSpan(click,regis.indexOf("REGISTER"),regis.indexOf("REGISTER") + String.valueOf("REGISTER").length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tRegis.setText(span);
		tRegis.setMovementMethod(LinkMovementMethod.getInstance());
	} 
}
