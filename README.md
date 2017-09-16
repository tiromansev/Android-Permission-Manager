# Android-Permission-Manager
Android permission manager wrapper

# How to use

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
