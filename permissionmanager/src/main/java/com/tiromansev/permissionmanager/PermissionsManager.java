package com.tiromansev.permissionmanager;

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
import static android.Manifest.permission.READ_MEDIA_AUDIO;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_MEDIA_VIDEO;
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
import static android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS;
import static android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class PermissionsManager {

    public final static int LOCATION_REQUEST = 100;
    public final static int WRITE_EXTERNAL_REQUEST = 101;
    public final static int READ_CALENDAR_REQUEST = 102;
    public final static int WRITE_CALENDAR_REQUEST = 103;
    public final static int CAMERA_REQUEST = 104;
    public final static int READ_CONTACTS_REQUEST = 105;
    public final static int WRITE_CONTACTS_REQUEST = 106;
    public final static int GET_ACCOUNTS_REQUEST = 107;
    public final static int ACCESS_FINE_LOCATION_REQUEST = 108;
    public final static int ACCESS_COARSE_LOCATION_REQUEST = 109;
    public final static int RECORD_AUDIO_REQUEST = 110;
    public final static int READ_PHONE_STATE_REQUEST = 111;
    public final static int CALL_PHONE_REQUEST = 112;
    public final static int READ_CALL_LOG_REQUEST = 113;
    public final static int WRITE_CALL_LOG_REQUEST = 114;
    public final static int ADD_VOICEMAIL_REQUEST = 115;
    public final static int USE_SIP_REQUEST = 116;
    public final static int PROCESS_OUTGOING_CALLS_REQUEST = 117;
    public final static int BODY_SENSORS_REQUEST = 118;
    public final static int SEND_SMS_REQUEST = 119;
    public final static int RECEIVE_SMS_REQUEST = 120;
    public final static int READ_SMS_REQUEST = 121;
    public final static int RECEIVE_WAP_PUSH_REQUEST = 122;
    public final static int RECEIVE_MMS_REQUEST = 123;
    public final static int READ_EXTERNAL_STORAGE_REQUEST = 124;
    public final static int READ_MEDIA_AUDIO_REQUEST = 125;
    public final static int READ_MEDIA_IMAGES_REQUEST = 126;
    public final static int READ_MEDIA_VIDEO_REQUEST = 127;

    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected;
    private static Context appContext;
    private static SharedPreferences appPreferences;
    private static PermissionsManager mInstance;
    private PermissionCallback permissionCallback;
    private static boolean requestActive = false;

    public static void setAppContext(Context appContext) {
        PermissionsManager.appContext = appContext;
        mInstance = new PermissionsManager();
        appPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public static void setRequestActive(boolean requestActive) {
        PermissionsManager.requestActive = requestActive;
    }

    public boolean hasCallBack() {
        return permissionCallback != null;
    }

    public static PermissionsManager get() {
        return mInstance;
    }

    public void checkLocationAcess(PermissionCallback permissionCallback) {
        checkPermissions(LOCATION_REQUEST, permissionCallback);
    }

    public void checkLocationAcess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, LOCATION_REQUEST);
    }

    public void checkWriteExternalAccess(PermissionCallback permissionCallback) {
        checkPermissions(WRITE_EXTERNAL_REQUEST, permissionCallback);
    }

    public void checkWriteExternalAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, WRITE_EXTERNAL_REQUEST);
    }

    public void checkReadCalendarAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_CALENDAR_REQUEST, permissionCallback);
    }

    public void checkReadCalendarAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_CALENDAR_REQUEST);
    }

    public void checkWriteCalendarAccess(PermissionCallback permissionCallback) {
        checkPermissions(WRITE_CALENDAR_REQUEST, permissionCallback);
    }

    public void checkWriteCalendarAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, WRITE_CALENDAR_REQUEST);
    }

    public void checkCameraAccess(PermissionCallback permissionCallback) {
        checkPermissions(CAMERA_REQUEST, permissionCallback);
    }

    public void checkCameraAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions( permissionCallback, rationaleMessage, CAMERA_REQUEST);
    }

    public void checkReadContactsAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_CONTACTS_REQUEST, permissionCallback);
    }

    public void checkReadContactsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_CONTACTS_REQUEST);
    }

    public void checkWriteContactsAccess(PermissionCallback permissionCallback) {
        checkPermissions(WRITE_CONTACTS_REQUEST, permissionCallback);
    }

    public void checkWriteContactsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, WRITE_CONTACTS_REQUEST);
    }

    public void checkGetAccountsAccess(PermissionCallback permissionCallback) {
        checkPermissions(GET_ACCOUNTS_REQUEST, permissionCallback);
    }

    public void checkGetAccountsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, GET_ACCOUNTS_REQUEST);
    }

    public void checkFineLocationAccess(PermissionCallback permissionCallback) {
        checkPermissions(ACCESS_FINE_LOCATION_REQUEST, permissionCallback);
    }

    public void checkFineLocationAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, ACCESS_FINE_LOCATION_REQUEST);
    }

    public void checkCoarseLocationAccess(PermissionCallback permissionCallback) {
        checkPermissions(ACCESS_COARSE_LOCATION_REQUEST, permissionCallback);
    }

    public void checkCoarseLocationAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, ACCESS_COARSE_LOCATION_REQUEST);
    }

    public void checkRecordAudioAccess(PermissionCallback permissionCallback) {
        checkPermissions(RECORD_AUDIO_REQUEST, permissionCallback);
    }

    public void checkRecordAudioAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, RECORD_AUDIO_REQUEST);
    }

    public void checkReadPhoneStateAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_PHONE_STATE_REQUEST, permissionCallback);
    }

    public void checkReadPhoneStateAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_PHONE_STATE_REQUEST);
    }

    public void checkCallPhoneAccess(PermissionCallback permissionCallback) {
        checkPermissions(CALL_PHONE_REQUEST, permissionCallback);
    }

    public void checkCallPhoneAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, CALL_PHONE_REQUEST);
    }

    public void checkReadCallLogAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_CALL_LOG_REQUEST, permissionCallback);
    }

    public void checkReadCallLogAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_CALL_LOG_REQUEST);
    }

    public void checkWriteCallLogAccess(PermissionCallback permissionCallback) {
        checkPermissions(WRITE_CALL_LOG_REQUEST, permissionCallback);
    }

    public void checkWriteCallLogAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, WRITE_CALL_LOG_REQUEST);
    }

    public void checkAddVoiceMailAccess(PermissionCallback permissionCallback) {
        checkPermissions(ADD_VOICEMAIL_REQUEST, permissionCallback);
    }

    public void checkAddVoiceMailAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, ADD_VOICEMAIL_REQUEST);
    }

    public void checkUseSipAccess(PermissionCallback permissionCallback) {
        checkPermissions(USE_SIP_REQUEST, permissionCallback);
    }

    public void checkUseSipAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, USE_SIP_REQUEST);
    }

    public void checkProcessOutgoingCallsAccess(PermissionCallback permissionCallback) {
        checkPermissions(PROCESS_OUTGOING_CALLS_REQUEST, permissionCallback);
    }

    public void checkProcessOutgoingCallsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, PROCESS_OUTGOING_CALLS_REQUEST);
    }

    public void checkBodySensorsAccess(PermissionCallback permissionCallback) {
        checkPermissions(BODY_SENSORS_REQUEST, permissionCallback);
    }

    public void checkBodySensorsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, BODY_SENSORS_REQUEST);
    }

    public void checkSendSmsAccess(PermissionCallback permissionCallback) {
        checkPermissions(SEND_SMS_REQUEST, permissionCallback);
    }

    public void checkSendSmsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, SEND_SMS_REQUEST);
    }

    public void checkReceiveSmsAccess(PermissionCallback permissionCallback) {
        checkPermissions(RECEIVE_SMS_REQUEST, permissionCallback);
    }

    public void checkReceiveSmsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, RECEIVE_SMS_REQUEST);
    }

    public void checkReadSmsAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_SMS_REQUEST, permissionCallback);
    }

    public void checkReadSmsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_SMS_REQUEST);
    }

    public void checkReceiveWapPushAccess(PermissionCallback permissionCallback) {
        checkPermissions(RECEIVE_WAP_PUSH_REQUEST, permissionCallback);
    }

    public void checkReceiveWapPushAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, RECEIVE_WAP_PUSH_REQUEST);
    }

    public void checkReceiveMmsAccess(PermissionCallback permissionCallback) {
        checkPermissions(RECEIVE_MMS_REQUEST, permissionCallback);
    }

    public void checkReceiveMmsAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, RECEIVE_MMS_REQUEST);
    }

    public void checkReadExternalAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_EXTERNAL_STORAGE_REQUEST, permissionCallback);
    }

    public void checkReadExternalAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_EXTERNAL_STORAGE_REQUEST);
    }

    public void checkReadAudioAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_MEDIA_AUDIO_REQUEST, permissionCallback);
    }

    public void checkReadAudioAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_MEDIA_AUDIO_REQUEST);
    }

    public void checkReadVideoAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_MEDIA_VIDEO_REQUEST, permissionCallback);
    }

    public void checkReadVideoAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_MEDIA_VIDEO_REQUEST);
    }

    public void checkReadImagesAccess(PermissionCallback permissionCallback) {
        checkPermissions(READ_MEDIA_IMAGES_REQUEST, permissionCallback);
    }

    public void checkReadImagesAccess(PermissionCallback permissionCallback, String rationaleMessage) {
        checkPermissions(permissionCallback, rationaleMessage, READ_MEDIA_IMAGES_REQUEST);
    }

    private void checkPermissions(int permissionId, @NonNull PermissionCallback permissionCallback) {
        checkPermissions(permissionCallback, permissionId);
    }

    public void checkPermissions(@NonNull PermissionCallback permissionCallback, Integer... permissionsId) {
        checkPermissions(permissionCallback, null, permissionsId);
    }

    public void checkPermissions(@NonNull PermissionCallback permissionCallback, String rationaleMessage, Integer... permissionsId) {
        ArrayList<String> permissions = new ArrayList<>();
        this.permissionCallback = permissionCallback;
        Log.d("permission_request", "set permission callback, requestActive = " + requestActive);

        if (!hasCallBack()) {
            return;
        }

        if (requestActive) {
            return;
        }

        for (int permissionId: permissionsId) {
            permissions.add(getPermissionStr(permissionId));
        }
        permissionsToRequest = findUnAskedPermissions(permissions);
        permissionsRejected = findRejectedPermissions(permissions);

        if (permissionsToRequest.size() > 0) {//we need to ask for permissions
            Log.d("permission_request", "start request");
            Intent requestIntent = PermissionRequestActivity.getRequestIntent(appContext, rationaleMessage,
                    permissionsToRequest.toArray(new String[permissionsToRequest.size()]));
            appContext.startActivity(requestIntent);
            if (rationaleMessage == null) {
                markRequestedPermissionsAsAsked();
            }
        } else {
            Log.d("permission_request", "has no request");
            if (permissionsRejected.size() > 0) {
                Intent messageIntent = PermissionRequestActivity.getMessageIntent(appContext, String.valueOf(permissionsRejected.size()) +
                        " " + appContext.getString(R.string.caption_permission_previously_rejected));
                appContext.startActivity(messageIntent);
            } else {
                if (hasCallBack()) {
                    this.permissionCallback.permissionAccepted();
                    this.permissionCallback = null;
                }
            }
        }
    }

    public void markRequestedPermissionsAsAsked() {
        for (String perm : permissionsToRequest) {
            markAsAsked(perm);
        }
    }

    public void executeSnackBarAction() {
        for (String perm : permissionsRejected) {
            clearMarkAsAsked(perm);
        }
        if (hasCallBack()) {
            this.permissionCallback.permissionRejected();
            this.permissionCallback = null;
        }
    }

    public void permissionRejected() {
        if (permissionCallback != null) {
            permissionCallback.permissionRejected();
        }
    }

    public void onRequestPermissionsResult(String[] permissions) {
        Log.d("permission_request", "requestPermission permissionCallback is null " + hasCallBack());
        if (hasCallBack()) {
            boolean allAccepted = true;
            for (String permission: permissions) {
                if (!hasPermission(permission)) {
                    permissionsRejected.add(permission);
                    allAccepted = false;
                }
            }
            if (allAccepted) {
                this.permissionCallback.permissionAccepted();
            } else {
                this.permissionCallback.permissionRejected();
            }
            this.permissionCallback = null;
        }
    }

    public String getPermissionStr(int requestCode) {
        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST:
                return WRITE_EXTERNAL_STORAGE;
                
            case LOCATION_REQUEST:
                return ACCESS_FINE_LOCATION;
                
            case READ_CALENDAR_REQUEST:
                return READ_CALENDAR;
                
            case WRITE_CALENDAR_REQUEST:
                return WRITE_CALENDAR;
                
            case CAMERA_REQUEST:
                return CAMERA;
                
            case READ_CONTACTS_REQUEST:
                return READ_CONTACTS;
                
            case WRITE_CONTACTS_REQUEST:
                return WRITE_CONTACTS;
                
            case GET_ACCOUNTS_REQUEST:
                return GET_ACCOUNTS;
                
            case ACCESS_FINE_LOCATION_REQUEST:
                return ACCESS_FINE_LOCATION;
                
            case ACCESS_COARSE_LOCATION_REQUEST:
                return ACCESS_COARSE_LOCATION;
                
            case RECORD_AUDIO_REQUEST:
                return RECORD_AUDIO;
                
            case READ_PHONE_STATE_REQUEST:
                return READ_PHONE_STATE;
                
            case CALL_PHONE_REQUEST:
                return CALL_PHONE;
                
            case READ_CALL_LOG_REQUEST:
                return READ_CALL_LOG;
                
            case WRITE_CALL_LOG_REQUEST:
                return WRITE_CALL_LOG;
                
            case ADD_VOICEMAIL_REQUEST:
                return ADD_VOICEMAIL;
                
            case USE_SIP_REQUEST:
                return USE_SIP;
                
            case PROCESS_OUTGOING_CALLS_REQUEST:
                return PROCESS_OUTGOING_CALLS;
                
            case BODY_SENSORS_REQUEST:
                return BODY_SENSORS;
                
            case SEND_SMS_REQUEST:
                return SEND_SMS;
                
            case RECEIVE_SMS_REQUEST:
                return RECEIVE_SMS;
                
            case READ_SMS_REQUEST:
                return READ_SMS;
                
            case RECEIVE_WAP_PUSH_REQUEST:
                return RECEIVE_WAP_PUSH;
                
            case RECEIVE_MMS_REQUEST:
                return RECEIVE_MMS;
                
            case READ_EXTERNAL_STORAGE_REQUEST:
                return READ_EXTERNAL_STORAGE;

            case READ_MEDIA_IMAGES_REQUEST:
                return READ_MEDIA_IMAGES;

            case READ_MEDIA_VIDEO_REQUEST:
                return READ_MEDIA_VIDEO;

            case READ_MEDIA_AUDIO_REQUEST:
                return READ_MEDIA_AUDIO;
        }

        return null;
    }

    private boolean hasPermission(String permission) {
        if (appContext == null) {
            return false;
        }
        return !canMakeSmores() ||
                (ActivityCompat.checkSelfPermission(appContext, permission) == PackageManager.PERMISSION_GRANTED);
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

    public void intentToAppSettings(@NonNull Activity activity) {
        //Open the specific App Info page:
        Intent intent = new Intent(ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + appContext.getPackageName()));
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivity(intent);
        } else {
            intent = new Intent(ACTION_MANAGE_APPLICATIONS_SETTINGS);
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        }
    }

}
