package com.tiromansev.permissionmanager.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tiromansev.permissionmanager.PermissionsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("permission_request", "create main activity");

        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionsManager.get().checkWriteExternalAccess(new PermissionsManager.PermissionCallback() {
                    @Override
                    public void permissionAccepted() {
                        Toast.makeText(MainActivity.this, getString(R.string.message_permission_accepted), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void permissionRejected() {
                        Toast.makeText(MainActivity.this, getString(R.string.message_permission_rejected), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
