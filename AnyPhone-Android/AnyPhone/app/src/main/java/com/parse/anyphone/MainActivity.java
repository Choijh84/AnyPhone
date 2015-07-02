package com.parse.anyphone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {
    ParseUser user = ParseUser.getCurrentUser();
    private TextView phoneNumberLabel, nameLabel;
    private EditText nameField, phoneNumberField;
    private Button saveSettingsButton;
    private Switch setting1, setting2, setting3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberField = (EditText) findViewById(R.id.phoneNumberField);
        phoneNumberField.setText(LoginActivity.phoneNumber);
        nameField = (EditText) findViewById(R.id.nameField);

        setting1 = (Switch) findViewById(R.id.setting1);
        setting2 = (Switch) findViewById(R.id.setting2);
        setting3 = (Switch) findViewById(R.id.setting3);

        saveSettingsButton = (Button) findViewById(R.id.saveSettingsButton);
        saveSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });

        if(!ParseUser.getCurrentUser().isNew())
            checkSettings();

    }
    private  void checkSettings(){
        setting1.setChecked(user.getBoolean("setting1"));
        setting2.setChecked(user.getBoolean("setting2"));
        setting2.setChecked(user.getBoolean("setting3"));
    }
    private void saveSettings() {
        if(nameField != null) {
            user.put("name",nameField.getText().toString());

            if (setting1.isChecked()) {
                user.put("setting1", true);
            }
            if (setting2.isChecked()) {
                user.put("setting2", true);
            }
            if (setting3.isChecked()) {
                user.put("setting3", true);
            }
            user.saveInBackground();
        }else{
            Toast.makeText(getApplicationContext(), "Please enter a username.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
