package mac.spike;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class NativeLibrarySpikeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("inside oncreate", "before create engine");
//        Log.d("avcodec_version","" + avcodec_version());
        String fileDirectory=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Audio Recorder";
        File sourceFile=new File("1.wav",fileDirectory);
        File targetFile=new File("1.mp3",fileDirectory);
        convertMP3(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath(), fileDirectory+"prog.txt", 44100, 192);
        kill();
        Log.d("after kill","after kill" );
    }
    
    public static native void kill();
    public static native void convertMP3(String inFile,String outFile,String progFile,int sampleRate,int bitRate);
    
    
    static {    	
    	System.loadLibrary("Lame");
        Log.d("static block","libraries loaded");
   }
 
}