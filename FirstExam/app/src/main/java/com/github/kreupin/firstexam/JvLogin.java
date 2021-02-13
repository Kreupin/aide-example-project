package com.github.kreupin.firstexam;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.text.*;
import android.text.style.*;
import android.content.*;
import android.text.method.*;

public class JvLogin extends Activity
{
	DbAccount sql;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_login);
		
		sql = new DbAccount(this);
		
		final EditText eUser = findViewById(R.id.edit_user);
		final EditText ePass = findViewById(R.id.edit_pass);
		Button bLogin = findViewById(R.id.button_login);
		TextView tRegis = findViewById(R.id.text_regis);
		
		bLogin.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String username = eUser.getText().toString();
				String password = ePass.getText().toString();
				
				if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
				{
					boolean chkLogin = sql.CheckLogin(username,password);
					if(chkLogin == true)
					{
						Intent intent = new Intent(JvLogin.this,MainActivity.class);
						startActivity(intent);
						JvLogin.this.finish();
					}
					else
					{
						Toast.makeText(JvLogin.this,"WRONG!!",Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		String regis = ">> REGISTER <<";
		SpannableStringBuilder span = new SpannableStringBuilder(regis);
		ClickableSpan click = new ClickableSpan()
		{
			@Override
			public void onClick(View view)
			{
				Intent register = new Intent(JvLogin.this,JvRegister.class);
				startActivity(register);
				JvLogin.this.finish();
			}
		};
		span.setSpan(click,regis.indexOf("REGISTER"),regis.indexOf("REGISTER") + String.valueOf("REGISTER").length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		tRegis.setText(span);
		tRegis.setMovementMethod(LinkMovementMethod.getInstance());
	} 
}
