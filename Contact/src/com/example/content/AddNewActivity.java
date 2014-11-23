package com.example.content;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AddNewActivity extends Activity
{
	ImageButton btn_img;
	AlertDialog imageChooseDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		
		btn_img = (ImageButton)this.findViewById(R.id.btn_img);
		btn_img.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				initImageChooseDialog();
				imageChooseDialog.show();
			}
		});
		
	}
	
	private void initImageChooseDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("ÇëÑ¡ÔñÍ¼Ïñ");
		imageChooseDialog = builder.create();
	}

	
}
