package com.tiromansev.permissionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PermissionRequestActivity extends AppCompatActivity {

    private static final String PERMISSIONS_KEY     = "permissions";
    private static final String PERMISSIONS_REQUEST = "PERMISSIONS_REQUEST";
    private static final String MESSAGE = "MESSAGE";

    static Intent getRequestIntent(Context context, int requestCode, String... permissions) {
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.putExtra(PERMISSIONS_KEY, permissions);
        intent.putExtra(PERMISSIONS_REQUEST, requestCode);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    static Intent getMessageIntent(Context context, String message) {
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.putExtra(MESSAGE, message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("permission_request", "savedInstanceState is null = " +
                (savedInstanceState == null) + " PermissionsManager.get().hasCallBack() = " +
                PermissionsManager.get().hasCallBack()
        );        if (savedInstanceState == null && PermissionsManager.get().hasCallBack()) {
            handleIntent(getIntent());
            return;
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("permission_request", "resume perm activity");
        PermissionsManager.setRequestActive(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("permission_request", "pause perm activity");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String message = intent.getStringExtra(MESSAGE);
        if (message == null) {
            String[] permissions = intent.getStringArrayExtra(PERMISSIONS_KEY);
            int requestCode = intent.getIntExtra(PERMISSIONS_REQUEST, 0);
            ActivityCompat.requestPermissions(this, permissions, requestCode);
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(null);
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton(R.string.caption_close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.setNegativeButton(R.string.caption_allow_ask_again, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionsManager.get().executeSnackBarAction();
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.setNeutralButton(R.string.go_to_app_permissions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionsManager.get().executeSnackBarAction();
                    PermissionsManager.get().intentToAppSettings(PermissionRequestActivity.this);
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.get().onRequestPermissionsResult(requestCode);
        PermissionsManager.setRequestActive(false);
        finish();
    }
}
