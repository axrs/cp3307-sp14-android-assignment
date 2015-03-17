package au.edu.jcu.mobile_technology.scott.alexander.assignment.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioEngine {

    //Logging Class Name Tag
    private static final String TAG = "AudioEngine";

    //Background Audio Track reference for pause and resume
    private static Audible backgroundAudio = null;
    //Background audio seek location for resume
    private static int backgroundSeekLocation = 0;

    //Class Instance Reference
    private static AudioEngine instance;

    //Class SoundPool reference for sound effects
    private static SoundPool soundPool;

    // Class MediaPlayer reference for background audio
    private static MediaPlayer mediaPlayer;

    // AudioEngine context
    private static Context context;

    //AudioQueue
    private BlockingQueue<Audible> audioQueue = new LinkedBlockingQueue<Audible>();

    //AudioEngine State
    private boolean isRunning = true;

    protected AudioEngine() {
        new Thread(core()).start();
    }

    /**
     * Gets the AudioEngine
     *
     * @return AudioEngine instance
     */
    public static AudioEngine getInstance(Context context) {
        if (instance == null) {
            instance = new AudioEngine();
            AudioEngine.context = context;
        }
        return instance;
    }

    /**
     * Adds a sound to the play queue
     *
     * @param sound Audible reference to play
     */
    public static void add(Audible sound) {
        System.out.printf("HERE");
        System.out.printf(sound.getClass().toString());
        getInstance(context).addSound(sound);
    }

    /**
     * Sets the background audio
     *
     * @param sound Audible reference to play
     */
    public static void setBackgroundAudio(Audible sound) {
        getInstance(context).setBackground(sound);
    }

    /**
     * Starts the AudioEngine
     */
    public void start() {
        isRunning = true;
        new Thread(core()).start();
    }

    /**
     * Stops the AudioEngine
     */
    public void stop() {
        isRunning = false;
        stopBackground();
    }

    /**
     * Gets the current AudioEngine state
     *
     * @return True if running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Gets the SoundPool reference
     *
     * @return SoundPool instance
     */
    private SoundPool getSoundPool() {
        if (soundPool == null) {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        return soundPool;
    }

    /**
     * AudioEngine Processing Method
     *
     * @return Runnable Method
     */
    private Runnable core() {
        return new Runnable() {
            @Override
            public void run() {
                SoundPool pool = getSoundPool();
                pool.autoResume();

                startBackground();

                while (isRunning) {

                    if (audioQueue.size() > 0) {
                        try {
                            Audible sound = audioQueue.take();

                            final int loop = (sound.isLooped()) ? -1 : 0;
                            final float leftVolume = sound.getLeftVolume();
                            final float rightVolume = sound.getRightVolume();

                            if (sound.getSoundId() == 0) {
                                final int result = pool.load(context, sound.getResourceId(), 1);
                                sound.setSoundId(result);
                                //audioQueue.add(sound);
                            } else {
                                int soundId = sound.getSoundId();
                                getSoundPool().play(soundId, leftVolume, rightVolume, 1, loop, 1);
                            }

                        } catch (InterruptedException e) {
                            Log.e(TAG, e.toString());
                        }
                    }
                }

                soundPool.autoPause();
                stopBackground();
            }
        };
    }

    /**
     * Adds a sound to the audio queue
     *
     * @param sound Audible reference to play
     */
    public void addSound(Audible sound) {
        try {
            audioQueue.put(sound);
        } catch (InterruptedException ex) {
            Log.e(TAG, ex.toString());
        }
    }

    /**
     * Sets the background music of the AudioEngine
     *
     * @param sound Audible reference to play
     */
    public void setBackground(Audible sound) {
        backgroundAudio = sound;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        AssetFileDescriptor afd = context.getResources().openRawResourceFd(sound.getResourceId());

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                Log.d(TAG, "MediaPlayer Prepared.");
            }
        });

        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(sound.isLooped());
            mediaPlayer.setVolume(sound.getLeftVolume(), sound.getRightVolume());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }


    /**
     * Pauses the AudioEngine
     */
    public void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            backgroundSeekLocation = mediaPlayer.getCurrentPosition();
        }
    }


    /**
     * Resumes the AudioEngine
     */
    public void resume() {
        if (mediaPlayer != null
                && backgroundAudio != null
                && !mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(backgroundSeekLocation);
            mediaPlayer.start();
        }

        if (!isRunning) {
            isRunning = true;
            new Thread(core()).start();
        }
    }

    /**
     * Stops the background audio
     */
    public void stopBackground() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    /**
     * Starts the background audio
     */
    public void startBackground() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

}
