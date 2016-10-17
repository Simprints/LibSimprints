# LibSimprints

**LibSimprints is the library used to pass data too and from the Simprints ID application.**

Enrolment Callout Example
```
    private void enrol() {
        Intent intent = new Intent();
        intent.setAction(Constants.SIMPRINTS_REGISTER_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, "Your Valid API Key Here");
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
        else {
            Toast.makeText(this, "Sorry: Simprints ID not installed\nPlease install from Google Play store", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
```
Identification Callout Example
```
    private void identify() {
        Intent intent = new Intent();
        intent.setAction(Constants.SIMPRINTS_IDENTIFY_INTENT);
        intent.putExtra(Constants.SIMPRINTS_API_KEY, "Your Valid API Key Here");
        intent.setType("text/plain");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
        else {
            Toast.makeText(this, "Sorry: Simprints ID not installed\nPlease install from Google Play store", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
```

Callback Example
```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the activity result is cancelled ignore it
        if (resultCode != RESULT_CANCELED) {
            assert data != null;
            
            switch (request) {
                //Enrol callback example
                case 1:
                    Registration registration = data.getParcelableExtra(Constants.SIMPRINTS_REGISTRATION);
                    if (registration == null) {
                        //No registration object attached
                    } else {
                        //registration.getGuid()));
                        for (FingerIdentifier fingerId : FingerIdentifier.values()) {
                            //fingerId.name()
                        }
                    }
                    break;
                //Identify callback example    
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
    }
```
