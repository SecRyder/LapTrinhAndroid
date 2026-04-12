package com.ptithcm.demoappmusic;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    TextView tvTime, tvDuration;
    SeekBar seekBarTime, seekBarVolume;
    ImageView imgvolumnedown, imgvolumneup;
    Button btnPlay;
    MediaPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Khởi tạo MediaPlayer
        musicPlayer = MediaPlayer.create(this, R.raw.cochangtraivietlencay);

        // Ánh xạ view
        tvTime = findViewById(R.id.tvTime);
        tvDuration = findViewById(R.id.tvDuration);
        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        imgvolumnedown = findViewById(R.id.imgvolumnedown);
        imgvolumneup = findViewById(R.id.imgvolumneup);
        btnPlay = findViewById(R.id.btnPlay);

        // Hiển thị tổng thời gian bài hát
        tvDuration.setText(millisecondsToString(musicPlayer.getDuration()));
        seekBarTime.setMax(musicPlayer.getDuration());

        // Nút Play/Pause
        btnPlay.setOnClickListener(this);
        btnPlay.setBackgroundResource(R.drawable.ic_play);

        // Thread cập nhật thời gian
        new Thread(this).start();

        // Âm lượng mặc định
        seekBarVolume.setProgress(50);
        musicPlayer.setVolume(0.5f, 0.5f);

        // Điều chỉnh âm lượng bằng SeekBar
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float volume = i / 100f;
                musicPlayer.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Điều chỉnh tiến trình bài hát
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                if (fromUser) {
                    musicPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Nút tăng âm lượng
        imgvolumneup.setOnClickListener(v -> {
            int currentVol = seekBarVolume.getProgress();
            currentVol = Math.min(100, currentVol + 10);
            float vol = currentVol / 100f;
            musicPlayer.setVolume(vol, vol);
            seekBarVolume.setProgress(currentVol);
        });

        // Nút giảm âm lượng
        imgvolumnedown.setOnClickListener(v -> {
            int currentVol = seekBarVolume.getProgress();
            currentVol = Math.max(0, currentVol - 10);
            float vol = currentVol / 100f;
            musicPlayer.setVolume(vol, vol);
            seekBarVolume.setProgress(currentVol);
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPlay) {
            if (musicPlayer.isPlaying()) {
                musicPlayer.pause();
                btnPlay.setBackgroundResource(R.drawable.ic_play);
            } else {
                musicPlayer.start();
                btnPlay.setBackgroundResource(R.drawable.ic_pause);
            }
        }
    }

    @Override
    public void run() {
        while (musicPlayer != null) {
            try {
                Thread.sleep(1000);
                if (musicPlayer.isPlaying()) {
                    final int current = musicPlayer.getCurrentPosition();
                    final String elapsedTime = millisecondsToString(current);
                    runOnUiThread(() -> {
                        tvTime.setText(elapsedTime);
                        seekBarTime.setProgress(current);
                    });
                }
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
        }
    }

    public String millisecondsToString(int time) {
        int minutes = time / 1000 / 60;
        int seconds = time / 1000 % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicPlayer != null) {
            musicPlayer.release();
            musicPlayer = null;
        }
    }
}
