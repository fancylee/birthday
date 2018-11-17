package lijianchnag.birthdayapp.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import lijianchnag.birthdayapp.R;


/**
 * Created by 13155 on 2018/6/7:14:27.
 * Des :
 */

public class SoundPoolUtils {

    private final SoundPool soundPool;

    public static SoundPoolUtils instance;

    public static SoundPoolUtils getInstance(Context context){
        if(instance == null){
            instance = new SoundPoolUtils(context);
        }
        return instance;
    }

    private SoundPoolUtils(Context context){
        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0);
        soundPool.load(context, R.raw.birthday,1);
    }

    public void play(){
        soundPool.play(1,0.5f, 0.5f, 0, 0, 1);

    }
}
