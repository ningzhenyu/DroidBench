package edu.wayne.cs;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

/**
 * @testcase_name BytecodeTamper1
 * @author Wayne State University,
 * @author_mail zhenyu.ning@wayne.edu
 * 
 * @description Use self-modified code to create a sink.
 * @dataflow onCreate: source -> maliciousMethod() -> sink
 * @number_of_leaks 1
 * @challenges Sophisticated opponent could use self-modified code to achieve
 *             malicious behavior.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView) findViewById(R.id.tv1)).setText(NativeInterface.jniTest());
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        benignMethod(tm.getDeviceId());
    }

    public void benignMethod(String param) {
        Log.e("DroidBench", "FAKE");
    }

    public void maliciousMethod(String param) {
        Log.e("DroidBench", param);
    }
}
