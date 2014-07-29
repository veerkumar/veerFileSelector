package veer.example.fileselector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

public class ImageLoader extends AsyncTask<String, Void, Void>{
	
	 public Bitmap[] imag;
	 public int len;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
	}
	 @Override
	protected Void doInBackground(String... path1) {
		
		
		imag=new Bitmap[path1.length];
		len=path1.length;
		App.imag=new Bitmap[len];
		//Log.i("length!!!!","!!!!"+len);
		
		   for(int i=0;i<path1.length;i++){
			   
			   if(path1[i].contains(".bmp")||path1[i].contains(".BMP")||path1[i].contains(".gif")||path1[i].contains(".GIF")||path1[i].contains(".jpeg")||path1[i].contains(".jpg")||path1[i].contains(".JPEG")||path1[i].contains(".png")||path1[i].contains(".PNG")){
			 
				  // Log.i("imagepath","1"+path1[i]);
				   imag[i]= ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path1[i]),80, 80);
			//	Log.i("image bitmap","00"+imag[i]);   
				App.imag[i]=this.imag[i];
				
				
			   }
			 if (path1[i].contains(".mp4")||path1[i].contains(".avi")||path1[i].contains(".3gp")||path1[i].contains(".flv")||path1[i].contains(".MP4")||path1[i].contains(".WMV")||path1[i].contains(".wmv")||path1[i].contains(".MPEG")||path1[i].contains(".mpeg")||path1[i].contains(".mov")){
		    	   Bitmap bMap = ThumbnailUtils.createVideoThumbnail(path1[i], MediaStore.Video.Thumbnails.MICRO_KIND);
		    	   App.imag[i]=bMap;
		        }
		   
	 }
		   
		return null;
		// TODO Auto-generated method stub
		
	}

	void onPostExecute(){
		
		for(int i=0;i<len;i++){
			//Log.i("post","postexecute");
			App.imag[i]=this.imag[i];
		}
		//App.adapat.notifyDataSetChanged();
		//App.adap.notifyDataSetChanged();
		
		
		
	}
	
}
