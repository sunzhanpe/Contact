package com.example.contact;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.content.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity
{
	GridView gv_bottom_menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
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
		ArrayList data = new ArrayList();
		HashMap map = new HashMap();
		map.put("itemImage", R.drawable.menu_new_user);
		map.put("itemText", "增加");
		data.add(map);
		
		map = new HashMap();
		map.put("itemImage", R.drawable.menu_search);
		map.put("itemText", "查找");
		data.add(map);
		
		map = new HashMap();
		map.put("itemImage", R.drawable.menu_delete);
		map.put("itemText", "删除");
		data.add(map);
		
		map = new HashMap();
		map.put("itemImage", R.drawable.controlbar_showtype_list);
		map.put("itemText", "菜单");
		data.add(map);
		
		map = new HashMap();
		map.put("itemImage", R.drawable.menu_exit);
		map.put("itemText", "退出");
		data.add(map);
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item_menu, new String[]{"itemImage","itemText"}, new int[]{R.id.item_image,R.id.item_text});
		
		gv_bottom_menu.setAdapter(adapter);
		
	}
}
