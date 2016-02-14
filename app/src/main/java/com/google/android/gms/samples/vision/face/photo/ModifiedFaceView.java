package com.google.android.gms.samples.vision.face.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

/**
 * Created by niol on 2016/2/14.
 */
public class ModifiedFaceView extends View {
    private Bitmap mBitmap, mBitmap2;
    private SparseArray<Face> mFaces, mFaces2;

    public ModifiedFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets the bitmap background and the associated face detections.
     */
    void setContent(Bitmap bitmap, SparseArray<Face> faces,Bitmap bitmap2,SparseArray<Face> faces2) {
        mBitmap = bitmap;
        mFaces = faces;

        mBitmap2 = bitmap2;
        mFaces2 = faces2;
        invalidate();
    }

    /**
     * Draws the bitmap background and the associated face landmarks.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if ((mBitmap != null) && (mFaces != null ) && (mBitmap2 != null) && (mFaces2 != null)) {
            double scale = drawBitmap(canvas);
            //drawFaceAnnotations(canvas, scale);
            //Bitmap resizedbitmap=Bitmap.createBitmap(bitmap, 0,0,(int)faces.valueAt(0).getLandmarks().get(0).getPosition().x,(int)faces.valueAt(0).getLandmarks().get(0).getPosition().y);

        }
    }

    /**
     * Draws the bitmap background, scaled to the device size.  Returns the scale for future use in
     * positioning the facial landmark graphics.
     */
    private double drawBitmap(Canvas canvas) {
        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        double scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight);

        //Rect destBounds = new Rect(0, 0, (int)(imageWidth * scale / 2), (int)(imageHeight * scale / 2));
        //Rect destBounds = new Rect(0, 0, (int)(mFaces.valueAt(0).getLandmarks().get(0).getPosition().x * scale), (int)(mFaces.valueAt(0).getLandmarks().get(0).getPosition().y * scale));

        float leftEyeX = mFaces.valueAt(0).getLandmarks().get(0).getPosition().x;
        float leftEyeY = mFaces.valueAt(0).getLandmarks().get(0).getPosition().y;
        float rightEyeX = mFaces.valueAt(0).getLandmarks().get(1).getPosition().x;
        float rightEyeY = mFaces.valueAt(0).getLandmarks().get(1).getPosition().y;
        float noseX = mFaces.valueAt(0).getLandmarks().get(2).getPosition().x;
        float noseY = mFaces.valueAt(0).getLandmarks().get(2).getPosition().y;
        float k = 0.3f;
        float leftBorder = leftEyeX - k * (rightEyeX - leftEyeX);
        float width = (1 + 2 * k) * (rightEyeX - leftEyeX);
        float k2 = 0.7f, k3 = 0.15f;
        float upperborder = leftEyeY - k2 * (noseY - leftEyeY);
        float height = (1 + k3 + k2) * (noseY - leftEyeY);

        //Bitmap resizedbitmap=Bitmap.createBitmap(mBitmap, (int)leftBorder, (int)upperborder , (int)width,(int)height);
        //Bitmap resizedbitmap=Bitmap.createBitmap(mBitmap, (int)leftBorder,(int)upperborder, (int)rightBorder,(int)lowerborder);

        //canvas.drawBitmap(resizedbitmap,0,0,null);
        //canvas.drawBitmap(resizedbitmap,null,destBounds,null);
        //canvas.drawBitmap(mBitmap, null, destBounds, null);

        //do the same thing for mBitmap2
        double imageWidth2 = mBitmap2.getWidth();
        double imageHeight2 = mBitmap2.getHeight();
        double scale2 = Math.min(viewWidth / imageWidth2, viewHeight / imageHeight2);

        //Rect destBounds2 = new Rect(0, 0, (int)(imageWidth2 * scale / 2), (int)(imageHeight2 * scale / 2));
        //Rect destBounds = new Rect(0, 0, (int)(mFaces.valueAt(0).getLandmarks().get(0).getPosition().x * scale), (int)(mFaces.valueAt(0).getLandmarks().get(0).getPosition().y * scale));

        float leftEyeX2 = mFaces2.valueAt(0).getLandmarks().get(0).getPosition().x;
        float leftEyeY2 = mFaces2.valueAt(0).getLandmarks().get(0).getPosition().y;
        float rightEyeX2 = mFaces2.valueAt(0).getLandmarks().get(1).getPosition().x;
        float rightEyeY2 = mFaces2.valueAt(0).getLandmarks().get(1).getPosition().y;
        float noseX2 = mFaces2.valueAt(0).getLandmarks().get(2).getPosition().x;
        float noseY2 = mFaces2.valueAt(0).getLandmarks().get(2).getPosition().y;
        float k_2 = 0.3f;
        float leftBorder2 = leftEyeX2 - k_2 * (rightEyeX2 - leftEyeX2);
        float width2 = (1 + 2 * k_2) * (rightEyeX2 - leftEyeX2);
        float k2_2 = 0.7f, k3_2 = 0.15f;
        float upperBorder2 = leftEyeY2 - k2_2 * (noseY2 - leftEyeY2);
        float height2 = (1 + k3_2 + k2_2) * (noseY2 - leftEyeY2);

        Bitmap resizedbitmap = Bitmap.createBitmap(mBitmap, (int)leftBorder, (int)upperborder , (int)width,(int)height);
        //Bitmap resizedbitmap=Bitmap.createBitmap(mBitmap, (int)leftBorder,(int)upperborder, (int)rightBorder,(int)lowerborder);
        Rect destBounds = new Rect(0, 0, (int)(imageWidth2 * scale2 ), (int)(imageHeight2 * scale2 ));
        Rect destBounds2 = new Rect((int)(leftBorder2 * scale2), (int)(upperBorder2 * scale2), (int)((leftBorder2 + width2) * scale2), (int)((upperBorder2 + height2) * scale2));

        //canvas.drawBitmap(resizedbitmap,0,0,null);
        canvas.drawBitmap(mBitmap2, null, destBounds, null);
        canvas.drawBitmap(resizedbitmap,null,destBounds2,null);
        //canvas.drawBitmap(mBitmap2, null, destBounds, null);
        return scale;
    }

    /**
     * Draws a small circle for each detected landmark, centered at the detected landmark position.
     * <p>
     *
     * Note that eye landmarks are defined to be the midpoint between the detected eye corner
     * positions, which tends to place the eye landmarks at the lower eyelid rather than at the
     * pupil position.
     */
    private void drawFaceAnnotations(Canvas canvas, double scale) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        for (int i = 0; i < mFaces.size(); ++i) {
            Face face = mFaces.valueAt(i);
            for (Landmark landmark : face.getLandmarks()) {
                int cx = (int) (landmark.getPosition().x * scale);
                int cy = (int) (landmark.getPosition().y * scale);
                canvas.drawCircle(cx, cy, 10, paint);
            }
        }
    }
}


