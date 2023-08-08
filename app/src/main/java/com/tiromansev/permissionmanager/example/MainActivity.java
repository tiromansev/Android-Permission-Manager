package com.tiromansev.permissionmanager.example;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tiromansev.permissionmanager.PermissionsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(v -> PermissionsManager.get().checkNotificationsAccess(new PermissionsManager.PermissionCallback() {
            @Override
            public void permissionAccepted() {
                Toast.makeText(MainActivity.this, getString(R.string.message_permission_accepted), Toast.LENGTH_LONG).show();
            }

            @Override
            public void permissionRejected() {
                Toast.makeText(MainActivity.this, getString(R.string.message_permission_rejected), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
