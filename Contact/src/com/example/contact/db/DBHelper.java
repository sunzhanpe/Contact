package com.example.contact.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.contact.entity.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
	public final static String DB_NAME="contact";
	public final static int VERSION = 1;
	private static DBHelper instance = null;
	
	private SQLiteDatabase db;
	
	public static DBHelper getInstance(Context context)
	{
		if(instance == null)
		{
			instance = new DBHelper(context);
		}
		return instance;
	}
	
	private void openDatabase()
	{
		if(db == null)
		{
			db = this.getWritableDatabase(); //得到数据库的实例
		}
	}
	
	private DBHelper(Context context)
	{
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) //只执行一次，在没有数据库时，建表
	{
		StringBuffer tableCreate = new StringBuffer();
		tableCreate.append("create table user( _id integer primary key autoincrement,")
		.append("name text,")
		.append("mobilePhone text,")
		.append("familyPhone text,")
		.append("officePhone text,")
		.append("position text,")
		.append("company text,")
		.append("address text,")
		.append("zipcode text,")
		.append("email text,")
		.append("otherContact text,")
		.append("remark text,")
		.append("imageid int )");
		
		db.execSQL(tableCreate.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		String sql = "drop table if exists user";
		db.execSQL(sql);
		onCreate(db);
	}
	
	public long save(User user)
	{
		openDatabase();
		ContentValues values = new ContentValues();
		values.put("name", user.name);
		values.put("mobilePhone", user.mobliePhone);
		values.put("familyPhone", user.familyPhone);
		values.put("officePhone", user.officePhone);
		values.put("position", user.position);
		values.put("company", user.company);
		values.put("address", user.address);
		values.put("zipcode", user.zipcode);
		values.put("email", user.email);
		values.put("otherContact", user.otherContact);
		values.put("remark", user.remark);
		values.put("imageid", user.imageId);
		
		return db.insert("user", null, values);
		
	}
	
	public ArrayList<Map<String, Object>> getUserList()
	{
		openDatabase();
		Cursor cursor = db.query("user", null, null, null, null, null, null);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while(cursor.moveToNext())
		{
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
			map.put("familyPhone", cursor.getString(cursor.getColumnIndex("familyPhone")));
			map.put("officePhone", cursor.getString(cursor.getColumnIndex("officePhone")));
			map.put("position", cursor.getString(cursor.getColumnIndex("position")));
			map.put("company", cursor.getString(cursor.getColumnIndex("company")));
			map.put("address", cursor.getString(cursor.getColumnIndex("address")));
			map.put("zipcode", cursor.getString(cursor.getColumnIndex("zipcode")));
			map.put("email", cursor.getString(cursor.getColumnIndex("email")));
			map.put("otherContact", cursor.getString(cursor.getColumnIndex("otherContact")));
			map.put("remark", cursor.getString(cursor.getColumnIndex("remark")));
			map.put("imageid", cursor.getInt(cursor.getColumnIndex("imageid")));
			map.put("name", cursor.getString(cursor.getColumnIndex("name")));
			map.put("mobilePhone", cursor.getString(cursor.getColumnIndex("mobilePhone")));
			list.add(map);
		}
		return list;
	}
}
