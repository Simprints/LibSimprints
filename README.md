# LibSimprints
[Simprints ID](https://play.google.com/store/apps/details?id=com.simprints.id) Interface Library

*LibSimprints is the library used to pass data too and from the Simprints ID application.*

**Installation from jcenter** 

Gradle
```
compile 'com.simprints:LibSimprints:1.0.5'
```

Maven
```
<dependency>
  <groupId>com.simprints</groupId>
  <artifactId>LibSimprints</artifactId>
  <version>1.0.5</version>
  <type>pom</type>
</dependency>
```

**Examples**

Enrolment Callout Example
```
    private void enrol() {
        Intent intent = new Intent();
        intent.setAction(Constants.SIMPRINTS_REGISTER_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, "Your Valid API Key Here");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
        else {
           startActivity(new Intent(Intent.ACTION_VIEW, 
                    Uri.parse("https://play.google.com/store/apps/details?id=com.simprints.id")));
        }
    }
```
Identification Callout Example
```
    private void identify() {
        Intent intent = new Intent();
        intent.setAction(Constants.SIMPRINTS_IDENTIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, "Your Valid API Key Here");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
        else {
            startActivity(new Intent(Intent.ACTION_VIEW, 
                    Uri.parse("https://play.google.com/store/apps/details?id=com.simprints.id")));
        }
    }
```

Callback Example
```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_CANCELED || data == null) {
            return;
        }

        switch (requestCode) {

            //Enrollment Callback
            case 1:
                Registration registration = data.getParcelableExtra(Constants.SIMPRINTS_REGISTRATION);
                if (registration == null) {
                    //No registration object attached
                } else {
                    for (FingerIdentifier fingerId : FingerIdentifier.values()) {
                        //fingerId.name()
                    }
                }
                break;

            //Identification Callback
            case 2:
                ArrayList<Identification> identifications = data.getParcelableArrayListExtra(Constants.SIMPRINTS_IDENTIFICATIONS);
                if (identifications == null) {
                    //No identification objects attached
                } else {
                    for (Identification id : identifications) {
                        //id.getGuid()
                        //id.getConfidence()
                        //id.getTier().name()
                    }
                }
                break;
        }
    }
```
