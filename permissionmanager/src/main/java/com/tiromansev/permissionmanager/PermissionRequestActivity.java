package com.tiromansev.permissionmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class PermissionRequestActivity extends AppCompatActivity {

    private static final String PERMISSIONS_KEY     = "permissions";
    private static final int PERMISSIONS_REQUEST = 99;
    private static final String MESSAGE = "MESSAGE";
    private static final String RATIONALE_MESSAGE = "RATIONALE_MESSAGE";

    static Intent getRequestIntent(Context context, String rationaleMessage, String... permissions) {
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.putExtra(PERMISSIONS_KEY, permissions);
        intent.putExtra(RATIONALE_MESSAGE, rationaleMessage);
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
        );

        if (savedInstanceState == null && PermissionsManager.get().hasCallBack()) {
            handleIntent(getIntent());
            return;
        }
        close();
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
        String rationaleMessage = intent.getStringExtra(RATIONALE_MESSAGE);
        if (message == null) {
            final String[] permissions = intent.getStringArrayExtra(PERMISSIONS_KEY);
            if (rationaleMessage != null) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                dialog.setTitle(null);
                dialog.setMessage(rationaleMessage);
                dialog.setCancelable(false);
                dialog.setPositiveButton(R.string.caption_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PermissionsManager.get().markRequestedPermissionsAsAsked();
                        ActivityCompat.requestPermissions(PermissionRequestActivity.this, permissions, PERMISSIONS_REQUEST);
                    }
                });
                dialog.show();
            }
            else {
                ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST);
            }
        }
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            dialog.setTitle(null);
            dialog.setMessage(message);
            dialog.setCancelable(false);
            dialog.setPositiveButton(R.string.caption_close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    PermissionsManager.get().permissionRejected();
                    close();
                }
            });
            dialog.setNegativeButton(R.string.caption_allow_ask_again, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionsManager.get().executeSnackBarAction();
                    dialog.dismiss();
                    close();
                }
            });
            dialog.setNeutralButton(R.string.go_to_app_permissions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PermissionsManager.get().executeSnackBarAction();
                    PermissionsManager.get().intentToAppSettings(PermissionRequestActivity.this);
                    dialog.dismiss();
                    close();
                }
            });
            dialog.show();
        }
    }

    private void close() {
        PermissionsManager.setRequestActive(false);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        close();
        PermissionsManager.get().onRequestPermissionsResult(permissions);
    }
}
