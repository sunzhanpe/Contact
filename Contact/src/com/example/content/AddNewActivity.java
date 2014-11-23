package com.example.content;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class AddNewActivity extends Activity
{
	ImageButton btn_img;
	AlertDialog imageChooseDialog;
	
	private int[] images = {R.drawable.image1,R.drawable.image2,
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
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.imageswitch,null);
		Gallery gallery = (Gallery) view.findViewById(R.id.img_gallery);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setSelection(images.length/2);
		ImageSwitcher is = (ImageSwitcher) view.findViewById(R.id.imag_switcher);
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
}
