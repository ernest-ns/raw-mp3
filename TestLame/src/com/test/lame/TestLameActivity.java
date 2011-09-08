package com.test.lame;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class TestLameActivity extends Activity {
    /** Called when the activity is first created. */
	
	public static native void kill();
    public static native void convertMP3(String inFile,String outFile,String progFile,int sampleRate,int bitRate);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String fileDirectory=Environment.getExternalStorageDirectory().getAbsolutePath()+"/AudioRecorder";
        File sourceFile=new File(fileDirectory, "1.wav");
        File targetFile=new File(fileDirectory , "1.mp3");
        
       
        try {
			targetFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Log.d("Kill","Before Kill" );
        kill();
        Log.d("Kill","after Kill" );       
        
        Log.d("Convert","before convert" );
        convertMP3(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath(), fileDirectory+"prog.txt", 44100, 192);
        Log.d("Convert","after convert" );
    }
    
    
    static {    	
    	System.loadLibrary("Lame");
        Log.d("static block","libraries loaded");
   }
}