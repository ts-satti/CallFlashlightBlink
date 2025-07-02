# 📞 CallFlashlightBlink

An Android application that blinks the flashlight when there's an incoming call — helpful for users in silent mode or with hearing impairments.

## 🚀 Features

- Detects incoming calls
- Blinks flashlight while phone is ringing
- Stops blinking once call ends
- Runs via BroadcastReceiver
- Uses Android's Camera2 API


## 🔧 Requirements

- Android Studio (Arctic Fox or newer)
- Android SDK 21+
- Java 8 or higher

## ⚙️ How It Works

- A custom `BroadcastReceiver` (`PhoneStateReceiver`) listens for call state changes.
- On `CALL_STATE_RINGING`, the flashlight blinks using `CameraManager` and `setTorchMode`.
- Automatically stops blinking on `CALL_STATE_IDLE`.

## 🛠️ Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/CallFlashlightBlink.git
   cd CallFlashlightBlink
   ```

2. Open in **Android Studio**

3. Build the project

4. Install APK via USB:
   ```bash
   adb install app/build/outputs/apk/debug/app-debug.apk
   ```

## ⚠️ Permissions Required

Make sure your app has the following permissions in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
```

Also add in the `MainActivity` or launch screen:

```java
ActivityCompat.requestPermissions(this, new String[]{
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.CAMERA
}, 1);
```

## ✅ TODOs / Future Improvements

- Add toggle to enable/disable flashlight blinking
- Customize blink frequency
- Support for Do Not Disturb mode detection