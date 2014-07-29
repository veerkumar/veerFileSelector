package veer.example.fileselector;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import veer.example.fileselector.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.commonsware.cwac.merge.MergeAdapter;

public class FileSelectionActivity extends Activity {

    private static final String TAG = "FileSelection";
    private static final String FILES_TO_UPLOAD = "upload";
    private File mainPath = new File(Environment.getExternalStorageDirectory()+"");
    private ArrayList<File> resultFileList;
    public ArrayList<String> Filesize=new ArrayList<String>();

    private ListView directoryView;
    private ArrayList<File> directoryList = new ArrayList<File>();
    private ArrayList<String> directoryNames = new ArrayList<String>();
    //private ListView fileView;
	private ArrayList<File> fileList = new ArrayList<File>();
	private ArrayList<String> fileNames = new ArrayList<String>();
    Button ok, all;
    TextView path;
    Boolean Switch = false;
    ArrayList<String> fileListPath= new ArrayList<String>();
    String[] fileitempath;
    CustomList adapter2;
    CustomListSingleOnly adapter1 ;
    MergeAdapter adap;

    Integer[] imageId = {
            R.drawable.document,
            R.drawable.document_gray,
            R.drawable.folder,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selection);
        //getActionBar().setDisplayHomeAsUpEnabled(true);



        directoryView = (ListView)findViewById(R.id.directorySelectionList);
        ok = (Button)findViewById(R.id.ok);
        all = (Button)findViewById(R.id.all);
        path = (TextView)findViewById(R.id.folderpath);

        loadLists();




        directoryView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                File lastPath = mainPath;
                try {
                    if (position < directoryList.size()) {
                        mainPath = directoryList.get(position);
                        loadLists();
                    }
                }catch (Throwable e){
                    mainPath = lastPath;
                    loadLists();
                }

			}
		});

       ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ok();
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!Switch){
                    for (int i = directoryList.size(); i < directoryView.getCount(); i++){
                        directoryView.setItemChecked(i, true);
                    }
                    all.setText(getString(R.string.none));
                    Switch = true;
                }else if(Switch){
                    for (int i = directoryList.size(); i < directoryView.getCount(); i++) {
                        directoryView.setItemChecked(i, false);
                    }
                    all.setText(getString(R.string.all));
                    Switch = false;
                }
                }

        });
    }

    public void onBackPressed() {
        try {
            if(mainPath.equals(Environment.getExternalStorageDirectory().getParentFile().getParentFile())){
                finish();
            }else{
                File parent = mainPath.getParentFile();
                mainPath = parent;
                loadLists();
            }

        }catch (Throwable e){

        }
    }

	public void ok(){
    	Log.d(TAG, "Upload clicked, finishing activity");


        resultFileList = new ArrayList<File>();

        for(int i = 0 ; i < directoryView.getCount(); i++){
        	if(directoryView.isItemChecked(i)){
        		resultFileList.add(fileList.get(i-directoryList.size()));
        	}
        }
        if(resultFileList.isEmpty()){
        	Log.d(TAG, "Nada seleccionado");
        	finish();
        }
        Log.d(TAG, "Files: "+resultFileList.toString());
        Toast.makeText(this,resultFileList.toString(),Toast.LENGTH_SHORT).show();
        Intent result = this.getIntent();
        result.putExtra(FILES_TO_UPLOAD, resultFileList);
      // Log.i("new",""+ result.getExtras().get(FILES_TO_UPLOAD));
        setResult(Activity.RESULT_OK, result);
        
    	finish();
    }

	private void loadLists(){
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		};
		FileFilter directoryFilter = new FileFilter(){
			public boolean accept(File file){
				return file.isDirectory();
			}
		};

		//if(mainPath.exists() && mainPath.length()>0){
			//Lista de directorios
			File[] tempDirectoryList = mainPath.listFiles(directoryFilter);
			directoryList = new ArrayList<File>();
			directoryNames = new ArrayList<String>();
			for(File file: tempDirectoryList){
				directoryList.add(file);
				directoryNames.add(file.getName());
			}
			ArrayAdapter<String> directoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, directoryNames);


			//Lista de ficheros
			File[] tempFileList = mainPath.listFiles(fileFilter);
			fileList = new ArrayList<File>();
    		fileNames = new ArrayList<String>();
    		fileListPath = new ArrayList<String>();
    		Filesize=new ArrayList<String>();
    		int i=0;
    		for(File file : tempFileList){
    			
    			fileList.add(file);
    			fileListPath.add(fileList.get(i).toString());
    			//Log.i("file address","1"+fileList.get(i));
    			fileNames.add(file.getName());
    			
    			double bytes = file.length();
    			double kilobytes = (bytes / 1024);
    			double megabytes = (kilobytes / 1024);
    			double gigabytes = (megabytes / 1024);
    			String str;
    			if(gigabytes>=1){
    				str=Math.round(gigabytes * 100.0) / 100.0+"GB";
    				
    			}
    			else if(megabytes>1){
    				str=Math.round(megabytes * 100.0) / 100.0+"MB";
    			}
    			else if(kilobytes>1)
    			{
    				str=Math.round(kilobytes* 100.0) / 100.0+"KB";
    			}
    			else{
    				str=bytes+"B";
    			}
    			
    			Filesize.add(str);
    			i++;
    		}

    		 fileitempath=new String[fileNames.size()];
    		new ImageLoader().execute(fileListPath.toArray(fileitempath));

            path.setText(mainPath.toString());
            iconload();
            setTitle(mainPath.getName());
		//}
	}

    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_file_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }**/

    public void iconload(){
        String[] foldernames = new String[directoryNames.size()];
        foldernames = directoryNames.toArray(foldernames);

        String[] filenames = new String[fileNames.size()];
       // filenames = fileNames.toArray(filenames);
        String[] filesizes=new String[fileNames.size()];
      //  fileitems=fileList.toArray(fileitems);
//Log.i("in adapter","1"+fileList.toString());

        adapter1 = new CustomListSingleOnly(FileSelectionActivity.this, directoryNames.toArray(foldernames), imageId[2]);
         adapter2 = new CustomList(FileSelectionActivity.this, fileNames.toArray(filenames), imageId[0],fileListPath.toArray(fileitempath),Filesize.toArray(filesizes));

       
       adap = new MergeAdapter();
       
        adap.addAdapter(adapter1);
        adap.addAdapter(adapter2);
       // App.adapat=adapter2;
       // App.adap=adap;

 
        directoryView.setAdapter(adap);
       //adapter2.notifyDataSetChanged();
		Timer t = new Timer();
		//Set the schedule function and rate
//		t.scheduleAtFixedRate(new TimerTask() {
//
//		    @Override
//		    public void run() {
//		       
//		    	runOnUiThread(new Runnable() {
//
//		    	    @Override
//		    	    public void run() {
//		    	    	Log.i("timer","timer");
//		    	    	
//				    	adapter2.notifyDataSetChanged();
//				    //	adap.notifyDataSetChanged();
//		    	    }
//
//		    	});
//		    	
//		    }},0,1000);
//      
		CountDownTimer waitTimer;
	     waitTimer = new CountDownTimer(30000, 800) {

	       public void onTick(long millisUntilFinished) {
	          //called every 300 milliseconds, which could be used to
	    	 //  Log.i("timer","timer");
	          //send messages or some other action
	    	   adapter2.notifyDataSetChanged();
	       }

	       public void onFinish() {
	          //After 60000 milliseconds (60 sec) finish current 
	          //if you would like to execute something when time finishes          
	       }
	     }.start();
    }

}
