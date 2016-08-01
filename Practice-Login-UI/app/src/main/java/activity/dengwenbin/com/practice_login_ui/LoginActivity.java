package activity.dengwenbin.com.practice_login_ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends Activity {

    private EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.et_username);
        username.setFocusable(true);
        username.setFocusableInTouchMode(true);
        username.requestFocus();
        password = (EditText) findViewById(R.id.et_password);
    }
}
