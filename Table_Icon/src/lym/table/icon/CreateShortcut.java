package lym.table.icon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.preference.PreferenceManager;

public class CreateShortcut {
	private static final String PREFERENCE_KEY_SHORTCUT_EXISTS = "IsShortCutExists";
	private Uri uri = Uri.parse("www.baidu.com");
	public Context context;
	private Drawable icon;
	public Bitmap bm;
	
	SharedPreferences sharedPreferences ;
	boolean exists ;
	public CreateShortcut(Context context){
		this.context = context;
	}
	public void createShortcut(){
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        exists = sharedPreferences.getBoolean(PREFERENCE_KEY_SHORTCUT_EXISTS, false);
        if (!exists) {
			 addShortcut();
	        }
	}
	public void addShortcut() {
		ReadAssetsFile readAssetsFile = new ReadAssetsFile(context);
		icon = readAssetsFile.getAssertDrawable(context, "icon.png");
		BitmapDrawable bd = (BitmapDrawable)icon;
		bm = bd.getBitmap();
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		// 不允许重建
		shortcut.putExtra("duplicate", false);
		// 设置名字
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,"百度");
		// 设置图标
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON,
				generatorContactCountIcon(bm));
		Intent intentLauncher = new Intent();
		intentLauncher.setData(uri);
		intentLauncher.setAction(Intent.ACTION_VIEW);
		intentLauncher.setClassName("com.android.browser",
				"com.android.browser.BrowserActivity");
		// 添加快捷方式的启动方法
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intentLauncher);
		// 发送广播
		context.sendBroadcast(shortcut);
	}
	
	public Bitmap generatorContactCountIcon(Bitmap icon){ 
        //初始化画布 
        int iconSize=(int)context.getResources().getDimension(android.R.dimen.app_icon_size); 
        Bitmap contactIcon=Bitmap.createBitmap(iconSize, iconSize, Config.ARGB_8888); 
        Canvas canvas=new Canvas(contactIcon); 
         
        //拷贝图片 
        Paint iconPaint=new Paint(); 
        iconPaint.setDither(true);//防抖动 
        iconPaint.setFilterBitmap(true);//用来对Bitmap进行滤波处理，这样，当你选择Drawable时，会有抗锯齿的效果 
        Rect src=new Rect(0, 0, icon.getWidth(), icon.getHeight()); 
        Rect dst=new Rect(0, 0, iconSize, iconSize); 
        canvas.drawBitmap(icon, src, dst, iconPaint); 
         
//        //在图片上创建一个覆盖的联系人个数 
//        int contacyCount=11; 
//        //启用抗锯齿和使用设备的文本字距 
//        Paint countPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DEV_KERN_TEXT_FLAG); 
//        countPaint.setColor(Color.RED); 
//        countPaint.setTextSize(20f); 
//        countPaint.setTypeface(Typeface.DEFAULT_BOLD); 
//        canvas.drawText(String.valueOf(contacyCount), iconSize-18, 25, countPaint); 
        return contactIcon; 
    }
//	public byte[] Bitmap2Bytes(Bitmap bm){  
//	    ByteArrayOutputStream baos = new ByteArrayOutputStream();    
//	    bm.compress(Bitmap.CompressFormat.PNG, 0, baos);    
//	    return baos.toByteArray();  
//	   }
}
