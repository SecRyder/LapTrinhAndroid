package com.ptithcm.servicedemo.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.ptithcm.servicedemo.MainActivity;
import com.ptithcm.servicedemo.R;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    private static final String TAG = "MusicService";
    private static final String CHANNEL_ID = "MusicServiceChannel";

    public static final String ACTION_PLAY = "ACTION_PLAY";
    public static final String ACTION_PAUSE = "ACTION_PAUSE";
    public static final String ACTION_RESUME = "ACTION_RESUME";
    public static final String ACTION_STOP = "ACTION_STOP";

    private MediaPlayer mediaPlayer;
    private Handler handler;
    private Runnable updateRunnable;
    private boolean isPaused = false;

    // Callback ve Activity
    private static OnMusicListener listener;

    public interface OnMusicListener {
        void onMusicUpdate(String songName, String time, boolean isPlaying, int progress, int duration);
        void onMusicStopped();
    }

    public static void setListener(OnMusicListener l) { listener = l; }
    public static void removeListener() { listener = null; }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() - Khoi tao Music Service");
        handler = new Handler(Looper.getMainLooper());
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : ACTION_PLAY;

        if (ACTION_STOP.equals(action)) {
            stopMusic();
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }

        if (ACTION_PAUSE.equals(action)) {
            pauseMusic();
            return START_STICKY;
        }

        if (ACTION_RESUME.equals(action)) {
            resumeMusic();
            return START_STICKY;
        }

        // ACTION_PLAY - Bat dau phat nhac
        startMusic();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        stopMusic();
    }

    // ============================================================
    // DIEU KHIEN NHAC
    // ============================================================

    private void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Dung nhac chuong mac dinh cua thiet bi (KHONG can them file nhac)
        // mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer = MediaPlayer.create(this, R.raw.khatvonghocvien);

        if (mediaPlayer == null) {
            // Fallback: dung alarm ringtone
            //mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
            mediaPlayer = MediaPlayer.create(this, R.raw.khatvonghocvien);
        }

        if (mediaPlayer == null) {
            // Fallback cuoi: dung notification ringtone
            //mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
            mediaPlayer = MediaPlayer.create(this, R.raw.khatvonghocvien);
        }

        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true); // Lap lai
            mediaPlayer.start();
            isPaused = false;

            Log.d(TAG, "Bat dau phat nhac - Duration: " + mediaPlayer.getDuration() + "ms");

            // Hien thi Notification BAT BUOC (Slide 3)
            Notification notification = buildNotification("Dang phat nhac...", true);
            startForeground(1, notification);

            // Bat dau cap nhat thoi gian
            startUpdating();
        } else {
            Log.e(TAG, "Khong the khoi tao MediaPlayer!");
            if (listener != null) {
                listener.onMusicUpdate("Loi: Khong tim thay nhac", "00:00", false, 0, 0);
            }
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPaused = true;
            Log.d(TAG, "PAUSE nhac");
            updateNotification("Tam dung", false);
            notifyListener();
        }
    }

    private void resumeMusic() {
        if (mediaPlayer != null && isPaused) {
            mediaPlayer.start();
            isPaused = false;
            Log.d(TAG, "RESUME nhac");
            updateNotification("Dang phat nhac...", true);
        }
    }

    private void stopMusic() {
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isPaused = false;
        Log.d(TAG, "STOP nhac");
        if (listener != null) {
            listener.onMusicStopped();
        }
    }

    // ============================================================
    // CAP NHAT THOI GIAN PHAT
    // ============================================================

    private void startUpdating() {
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && !isPaused) {
                    notifyListener();
                    updateNotification("Dang phat: " + getCurrentTimeStr(), true);
                }
                handler.postDelayed(this, 500);
            }
        };
        handler.post(updateRunnable);
    }

    private void notifyListener() {
        if (listener != null && mediaPlayer != null) {
            int current = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            String timeStr = formatMs(current) + " / " + formatMs(duration);
            boolean playing = mediaPlayer.isPlaying();

            listener.onMusicUpdate(
                    "Nhac chuong thiet bi",
                    timeStr,
                    playing,
                    current,
                    duration
            );
        }
    }

    private String getCurrentTimeStr() {
        if (mediaPlayer == null) return "00:00";
        return formatMs(mediaPlayer.getCurrentPosition());
    }

    private String formatMs(int ms) {
        int sec = (ms / 1000) % 60;
        int min = (ms / 1000) / 60;
        return String.format("%02d:%02d", min, sec);
    }

    // ============================================================
    // NOTIFICATION
    // ============================================================

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID, "Music Player", NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("Dieu khien nhac");
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }

    private Notification buildNotification(String text, boolean isPlaying) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        int icon = isPlaying ? android.R.drawable.ic_media_play : android.R.drawable.ic_media_pause;

        return new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Music Service Demo")
                .setContentText(text)
                .setSmallIcon(icon)
                .setContentIntent(pi)
                .setOngoing(true)
                .build();
    }

    private void updateNotification(String text, boolean isPlaying) {
        Notification notification = buildNotification(text, isPlaying);
        getSystemService(NotificationManager.class).notify(1, notification);
    }
}