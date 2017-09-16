# Android-Permission-Manager
Android permission manager wrapper

# How to use

```Java
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/clContent"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnCheck"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="8dp"
        android:text="Button"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="@string/caption_btn_check" />
</android.support.constraint.ConstraintLayout>
```

```Java
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
```

# Download

Add it in your root build.gradle at the end of repositories:

```Groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```Groovy
	dependencies {
	        compile 'com.github.tiromansev:Android-Permission-Manager:0.0.2'
	}
```

Maven:

```Groovy
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

Step 2. Add the dependency

```Groovy
<dependency>
	    <groupId>com.github.tiromansev</groupId>
	    <artifactId>Android-Permission-Manager</artifactId>
	    <version>0.0.2</version>
	</dependency>
```
