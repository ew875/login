package sg.edu.np.mad.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import javax.microedition.khronos.opengles.GL;

public class MainActivity2 extends AppCompatActivity {
    String title = "Main Activity 2";

//    public String GLOBAL_PREF = "MyPrefs";
//    public String PREF_USERNAME = "UserName";
//    public String PREF_PASSWORD = "Password";
//    SharedPreferences sharedPreferences;

    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(title, "Starting creation of acct");

        EditText etusername = findViewById(R.id.editTextText3);
        EditText etpassword = findViewById(R.id.editTextText4);

        Button createButton = findViewById(R.id.button2);
        Button cancelButton = findViewById(R.id.button3);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPreferences = getSharedPreferences(GLOBAL_PREF, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString(PREF_USERNAME,etusername.getText().toString());
//                editor.putString(PREF_PASSWORD,etpassword.getText().toString());
//                editor.commit();
//                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
//                startActivity(intent);

                UserData dbUserData = myDBHandler.findUser(etusername.getText().toString());
                if(dbUserData == null){
                    UserData userdata = new UserData(etusername.getText().toString(), etpassword.getText().toString());
                    myDBHandler.addUser(userdata);
                    Log.i(title, "Added User" + userdata);
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity2.this, "USER ALREADY EXIST", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}