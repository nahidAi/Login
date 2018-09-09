package test.login.com.login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "mydatabase";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "user";

    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PASS = "pass";
    public static final String COL_FAVORITE = "fav";

    public static  final String QUERY = "CREATE TABLE IF NOT EXISTS " + TBL_NAME +"("+
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT ," +
            COL_FAVORITE + " INTEGER ," +
            COL_PASS + " TEXT);";

    Context context;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(QUERY);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long addInfo(String name,String pass,int fav){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_PASS,pass);
        contentValues.put(COL_FAVORITE,fav);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       long result = sqLiteDatabase.insert(TBL_NAME,null,contentValues);
       if (result>0){
           return result;
       }else {
           return result;
       }

    }
    public Cursor getInfo(){
        String query = "SELECT * FROM " + TBL_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,null);

    }

    public void deleteRaw(int itemId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TBL_NAME, COL_ID + " = ?",new String[] {String.valueOf(itemId)});






    }
    public  void updateRow(String name,String pass,int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_PASS,pass);
        sqLiteDatabase.update(TBL_NAME,contentValues,COL_ID+" = ?",new String[]{String.valueOf(id)});

    }

}
