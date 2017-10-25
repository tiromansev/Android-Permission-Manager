package com.tiromansev.permissionmanager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ADD_VOICEMAIL;
import static android.Manifest.permission.BODY_SENSORS;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.PROCESS_OUTGOING_CALLS;
import static android.Manifest.permission.READ_CALENDAR;
import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_MMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.RECEIVE_WAP_PUSH;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission.USE_SIP;
import static android.Manifest.permission.WRITE_CALENDAR;
import static android.Manifest.permission.WRITE_CALL_LOG;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PermissionsManager {

    private final static int LOCATION_REQUEST = 100;
    private final static int WRITE_EXTERNAL_REQUEST = 101;
    private final static int READ_CALENDAR_REQUEST = 102;
    private final static int WRITE_CALENDAR_REQUEST = 103;
    private final static int CAMERA_REQUEST = 104;
    private final static int READ_CONTACTS_REQUEST = 105;
    private final static int WRITE_CONTACTS_REQUEST = 106;
    private final static int GET_ACCOUNTS_REQUEST = 107;
    private final static int ACCESS_FINE_LOCATION_REQUEST = 108;
    private final static int ACCESS_COARSE_LOCATION_REQUEST = 109;
    private final static int RECORD_AUDIO_REQUEST = 110;
    private final static int READ_PHONE_STATE_REQUEST = 111;
    private final static int CALL_PHONE_REQUEST = 112;
    private final static int READ_CALL_LOG_REQUEST = 113;
    private final static int WRITE_CALL_LOG_REQUEST = 114;
    private final static int ADD_VOICEMAIL_REQUEST = 115;
    private final static int USE_SIP_REQUEST = 116;
    private final static int PROCESS_OUTGOING_CALLS_REQUEST = 117;
    private final static int BODY_SENSORS_REQUEST = 118;
    private final static int SEND_SMS_REQUEST = 119;
    private final static int RECEIVE_SMS_REQUEST = 120;
    private final static int READ_SMS_REQUEST = 121;
    private final static int RECEIVE_WAP_PUSH_REQUEST = 122;
    private final static int RECEIVE_MMS_REQUEST = 123;
    private final static int READ_EXTERNAL_STORAGE_REQUEST = 124;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected;
    private PermissionCallback permissionCallback;
    private View snackBarParent;
    private Activity context;
    private static Context appContext;
    private static SharedPreferences appPreferences;

    public static void setAppContext(Context appContext) {
        PermissionsManager.appContext = appContext;
    }

    public void attachTo(Activity context) {
        this.context = context;
        appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        snackBarParent = context.findViewById(android.R.id.content);
    }

    public void detachFrom() {
        appPreferences = null;
        snackBarParent = null;
        context = null;
    }

    public void checkLocationAcess(PermissionCallback permissionCallback) {
        checkPermission(LOCATION_REQUEST, permissionCallback);
    }

    public void checkWriteExternalAccess(PermissionCallback permissionCallback) {
        checkPermission(WRITE_EXTERNAL_REQUEST, permissionCallback);
    }

    public void checkReadCalendarAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_CALENDAR_REQUEST, permissionCallback);
    }

    public void checkWriteCalendarAccess(PermissionCallback permissionCallback) {
        checkPermission(WRITE_CALENDAR_REQUEST, permissionCallback);
    }

    public void checkCameraAccess(PermissionCallback permissionCallback) {
        checkPermission(CAMERA_REQUEST, permissionCallback);
    }

    public void checkReadContactsAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_CONTACTS_REQUEST, permissionCallback);
    }

    public void checkWriteContactsAccess(PermissionCallback permissionCallback) {
        checkPermission(WRITE_CONTACTS_REQUEST, permissionCallback);
    }

    public void checkGetAccountsAccess(PermissionCallback permissionCallback) {
        checkPermission(GET_ACCOUNTS_REQUEST, permissionCallback);
    }

    public void checkFineLocationAccess(PermissionCallback permissionCallback) {
        checkPermission(ACCESS_FINE_LOCATION_REQUEST, permissionCallback);
    }

    public void checkCoarseLocationAccess(PermissionCallback permissionCallback) {
        checkPermission(ACCESS_COARSE_LOCATION_REQUEST, permissionCallback);
    }

    public void checkRecordAudioAccess(PermissionCallback permissionCallback) {
        checkPermission(RECORD_AUDIO_REQUEST, permissionCallback);
    }

    public void checkReadPhoneStateAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_PHONE_STATE_REQUEST, permissionCallback);
    }

    public void checkCallPhoneAccess(PermissionCallback permissionCallback) {
        checkPermission(CALL_PHONE_REQUEST, permissionCallback);
    }

    public void checkReadCallLogAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_CALL_LOG_REQUEST, permissionCallback);
    }

    public void checkWriteCallLogAccess(PermissionCallback permissionCallback) {
        checkPermission(WRITE_CALL_LOG_REQUEST, permissionCallback);
    }

    public void checkAddVoiceMailAccess(PermissionCallback permissionCallback) {
        checkPermission(ADD_VOICEMAIL_REQUEST, permissionCallback);
    }

    public void checkUseSipAccess(PermissionCallback permissionCallback) {
        checkPermission(USE_SIP_REQUEST, permissionCallback);
    }

    public void checkProcessOutgoingCallsAccess(PermissionCallback permissionCallback) {
        checkPermission(PROCESS_OUTGOING_CALLS_REQUEST, permissionCallback);
    }

    public void checkBodySensorsAccess(PermissionCallback permissionCallback) {
        checkPermission(BODY_SENSORS_REQUEST, permissionCallback);
    }

    public void checkSendSmsAccess(PermissionCallback permissionCallback) {
        checkPermission(SEND_SMS_REQUEST, permissionCallback);
    }

    public void checkReceiveSmsAccess(PermissionCallback permissionCallback) {
        checkPermission(RECEIVE_SMS_REQUEST, permissionCallback);
    }

    public void checkReadSmsAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_SMS_REQUEST, permissionCallback);
    }

    public void checkReceiveWapPushAccess(PermissionCallback permissionCallback) {
        checkPermission(RECEIVE_WAP_PUSH_REQUEST, permissionCallback);
    }

    public void checkReceiveMmsAccess(PermissionCallback permissionCallback) {
        checkPermission(RECEIVE_MMS_REQUEST, permissionCallback);
    }

    public void checkReadExternalAccess(PermissionCallback permissionCallback) {
        checkPermission(READ_EXTERNAL_STORAGE_REQUEST, permissionCallback);
    }

    private void checkPermission(int permissionId, PermissionCallback permissionCallback) {
        if (context == null) {
            throw new RuntimeException(appContext.getString(R.string.message_context_is_null));
        }

        ArrayList<String> permissions = new ArrayList<>();
        this.permissionCallback = permissionCallback;

        switch (permissionId) {
            case LOCATION_REQUEST:
                permissions.add(ACCESS_FINE_LOCATION);
                break;
            case WRITE_EXTERNAL_REQUEST:
                permissions.add(WRITE_EXTERNAL_STORAGE);
                break;
            case READ_CALENDAR_REQUEST:
                permissions.add(READ_CALENDAR);
                break;
            case WRITE_CALENDAR_REQUEST:
                permissions.add(WRITE_CALENDAR);
                break;
            case CAMERA_REQUEST:
                permissions.add(CAMERA);
                break;
            case READ_CONTACTS_REQUEST:
                permissions.add(READ_CONTACTS);
                break;
            case WRITE_CONTACTS_REQUEST:
                permissions.add(WRITE_CONTACTS);
                break;
            case GET_ACCOUNTS_REQUEST:
                permissions.add(GET_ACCOUNTS);
                break;
            case ACCESS_FINE_LOCATION_REQUEST:
                permissions.add(ACCESS_FINE_LOCATION);
                break;
            case ACCESS_COARSE_LOCATION_REQUEST:
                permissions.add(ACCESS_COARSE_LOCATION);
                break;
            case RECORD_AUDIO_REQUEST:
                permissions.add(RECORD_AUDIO);
                break;
            case READ_PHONE_STATE_REQUEST:
                permissions.add(READ_PHONE_STATE);
                break;
            case CALL_PHONE_REQUEST:
                permissions.add(CALL_PHONE);
                break;
            case READ_CALL_LOG_REQUEST:
                permissions.add(READ_CALL_LOG);
                break;
            case WRITE_CALL_LOG_REQUEST:
                permissions.add(WRITE_CALL_LOG);
                break;
            case ADD_VOICEMAIL_REQUEST:
                permissions.add(ADD_VOICEMAIL);
                break;
            case USE_SIP_REQUEST:
                permissions.add(USE_SIP);
                break;
            case PROCESS_OUTGOING_CALLS_REQUEST:
                permissions.add(PROCESS_OUTGOING_CALLS);
                break;
            case BODY_SENSORS_REQUEST:
                permissions.add(BODY_SENSORS);
                break;
            case SEND_SMS_REQUEST:
                permissions.add(SEND_SMS);
                break;
            case RECEIVE_SMS_REQUEST:
                permissions.add(RECEIVE_SMS);
                break;
            case READ_SMS_REQUEST:
                permissions.add(READ_SMS);
                break;
            case RECEIVE_WAP_PUSH_REQUEST:
                permissions.add(RECEIVE_WAP_PUSH);
                break;
            case RECEIVE_MMS_REQUEST:
                permissions.add(RECEIVE_MMS);
                break;
            case READ_EXTERNAL_STORAGE_REQUEST:
                permissions.add(READ_EXTERNAL_STORAGE);
                break;
        }

        permissionsToRequest = findUnAskedPermissions(permissions);
        permissionsRejected = findRejectedPermissions(permissions);

        if (permissionsToRequest.size() > 0) {//we need to ask for permissions
            ActivityCompat.requestPermissions(context,
                    permissionsToRequest.toArray(new String[permissionsToRequest.size()]), permissionId);
            for (String perm : permissionsToRequest) {
                markAsAsked(perm);
            }
        } else {
            if (permissionsRejected.size() > 0) {
                if (snackBarParent != null) {
                    Snackbar
                            .make(snackBarParent, String.valueOf(permissionsRejected.size()) +
                                    " " + appContext.getString(R.string.caption_permission_previously_rejected), Snackbar.LENGTH_LONG)
                            .setAction(appContext.getString(R.string.caption_allow_ask_again), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (String perm : permissionsRejected) {
                                        clearMarkAsAsked(perm);
                                    }
                                }
                            })
                            .show();
                    this.permissionCallback.permissionRejected();
                    this.permissionCallback = null;
                } else {
                    Toast.makeText(context, appContext.getString(R.string.message_snakbar_parent_is_null), Toast.LENGTH_LONG).show();
                }
            } else {
                this.permissionCallback.permissionAccepted();
                this.permissionCallback = null;
            }
        }
    }

    private void requestPermission(String permission) {
        if (hasPermission(permission)) {
            this.permissionCallback.permissionAccepted();
            permissionCallback = null;
        } else {
            permissionsRejected.add(permission);
            makePostRequestSnack();
        }
    }

    public void onRequestPermissionsResult(Activity context, int requestCode) {
        this.context = context;
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST:
                requestPermission(WRITE_EXTERNAL_STORAGE);
                break;
            case LOCATION_REQUEST:
                requestPermission(ACCESS_FINE_LOCATION);
                break;
            case READ_CALENDAR_REQUEST:
                requestPermission(READ_CALENDAR);
                break;
            case WRITE_CALENDAR_REQUEST:
                requestPermission(WRITE_CALENDAR);
                break;
            case CAMERA_REQUEST:
                requestPermission(CAMERA);
                break;
            case READ_CONTACTS_REQUEST:
                requestPermission(READ_CONTACTS);
                break;
            case WRITE_CONTACTS_REQUEST:
                requestPermission(WRITE_CONTACTS);
                break;
            case GET_ACCOUNTS_REQUEST:
                requestPermission(GET_ACCOUNTS);
                break;
            case ACCESS_FINE_LOCATION_REQUEST:
                requestPermission(ACCESS_FINE_LOCATION);
                break;
            case ACCESS_COARSE_LOCATION_REQUEST:
                requestPermission(ACCESS_COARSE_LOCATION);
                break;
            case RECORD_AUDIO_REQUEST:
                requestPermission(RECORD_AUDIO);
                break;
            case READ_PHONE_STATE_REQUEST:
                requestPermission(READ_PHONE_STATE);
                break;
            case CALL_PHONE_REQUEST:
                requestPermission(CALL_PHONE);
                break;
            case READ_CALL_LOG_REQUEST:
                requestPermission(READ_CALL_LOG);
                break;
            case WRITE_CALL_LOG_REQUEST:
                requestPermission(WRITE_CALL_LOG);
                break;
            case ADD_VOICEMAIL_REQUEST:
                requestPermission(ADD_VOICEMAIL);
                break;
            case USE_SIP_REQUEST:
                requestPermission(USE_SIP);
                break;
            case PROCESS_OUTGOING_CALLS_REQUEST:
                requestPermission(PROCESS_OUTGOING_CALLS);
                break;
            case BODY_SENSORS_REQUEST:
                requestPermission(BODY_SENSORS);
                break;
            case SEND_SMS_REQUEST:
                requestPermission(SEND_SMS);
                break;
            case RECEIVE_SMS_REQUEST:
                requestPermission(RECEIVE_SMS);
                break;
            case READ_SMS_REQUEST:
                requestPermission(READ_SMS);
                break;
            case RECEIVE_WAP_PUSH_REQUEST:
                requestPermission(RECEIVE_WAP_PUSH);
                break;
            case RECEIVE_MMS_REQUEST:
                requestPermission(RECEIVE_MMS);
                break;
            case READ_EXTERNAL_STORAGE_REQUEST:
                requestPermission(READ_EXTERNAL_STORAGE);
                break;
        }
    }

    private void makePostRequestSnack() {
        if (snackBarParent != null) {
            Snackbar
                    .make(snackBarParent, String.valueOf(permissionsRejected.size()) + " " +
                            appContext.getString(R.string.caption_permission_rejected),
                            Snackbar.LENGTH_LONG)
                    .setAction(appContext.getString(R.string.caption_allow_ask_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (String perm : permissionsRejected) {
                                clearMarkAsAsked(perm);
                            }
                        }
                    })
                    .show();
            permissionCallback.permissionRejected();
            permissionCallback = null;
        } else if (context != null) {
            Toast.makeText(context, appContext.getString(R.string.message_snakbar_parent_is_null), Toast.LENGTH_LONG).show();
        }
    }

    private boolean hasPermission(String permission) {
        if (context == null) {
            return false;
        }
        return !canMakeSmores() ||
                (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean shouldWeAsk(String permission) {
        if (appPreferences == null) {
            return false;
        }
        return appPreferences.getBoolean(permission, true);
    }

    private void markAsAsked(String permission) {
        if (appPreferences != null) {
            SharedPreferences.Editor edit = appPreferences.edit();
            edit.putBoolean(permission, false);
            edit.apply();
        }
    }

    private void clearMarkAsAsked(String permission) {
        if (appPreferences != null) {
            SharedPreferences.Editor edit = appPreferences.edit();
            edit.putBoolean(permission, true);
            edit.apply();
        }
    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm) && shouldWeAsk(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private ArrayList<String> findRejectedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wanted) {
            if (!hasPermission(perm) && !shouldWeAsk(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public interface PermissionCallback {
        void permissionAccepted();
        void permissionRejected();
    }

}
