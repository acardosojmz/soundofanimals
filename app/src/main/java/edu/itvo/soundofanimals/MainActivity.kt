package edu.itvo.soundofanimals

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.itvo.soundofanimals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),  CellClickListener {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var mediaplayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.configGridLayout(3)

        val animalsAndSounds = ArrayList<ImageSoundModel>()
        animalsAndSounds.add(ImageSoundModel(R.drawable.coyote,R.raw.coyotes))
        animalsAndSounds.add(ImageSoundModel(R.drawable.donkey,R.raw.donkey))
        animalsAndSounds.add(ImageSoundModel(R.drawable.crickets,R.raw.crickets))
        animalsAndSounds.add(ImageSoundModel(R.drawable.frogs,R.raw.frogs))

        val adapter = ImageAdapter(animalsAndSounds, this)

        binding.recyclerView.adapter = adapter
    }
    private fun configGridLayout(cols: Int=3){
        GridLayoutManager(
            this, // context
            cols, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            binding.recyclerView.layoutManager = this
        }
    }
    override fun onCellClickListener(imageSoundModel: ImageSoundModel) {
        stopIfRunning()
        mediaplayer = MediaPlayer.create(this,imageSoundModel.sound)
        mediaplayer.start()
    }

    private  fun stopIfRunning(){
        if (this::mediaplayer.isInitialized && mediaplayer.isPlaying){
            mediaplayer.stop()
        }
    }
    override fun onStop() {
        super.onStop()
        this.stopIfRunning()
    }

}