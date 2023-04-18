package com.lewan.description_recipe_impl.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lewan.description_recipe_impl.DescriptionRecipe
import com.lewan.description_recipe_impl.databinding.IngredientItemBinding

class IngredientAdapter :
    ListAdapter<DescriptionRecipe.Ingredient, IngredientAdapter.IngredientViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(
            IngredientItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class IngredientViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(ingredient: DescriptionRecipe.Ingredient) = binding.run {
            ingredientName.text = ingredient.name
            ingredientAmount.text = "${ingredient.amount} ${ingredient.unit}"
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<DescriptionRecipe.Ingredient>() {
            override fun areItemsTheSame(
                oldItem: DescriptionRecipe.Ingredient,
                newItem: DescriptionRecipe.Ingredient
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DescriptionRecipe.Ingredient,
                newItem: DescriptionRecipe.Ingredient
            ): Boolean =
                oldItem == newItem
        }
    }
}