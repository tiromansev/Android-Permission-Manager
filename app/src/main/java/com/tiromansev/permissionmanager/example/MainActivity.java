package com.tiromansev.permissionmanager.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tiromansev.permissionmanager.PermissionsManager;

public class MainActivity extends AppCompatActivity {

    private PermissionsManager permissionsManager = new PermissionsManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConstraintLayout clContent = (ConstraintLayout) findViewById(R.id.clContent);

        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionsManager.setContext(MainActivity.this);
                permissionsManager.attachToView(clContent);
                permissionsManager.checkPermission(PermissionsManager.WRITE_EXTERNAL_REQUEST, new PermissionsManager.PermissionCallback() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
