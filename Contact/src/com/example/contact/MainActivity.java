package com.example.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.contact.db.DBHelper;
import com.example.content.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MainActivity extends Activity
{
	GridView gv_bottom_menu;
	ListView lv_userList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		loadUserList();
	}
	
	private void loadUserList()
	{
		lv_userList = (ListView) this.findViewById(R.id.lv_userlist);
		ArrayList<Map<String, Object>> data = DBHelper.getInstance(this).getUserList();
		SimpleAdapter adapter = new SimpleAdapter(this, 
									data, R.layout.list_item, 
									new String[]{"imageid", "name", "mobilePhone" }, 
									new int[]{R.id.user_image,R.id.tv_showname,R.id.tv_showmobilephone});
		lv_userList.setAdapter(adapter);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_MENU)
		{
			if(gv_bottom_menu == null)
			{
				loadBottomMenu();
			}
			if(gv_bottom_menu.getVisibility() == View.GONE)
			{
				gv_bottom_menu.setVisibility(View.VISIBLE);
			}
			else {
				gv_bottom_menu.setVisibility(View.GONE);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void loadBottomMenu()
	{
		gv_bottom_menu = (GridView) this.findViewById(R.id.gv_bottom_menu);
		gv_bottom_menu.setBackgroundResource(R.drawable.channelgallery_bg);
		gv_bottom_menu.setNumColumns(5); //每行列数
		gv_bottom_menu.setGravity(Gravity.CENTER);
		gv_bottom_menu.setVerticalSpacing(10);
		gv_bottom_menu.setHorizontalSpacing(10);
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("itemImage", R.drawable.menu_new_user);
		map.put("itemText", "增加");
		data.add(map);
		
		map = new HashMap<String,Object>();
		map.put("itemImage", R.drawable.menu_search);
		map.put("itemText", "查找");
		data.add(map);
		
		map = new HashMap<String,Object>();
		map.put("itemImage", R.drawable.menu_delete);
		map.put("itemText", "删除");
		data.add(map);
		
		map = new HashMap<String,Object>();
		map.put("itemImage", R.drawable.controlbar_showtype_list);
		map.put("itemText", "菜单");
		data.add(map);
		
		map = new HashMap<String,Object>();
		map.put("itemImage", R.drawable.menu_exit);
		map.put("itemText", "退出");
		data.add(map);
		
		SimpleAdapter adapter = new SimpleAdapter(this, 
										data, R.layout.item_menu, 
										new String[]{"itemImage","itemText"}, 
										new int[]{R.id.item_image,R.id.item_text});
		
		gv_bottom_menu.setAdapter(adapter);
		
		gv_bottom_menu.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				switch (position)
				{
				case 0:{
					Intent intent = new Intent(MainActivity.this,AddNewActivity.class);
					//0代表的是请求跳转到添加界面
					startActivityForResult(intent, 0);
					break;
				}
				case 1:{
					break;
				}
				case 2:{
					break;
				}
				case 3:{
					break;
				}
				case 4:{
					break;
				}
					
				default:
					break;
				}
			}
			
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 0)
		{
			if(resultCode == 1)
			{
				//增加用户成功，进行刷新
				loadUserList();
			}
			else if(requestCode ==2 )
			{
				//失败，不刷新
			}
				
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
}
