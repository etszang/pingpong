package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DB extends SQLiteOpenHelper {

	public DB(Context context) {
		super(context, "mydb", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table table1 ("
				+"id integer primary key autoincrement,"
				+"name text"
				+");"
				);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public String loadLevel() {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query("table1", null, null, null, null, null, null, null);
		if(c.moveToFirst()) {
			do {
				String name = c.getString(c.getColumnIndex("name"));
				Log.d("ferus.tigris.db", name);
				return name;
			} while(c.moveToNext());
		}
		return "";
	}

	public void saveGame() {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", "aaaaaaa");
		db.insert("table1", null, values);
	}

}
