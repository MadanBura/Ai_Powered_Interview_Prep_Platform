package com.interview.platform.data.audio

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import com.interview.platform.domain.audio.AudioPlayer
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
) : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File, onCompletion: (() -> Unit)?) {
        try {
            MediaPlayer.create(context, file.toUri()).apply {
                player = this
                setOnCompletionListener {
                    onCompletion?.invoke()
                }
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun stop() {
        try {
            player?.stop()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        player?.release()
        player = null
    }

    override fun pause() {
        try {
            player?.pause()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun resume() {
        try {
            player?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getCurrentPosition(): Int {
        return player?.currentPosition ?: 0
    }

    override fun getDuration(): Int {
        return player?.duration ?: 0
    }

    override fun isPlaying(): Boolean {
        return player?.isPlaying ?: false
    }
}
