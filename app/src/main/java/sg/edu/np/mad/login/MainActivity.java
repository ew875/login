package sg.edu.np.mad.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String title = "Main Activity";
    TextView newUser = findViewById(R.id.editTextText);
//    public String GLOBAL_PREF = "MyPrefs";
//    public String PREF_USERNAME = "UserName";
//    public String PREF_PASSWORD = "Password";
//    SharedPreferences sharedPreferences;
    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(title, "Create Login Page");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(title, "Start Login Page");

        EditText etusername = findViewById(R.id.editTextText);
        EditText etpassword = findViewById(R.id.editTextText2);

        Button loginButton = findViewById(R.id.button);

        newUser.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return false;
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (isValidCredentials(etusername.getText().toString(),etpassword.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid UserName/Password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password){

//        sharedPreferences = getSharedPreferences(GLOBAL_PREF,MODE_PRIVATE);
//        String sharedUserName = sharedPreferences.getString(PREF_USERNAME, "");
//        String sharedPassword = sharedPreferences.getString(PREF_PASSWORD, "");
//
//        if (sharedUserName.equals(username) && sharedPassword.equals(password)){
//            return true;
//        }
//        return false;
        UserData dbUserData = myDBHandler.findUser(username);

        if(dbUserData.getUsername().equals(username) && dbUserData.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}