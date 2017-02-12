package in.compuboot.tboode.streetmusicchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


public class QRScannerActivity extends AppCompatActivity {
    SurfaceView cameraView;
    TextView barcodeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_qrscanner );

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.code_info);

        detect();
    }

    private void detect() {
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        final CameraSource cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new MyCameraSurface(cameraSource));

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() { // No special functionality is needed for releasing the barcode scanner.
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeInfo.post(new MyDetector(barcodes));
                }

            }
        });
    }

    private class MyDetector implements Runnable {
        private final SparseArray<Barcode> barcodes;

        public MyDetector(SparseArray<Barcode> barcodes) {
            this.barcodes = barcodes;
        }     // Use the post method of the TextView

        @Override
        public void run() {

            String qrValue = barcodes.valueAt(0).displayValue;

            barcodeInfo.setText(qrValue);

            if(qrValue.toLowerCase().contains("compuboot.in"))
            {
                //navigate to artist
                UriManager uriManager = UriManager.getInstance();
                uriManager.setUri(qrValue);
                Intent myIntent = new Intent(QRScannerActivity.this, WrapperActivity.class);
                QRScannerActivity.this.startActivity(myIntent);
            }
            else
            {
                Toast.makeText(QRScannerActivity.this, "invalid uri: " + qrValue, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class MyCameraSurface implements SurfaceHolder.Callback {
        private final CameraSource cameraSource;

        public MyCameraSurface(CameraSource cameraSource) {
            this.cameraSource = cameraSource;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException ie) {
                Log.e("CAMERA SOURCE", ie.getMessage());
                cameraSource.stop();
            }
            catch (SecurityException se) {
                Log.e("CAMERA SOURCE", se.getMessage());
                cameraSource.stop();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { /* Empty because we do not need to handle surface changes */ }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            cameraSource.stop();
        }
    }
}