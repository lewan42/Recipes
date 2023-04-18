package com.lewan.search_recipe_impl.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lewan.search_recipe_impl.R
import com.lewan.search_recipe_impl.Recipe
import com.lewan.search_recipe_impl.databinding.SearchRecipeItemBinding

class SearchRecipesViewHolder(
    private val binding: SearchRecipeItemBinding,
    private val onClickItem: (Recipe) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(48))

    fun bind(recipe: Recipe) = binding.run {

        nameRecipe.text = recipe.name
        Glide
            .with(itemView.context)
            .load(recipe.image)
            .placeholder(R.drawable.dish)
            .apply(requestOptions)
            .into(imageRecipe)

        itemView.setOnClickListener {
            onClickItem.invoke(recipe)
        }
    }
}