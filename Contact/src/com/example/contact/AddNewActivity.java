package com.example.contact;

import com.example.contact.db.DBHelper;
import com.example.contact.entity.User;
import com.example.content.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class AddNewActivity extends Activity
{
	ImageButton btn_img;
	AlertDialog imageChooseDialog;
	Gallery gallery;
	ImageSwitcher is;
	int imageposition;  //传递选择的图的下标
	
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
	
	Button btn_save;
	Button btn_return;
	
	//imageposition初始值为0，考虑用户不选择头像时默认的头像应该是下标为0的图片，所以给数组加一个ic_launcher在0下标
	private int[] images = {R.drawable.ic_launcher,R.drawable.image1,R.drawable.image2,
						   R.drawable.image3,R.drawable.image4,
						   R.drawable.image5,R.drawable.image6,
						   R.drawable.image7,R.drawable.image8,
						   R.drawable.image9,R.drawable.image10,
						   R.drawable.image11,R.drawable.image12,
						   R.drawable.image13,R.drawable.image14,
						   R.drawable.image15,R.drawable.image16,
						   R.drawable.image17,R.drawable.image18,
						   R.drawable.image19,R.drawable.image20,
						   R.drawable.image21,R.drawable.image22,
						   R.drawable.image23,R.drawable.image24,
						   R.drawable.image25,R.drawable.image26,
						   R.drawable.image27,R.drawable.image28,
						   R.drawable.image29,R.drawable.image30
						   };
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		initWidget();
		
		btn_save.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				String name = et_name.getText().toString();
				if(name.equals(""))
				{
					Toast.makeText(AddNewActivity.this, "姓名不能为空", Toast.LENGTH_LONG).show();
					return;
				}
				String mobilePhone = et_mobilePhone.getText().toString();
				String familyPhone = et_familyPhone.getText().toString();
				String officePhone = et_officePhone.getText().toString();
				String position = et_position.getText().toString();
				String company = et_company.getText().toString();
				String address = et_address.getText().toString();
				String zipcode = et_zipcode.getText().toString();
				String email = et_email.getText().toString();
				String otherContact = et_otherContact.getText().toString();
				String remark = et_remark.getText().toString();
				
				int imageId = images[imageposition];
				
				User user = new User();
				user.address = address;
				user.company = company;
				user.email = email;
				user.familyPhone = familyPhone;
				user.officePhone = officePhone;
				user.mobliePhone = mobilePhone;
				user.imageId = imageId;
				user.zipcode = zipcode;
				user.otherContact =otherContact;
				user.name = name;
				user.remark = remark;
				user.position = position;
				
				//save user to database
				DBHelper.getInstance(AddNewActivity.this).save(user);
			}
		});
		btn_return.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
			}
		});
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
		
		btn_save = (Button) this.findViewById(R.id.btn_save);
		btn_return = (Button) this.findViewById(R.id.btn_return);
	}
	
	private void initImageChooseDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择图像");
		//添加按钮
		
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				btn_img.setImageResource(images[imageposition]);
			}
		});
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.imageswitch, null);
		gallery = (Gallery) view.findViewById(R.id.img_gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setSelection(images.length/2);   //设定打开时的默认位置在中间
		
		is = (ImageSwitcher) view.findViewById(R.id.imag_switcher);
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id)
			{
				imageposition = position;
				is.setImageResource(images[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				
			}
			
		});
		is.setFactory(new MyViewFactory(this));
		builder.setView(view);
		imageChooseDialog = builder.create();
	}

	
	class ImageAdapter extends BaseAdapter{
		
		private Context context;
		
		public ImageAdapter(Context context)
		{
			this.context = context;
		}
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return images.length;
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			ImageView iv = new ImageView(context);
			iv.setImageResource(images[position]);
			iv.setLayoutParams(new Gallery.LayoutParams(80,80));
			iv.setPadding(15, 10, 15, 10);
			
			return iv;
		}
		
	}
	
	class MyViewFactory implements ViewFactory{

		private Context context;
		
		public MyViewFactory(Context context)
		{
			this.context = context;
		}
		@Override
		public View makeView()
		{
			ImageView iv = new ImageView(context);
			iv.setLayoutParams(new ImageSwitcher.LayoutParams(75,75));
			return iv;
		}
		
	}
}
