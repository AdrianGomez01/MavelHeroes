package com.santosgo.mavelheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.santosgo.mavelheroes.databinding.HeroItemBinding

class HeroAdapter(
    private val heroList: MutableList<Hero>,
    private val onClickDelete : (Int) -> Unit,
    private val onClickClone : (Int,Hero) -> Unit,
) : RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {

    class HeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object{
            const val DRAWABLE = "drawable"
            const val CLON = "clon"
        }

        private val binding = HeroItemBinding.bind(view)

        fun bind(hero : Hero, onClickDelete : (Int) -> Unit,onClickClone : (Int,Hero) -> Unit ){
            binding.tvName.text = hero.name
            binding.tvIntelligence.text = hero.intelligence.toString()
            binding.tvPower.text = hero.power.toString()
            val context = itemView.context
            binding.ivPhoto.setImageResource(context.resources.getIdentifier(hero.photo, DRAWABLE, context.packageName))

            binding.root.setOnClickListener{
               // context.resources.getIdentifier("invoke_hero", "string", context.packageName)
                Snackbar.make(it, "Has convocado a ${hero.name}.", Snackbar.LENGTH_SHORT).show()
            }

            binding.ivDelHero.setOnClickListener{
                onClickDelete(adapterPosition)
                Snackbar.make(it, "Has destruido a ${hero.name}.", Snackbar.LENGTH_SHORT).show()
            }

            binding.ivPhoto.setOnClickListener {
                val nombreHeroe = "${hero.name}"
                if (!nombreHeroe.contains("clon")){
                    val clon = hero.copy(name = "$CLON ${hero.name}")
                    onClickClone(adapterPosition,clon)
                    Snackbar.make(it, "Has creado un clon de ${hero.name}.", Snackbar.LENGTH_SHORT).show()
                } else{
                    Snackbar.make(it, "No puedes crear u clon de un clon!!", Snackbar.LENGTH_SHORT).show()
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroViewHolder(layoutInflater.inflate(R.layout.hero_item,parent,false))
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(heroList[position],onClickDelete, onClickClone)
    }

}