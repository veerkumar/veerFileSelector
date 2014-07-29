package veer.example.fileselector;

import veer.example.fileselector.R;
import veer.example.fileselector.R.drawable;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomList extends ArrayAdapter<String>{
	 static final String TAG = "Filetag";
	  private static final boolean DEBUG = false; // Set to true to enable logging

	    public static final int THUMBNAIL_SIZE = 128;

	    public static final String HIDDEN_PREFIX = ".";
	    public Bitmap[] imag;
    private final Activity context;
    private final String[] web;
    private final String[] path1;
    private final String[] filesizes;
    private final Integer imageId;
    public CustomList(Activity context, String[] web, Integer imageId, String[] fileitems, String[] filesizes) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;
        this.path1=fileitems;
        this.filesizes=filesizes;
      //  Log.i("in conte","con");
      //  bitmap1();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                
//            }
//        }).start();
        
    }
   public void bitmap1(){
	   imag=new Bitmap[path1.length];
	   App.imag=new Bitmap[path1.length];
	   for(int i=0;i<this.path1.length;i++){
//		  imag[i]= ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path1[i]),80, 80);
		  //Log.i("image bitmap","00"+imag[i]);  
		  App.imag[i]=null;
		   
	   }
	   
   }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
    	//Log.i("costom list","costomlist");
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView size = (TextView) rowView.findViewById(R.id.size);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        imageView.setImageResource(imageId);
 	   size.setText(filesizes[position]);
        
     //  Log.i("web"+position,"1"+web[position]);
       if(path1[position].contains(".pdf")){
    	   imageView.setImageResource(R.drawable.pdf);
    	   size.setText(filesizes[position]);
    	 //  imageView.setImageURI(Uri.parse(path1[position]));
       }
        if(path1[position].contains(".mp3")){
    	   imageView.setImageResource(R.drawable.mp3);
    	   size.setText(filesizes[position]);
    	   
       }
        if(path1[position].contains(".bmp")||path1[position].contains(".BMP")||path1[position].contains(".gif")||path1[position].contains(".GIF")||path1[position].contains(".jpeg")||path1[position].contains(".jpg")||path1[position].contains(".JPEG")||path1[position].contains(".png")||path1[position].contains(".PNG")){
    	// imageView.
  //  Bitmap ThumbImage = 
    	   
    	 //  Uri uri=Uri.parse(path1[position]);
    	//   Bitmap ThumbImage =  getThumbnail(context,  uri);
    	//   Log.i("setting","image");
    	  // App.imag[position]
    	 //  Log.i("imageb","00"+App.imag[position]);
    	   size.setText(filesizes[position]);
    	   imageView.setImageResource(R.drawable.icon_image);
   	   if(!(""+App.imag[position]).contains("null")){
        
             imageView.setImageBitmap(App.imag[position]);
             size.setText(filesizes[position]);
             
         }
     
       	   
       }
      
      if(path1[position].contains(".mp4")||path1[position].contains(".avi")||path1[position].contains(".3gp")||path1[position].contains(".flv")||path1[position].contains(".MP4")||path1[position].contains(".WMV")||path1[position].contains(".wmv")||path1[position].contains(".MPEG")||path1[position].contains(".mpeg")||path1[position].contains(".mov")){
    	   
    	  imageView.setImageResource(R.drawable.icon_video);
      	   if(!(""+App.imag[position]).contains("null")){
           
                imageView.setImageBitmap(App.imag[position]);
                size.setText(filesizes[position]);
                
            }
       }
       
        if(path1[position].contains(".jar")||path1[position].contains(".zip")||path1[position].contains(".rar")){
       imageView.setImageResource(R.drawable.icon_zip);
       size.setText(filesizes[position]);
       }
       
        if(path1[position].contains(".doc")||path1[position].contains(".docx")||path1[position].contains(".DOC")||path1[position].contains(".DOCX")){
        
        	imageView.setImageResource(R.drawable.icon_docx);
        	size.setText(filesizes[position]);
        }
        
        if(path1[position].contains(".xml")){
        	imageView.setImageResource(R.drawable.xml);
        	size.setText(filesizes[position]);
        }
        if(path1[position].contains(".txt")){
        	imageView.setImageResource(R.drawable.txt);
        	size.setText(filesizes[position]);
        }
        if(path1[position].contains(".cpp")||path1[position].contains(".c")){
        	imageView.setImageResource(R.drawable.cpp);
        	size.setText(filesizes[position]);
        	
        }
        if(path1[position].contains(".ppt")||path1[position].contains(".PPT")){
        	imageView.setImageResource(R.drawable.ppt);
        	size.setText(filesizes[position]);
        }
        if(path1[position].contains(".apk")){
        	imageView.setImageResource(R.drawable.apk);
        	size.setText(filesizes[position]);
        }	
        if(path1[position].contains(".tar")){
        	imageView.setImageResource(R.drawable.tar);
        	size.setText(filesizes[position]);
        }	
        //imageView.setim
       
       
       
        return rowView;
    }
  

}

