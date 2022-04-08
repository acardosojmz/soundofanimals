package edu.itvo.soundofanimals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.itvo.soundofanimals.databinding.ItemBinding

class ImageAdapter(private val listImageSound: List<ImageSoundModel>,
                   private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        with(holder){
            with(listImageSound[position]){
                binding.imageView.setImageResource(this.image)
                binding.imageView.tag = this.sound

                binding.imageView.setOnClickListener{
                    cellClickListener.onCellClickListener(this)
                }
            }
        }

    }


    override fun getItemCount(): Int {
        return listImageSound.size
    }

}