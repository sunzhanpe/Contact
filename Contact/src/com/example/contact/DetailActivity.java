package com.example.contact;


import java.util.HashMap;

import com.example.content.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DetailActivity extends Activity
{
	EditText et_name;
	EditText et_mobilePhone;
	EditText et_familyPhone;
	EditText et_officePhone;
	EditText et_position;
	EditText et_company;
	EditText et_address;
	EditText et_zipcode;
	EditText et_email;
	EditText et_otherContact;
	EditText et_remark;
	ImageButton btn_img;
	
	Button btn_modify;
	Button btn_delete;
	Button btn_return;
	boolean flag = false;
	HashMap map;
	
	

	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		initWidget();
		Intent intent = getIntent();
		map = (HashMap)intent.getSerializableExtra("usermap");
		displayData();
		setEditDisbale();
		
		btn_modify.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v)
			{
				
				if(flag)
				{
					flag = false;
					btn_modify.setText("修改");
					setEditDisbale();
				}
				else{
					flag = true;
					btn_modify.setText("保存");
					setEditAble();
				}
				
			}
		});
	}
	
	public void initWidget()
	{
		et_name = (EditText) this.findViewById(R.id.et_name);
		et_mobilePhone = (EditText) this.findViewById(R.id.et_mobilephone);
		et_familyPhone = (EditText) this.findViewById(R.id.et_familyphone);
		et_officePhone = (EditText) this.findViewById(R.id.et_officephone);
		et_position = (EditText) this.findViewById(R.id.et_position);
		et_company = (EditText) this.findViewById(R.id.et_company);
		et_address = (EditText) this.findViewById(R.id.et_address);
		et_zipcode = (EditText) this.findViewById(R.id.et_zipcode);
		et_email = (EditText) this.findViewById(R.id.et_email);
		et_otherContact = (EditText) this.findViewById(R.id.et_other);
		et_remark = (EditText) this.findViewById(R.id.et_remark);
		
		btn_img = (ImageButton)this.findViewById(R.id.btn_img);
		btn_modify = (Button) this.findViewById(R.id.btn_modify);
		btn_delete = (Button) this.findViewById(R.id.btn_delete);
		btn_return = (Button) this.findViewById(R.id.btn_return);
	}
	
	private void displayData()
	{
		et_name.setText(String.valueOf(map.get("name")));
		et_mobilePhone.setText(String.valueOf(map.get("mobilePhone")));
		et_familyPhone.setText(String.valueOf(map.get("familyPhone")));
		et_officePhone.setText(String.valueOf(map.get("officePhone")));
		et_position.setText(String.valueOf(map.get("position")));
		et_company.setText(String.valueOf(map.get("company")));
		et_address.setText(String.valueOf(map.get("address")));
		et_zipcode.setText(String.valueOf(map.get("zipcode")));
		et_email.setText(String.valueOf(map.get("email")));
		et_otherContact.setText(String.valueOf(map.get("otherContact")));
		et_remark.setText(String.valueOf(map.get("remark")));
		btn_img.setImageResource(Integer.parseInt(String.valueOf(map.get("imageid"))));
		
		
	}
	
	private void setEditDisbale()
	{
		et_name.setEnabled(false);
		et_mobilePhone.setEnabled(false);
		et_familyPhone.setEnabled(false);
		et_officePhone.setEnabled(false);
		et_position.setEnabled(false);
		et_company.setEnabled(false);
		et_address.setEnabled(false);
		et_email.setEnabled(false);
		et_zipcode.setEnabled(false);
		et_otherContact.setEnabled(false);
		et_remark.setEnabled(false);
		btn_img.setEnabled(false);
	}
	
	private void setEditAble()
	{
		et_name.setEnabled(true);
		et_mobilePhone.setEnabled(true);
		et_familyPhone.setEnabled(true);
		et_officePhone.setEnabled(true);
		et_position.setEnabled(true);
		et_company.setEnabled(true);
		et_address.setEnabled(true);
		et_email.setEnabled(true);
		et_zipcode.setEnabled(true);
		et_otherContact.setEnabled(true);
		et_remark.setEnabled(true);
		btn_img.setEnabled(true);
	}
}
