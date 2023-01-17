package com.example.livecastpoc.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.mediarouter.app.MediaRouteButton
import com.example.livecastpoc.R
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.common.images.WebImage

class MainFragment : Fragment() {
    private var castContext: CastContext? = null

    private var image: String? = "https://placekitten.com/100/100"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mMediaRouteButton = view?.findViewById<View>(R.id.media_route_button) as MediaRouteButton
        CastButtonFactory.setUpMediaRouteButton(requireContext(), mMediaRouteButton)
        castContext = CastContext.getSharedInstance()

        val textView = view?.findViewById<View>(R.id.image_text) as TextView
        textView.text = image

        val startButton = view?.findViewById<View>(R.id.start_button) as Button
        startButton.setOnClickListener {
            stopStream()
            startStream()
        }

        val stopButton = view?.findViewById<View>(R.id.stop_button) as Button
        stopButton.setOnClickListener {
            stopStream()
        }

        val asset1Button = view?.findViewById<View>(R.id.asset_button1) as Button
        asset1Button.setOnClickListener {
            image = "https://placekitten.com/100/100"
            textView.text = image
        }

        val asset2Button = view?.findViewById<View>(R.id.asset_button2) as Button
        asset2Button.setOnClickListener {
            image = "https://placekitten.com/g/100/100"
            textView.text = image
        }

        val asset3Button = view?.findViewById<View>(R.id.asset_button3) as Button
        asset3Button.setOnClickListener {
            image = null
            textView.text = ""
        }
    }

    private fun startStream() {
        getMediaClient()?.run {
            val movieMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE)

            movieMetadata.putString(MediaMetadata.KEY_TITLE, "Live Test Stream")
            movieMetadata.putString(MediaMetadata.KEY_SUBTITLE, "Test subtitle")
            if (image != null) {
                movieMetadata.addImage(WebImage(Uri.parse(image)))
            }

            val mediaInfo = MediaInfo.Builder("https://livesim.dashif.org/livesim/testpic_2s/Manifest.mpd")
                .setStreamType(MediaInfo.STREAM_TYPE_LIVE)
                .setContentType("live")
                .setStreamDuration(0L)
                .setMetadata(movieMetadata)
                .build()
            load(MediaLoadRequestData.Builder().setMediaInfo(mediaInfo).build())
        }
    }

    private fun stopStream() {
        getMediaClient()?.run {
            stop()
        }
    }

    private fun getMediaClient() = castContext?.sessionManager?.currentCastSession?.remoteMediaClient

    companion object {
        fun newInstance() = MainFragment()
    }
}