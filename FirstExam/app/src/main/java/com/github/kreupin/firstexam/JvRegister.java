package com.github.kreupin.firstexam;
import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import android.text.*;

public class JvRegister extends Activity
{
	DbAccount sql;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_register);
		
		sql = new DbAccount(this);
		
		final EditText newUser = findViewById(R.id.edit_newUser);
		final EditText newPass = findViewById(R.id.edit_newPass);
		final EditText confirmPass = findViewById(R.id.edit_confirm);
		Button bRegis =findViewById(R.id.button_regis);
		
		bRegis.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String username = newUser.getText().toString();
				String password = newPass.getText().toString();
				String confirm = confirmPass.getText().toString();
				
				if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && confirm.equals(password))
				{
					boolean chkUser = sql.CheckUsername(username);
					if(chkUser == true)
					{
						boolean insert = sql.Insert(username,password);
						if(insert == true)
						{
							Toast.makeText(JvRegister.this,"SUCCESS!!",Toast.LENGTH_SHORT).show();
							newUser.setText("");
							newPass.setText("");
							confirmPass.setText("");
						}
					}
					else
					{
						newUser.setError("username is already");
						newUser.requestFocus();
						return;
					}
				}
				else
				{
					confirmPass.setError("password don't match");
					confirmPass.requestFocus();
					return;
				}
			}
		 });
	} 
}
