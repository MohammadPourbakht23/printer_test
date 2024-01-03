package com.example.printer_test;

import static com.smartdevice.aidltestdemo.BaseActivity.mIzkcService;

import android.content.ServiceConnection;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.afid.utils.ZKCManager;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.dev/battery";

//    ZKCManager zkcManager;
    ServiceConnection mServiceConn;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            // This method is invoked on the main thread.


//                            mServiceConn.onServiceConnected();


//                            zkcManager.getPrintManager().printQRCode("123");

                            if (call.method.equals("getBatteryLevel")) {

                                System.out.println("printText func");
                                try {
                                    mIzkcService.printerInit();
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }
                                try {
                                    String status =mIzkcService.getPrinterStatus();
                                    System.out.println("+++++++++++++++++status++++++++++++++++++++");
                                    System.out.println(status);
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    boolean available = mIzkcService.checkPrinterAvailable();
                                    System.out.println("+++++++++++++++++available++++++++++++++++++++");
                                    System.out.println(available);
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }

                                try {
                                    System.out.println("+++++++++++++++++call print++++++++++++++++++++");
                                    mIzkcService.printTextWithFont("test text print",0,15);
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }

//                                zkcManager = ZKCManager.getInstance(this);
//                                zkcManager.bindService();

//                                if(zkcManager!=null){
//                                    zkcManager.getPrintManager().printText("Text test", 0 , 15);
//                                }
//                                int batteryLevel = getBatteryLevel();
                                int batteryLevel = 10;
//                                getBatteryLevel();

                                if (batteryLevel != -1) {
                                    result.success(batteryLevel);
                                } else {
                                    result.error("UNAVAILABLE", "Battery level not available.", null);
                                }
                            } else {
                                result.notImplemented();
                            }
                        }
                );
    }

//    public void printText(String message) {
//        System.out.println("printText func");
//        zkcManager.getPrintManager().printText(message);
//    }
//
//    public void printQRCode(int qrCode) {
//        zkcManager.getPrintManager().printQRCode(String.valueOf(qrCode));
//    }
//
//    public void printBarCode(int barCode) {
//        zkcManager.getPrintManager().printBarCode(String.valueOf(barCode));
//    }

//    public void scanQRCode(View view) {
//        zkcManager.getScanManager().toScanAct(this);
//    }

//    private void getBatteryLevel() {
////        int batteryLevel = -1;
////        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
////            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
////            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
////        } else {
////            Intent intent = new ContextWrapper(getApplicationContext()).
////                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
////            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
////                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
////        }
////
////        return batteryLevel;
//
//
//
//    }
}


