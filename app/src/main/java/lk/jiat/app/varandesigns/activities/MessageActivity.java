package lk.jiat.app.varandesigns.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import lk.jiat.app.varandesigns.R;

public class MessageActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String name = getIntent().getExtras().getString("name");
        Log.i(TAG,name.toString());
    }
}