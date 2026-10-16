package com.nico.nicoboy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

// NicoBoy: Startanimation (Logo faellt wie beim Game Boy von oben ein), danach startet der Emulator.
public class MainActivity extends Activity {
    private boolean started = false;
    private final Handler handler = new Handler();

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
        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(0xFF1B2A41);
        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.nicoboy_logo);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int size = (int) (Math.min(dm.widthPixels, dm.heightPixels) * 0.62f);
        root.addView(logo, new FrameLayout.LayoutParams(size, size, Gravity.CENTER));
        setContentView(root);
        root.setOnClickListener(v -> startGame()); // Tippen ueberspringt die Animation

        logo.setAlpha(0f);
        logo.setTranslationY(-dm.heightPixels * 0.6f);
        logo.animate().translationY(0f).alpha(1f).setDuration(1000)
            .setInterpolator(new BounceInterpolator())
            .withEndAction(() -> logo.animate().alpha(0f).setStartDelay(700).setDuration(350)
                .withEndAction(this::startGame).start())
            .start();
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
