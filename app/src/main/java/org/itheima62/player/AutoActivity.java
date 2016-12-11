package org.itheima62.player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.itheima62.player.async.R;

/**
 * Email: suoyiman@163.coom
 */
//http://news.iciba.com/admin/tts/2013-05-03.mp3

public class AutoActivity extends Activity {

    private Button mButton;
    private MediaPlayer player;
    String url1 = "http://news.iciba.com/admin/tts/2013-05-03.mp3";
    String url2 = "http://news.iciba.com/admin/tts/2013-05-04.mp3";
    String curUrl = "";
    private TextView mTextView;
    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        mTextView = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.button);
        mButton1 = (Button) findViewById(R.id.changtourl1);
        mButton2 = (Button) findViewById(R.id.changtourl1);


    }
    public void playOrPause(View v){
        if(!curUrl.equals(mTextView.getText().toString())){
            stop();
            curUrl=mTextView.getText().toString();
        }
        // 播放音乐
        if (player == null) {
            player = new MediaPlayer();
            // 重置播放器
            player.reset();
            try {
                player.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        System.out.println("what : " + what);
                        return false;
                    }
                });

                // 设置播放的资源
                player.setDataSource(curUrl);

                // player.prepare();// 准备播放-->阻塞方法

                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 准备完成时的回调

                        player.start();// 开始播放
                    }
                });
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mButton.setText("play");
                    }
                });
                player.prepareAsync();// 异步准备
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            pause();
        }


    }
    public void changeto1(View v){
        mTextView.setText(url1);
    }
    public void changeto2(View v){
        mTextView.setText(url2);
    }
    public void pause() {
        // 暂停
        if (player != null && player.isPlaying()) {
            player.pause();
            mButton.setText("继续");
        } else if (player != null) {
            player.start();
            mButton.setText("暂停");
        }
    }
    public void stop() {
        if (player != null) {
            player.stop();
            player.release();// 释放资源
            player = null;
        }
    }


}
