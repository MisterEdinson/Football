package com.example.football.ui.team

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.data.host.team.SquadItem
import kotlinx.android.synthetic.main.item_team_player.view.*

class TeamPlayerAdapter:RecyclerView.Adapter<TeamPlayerAdapter.PlayerHolder>() {
    class PlayerHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team_player,parent, false)
        return PlayerHolder(view)
    }

    private val callback = object : DiffUtil.ItemCallback<SquadItem>() {
        override fun areItemsTheSame(
            oldItem: SquadItem,
            newItem: SquadItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SquadItem,
            newItem: SquadItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val item = differ.currentList[position]
        holder.itemView.apply {
            if(item.position == "Goalkeeper"){
                tvPlayerPosition.text = "ВР"
            }else if(item.position == "Defence"){
                tvPlayerPosition.text = "ЗЩ"
            }else if(item.position == "Midfield"){
                tvPlayerPosition.text = "ПЗ"
            }else {
                tvPlayerPosition.text = "НП"
            }
            tvPlayerName.text = item.name
            tvPlayerCountry.text = item.nationality
            tvPlayerYears.text = item.dateOfBirth
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}