package com.swcode.imagebackgroundtesting


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swcode.imagebackgroundtesting.databinding.ItemCharacterDetailsBinding

class ItemAdapter(private val items: ArrayList<CharacterEntity>,
                  private val deleteListener: (id: Int)->Unit,
                private val onCharacterClickListener: OnCharacterClickListener)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCharacterDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        val llMain = binding.llMain
        val tvItemName = binding.tvItemName
        val tvItemRace = binding.tvItemRace
        val ivDelete = binding.ivDelChar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

        holder.tvItemName.text = item.characterName + ", "
        holder.tvItemRace.text = item.characterRace

        if (position % 2 == 0) {
            holder.llMain.setBackgroundColor(Color.parseColor("#EBEBEB"))
        } else {
            holder.llMain.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        holder.ivDelete.setOnClickListener {
            deleteListener.invoke(item.id)
        }

        holder.itemView.setOnClickListener {
            onCharacterClickListener.onCharacterItemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}