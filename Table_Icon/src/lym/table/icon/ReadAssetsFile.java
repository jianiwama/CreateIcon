package lym.table.icon;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ReadAssetsFile {
	private Context context;
	ReadAssetsFile(Context context){
		this.context = context;
	}
	public Drawable getAssertDrawable(Context context,String fileName){
        try {
            InputStream inStream=context.getAssets().open(fileName);
            return new BitmapDrawable(BitmapFactory.decodeStream(inStream));
        } catch (IOException e) {
            Log.e("getAssertDrawable", "Assert÷–"+fileName+"≤ª¥Ê‘⁄");
        }
        return null;
    }
}
