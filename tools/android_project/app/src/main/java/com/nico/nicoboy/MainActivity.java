package com.nico.nicoboy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

// NicoBoy: Startanimation (drehendes Sharingan + Logo faellt ein), danach startet der Emulator.
public class MainActivity extends Activity {
    private boolean started = false;

    // Einfaches Sharingan, ohne Bilddatei gezeichnet
    static class SharinganView extends View {
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        SharinganView(Context c) { super(c); }
        @Override
        protected void onDraw(Canvas cv) {
            float cx = getWidth() / 2f, cy = getHeight() / 2f;
            float r = Math.min(getWidth(), getHeight()) / 2f * 0.9f;
            p.setStyle(Paint.Style.FILL);
            p.setColor(0x33FF2832); cv.drawCircle(cx, cy, r * 1.08f, p);
            p.setColor(0xFF08080A); cv.drawCircle(cx, cy, r * 1.0f, p);
            p.setColor(0xFFD62639); cv.drawCircle(cx, cy, r * 0.93f, p);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(r * 0.07f);
            p.setColor(0xFF0A080A); cv.drawCircle(cx, cy, r * 0.58f, p);
            p.setStyle(Paint.Style.FILL);
            p.setColor(0xFF08080A); cv.drawCircle(cx, cy, r * 0.18f, p);
            for (int i = 0; i < 3; i++) {
                double a = i * 2.0943951;
                float px = cx + (float) Math.cos(a) * r * 0.58f;
                float py = cy + (float) Math.sin(a) * r * 0.58f;
                cv.drawCircle(px, py, r * 0.16f, p);
                cv.drawCircle(px + (float) -Math.sin(a) * r * 0.19f, py + (float) Math.cos(a) * r * 0.19f, r * 0.085f, p);
            }
        }
    }

    private void startGame() {
        if (started) return;
        started = true;
        startActivity(new Intent(this, EnhancedNativeActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int minSide = Math.min(dm.widthPixels, dm.heightPixels);
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(0xFF070B14);

        SharinganView sharingan = new SharinganView(this);
        int sSize = (int) (minSide * 1.05f);
        root.addView(sharingan, new FrameLayout.LayoutParams(sSize, sSize, Gravity.CENTER));

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.nicoboy_logo);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int size = (int) (minSide * 0.62f);
        root.addView(logo, new FrameLayout.LayoutParams(size, size, Gravity.CENTER));
        setContentView(root);
        root.setOnClickListener(v -> startGame()); // Tippen ueberspringt die Animation

        sharingan.setAlpha(0f);
        sharingan.setScaleX(0.3f);
        sharingan.setScaleY(0.3f);
        sharingan.animate().alpha(0.42f).scaleX(1f).scaleY(1f).rotationBy(900f)
            .setDuration(2400).setInterpolator(new LinearInterpolator()).start();

        logo.setAlpha(0f);
        logo.setTranslationY(-dm.heightPixels * 0.6f);
        logo.animate().translationY(0f).alpha(1f).setDuration(1000)
            .setInterpolator(new BounceInterpolator())
            .withEndAction(() -> logo.animate().alpha(0f).setStartDelay(900).setDuration(400)
                .withEndAction(this::startGame).start())
            .start();
    }
}
