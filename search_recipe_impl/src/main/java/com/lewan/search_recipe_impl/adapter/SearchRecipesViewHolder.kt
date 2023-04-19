package com.lewan.search_recipe_impl.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
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

    private val bitmapDrawable: RoundedBitmapDrawable

    init {
        val placeholder: Bitmap =
            BitmapFactory.decodeResource(itemView.context.resources, R.drawable.dish)
        bitmapDrawable =
            RoundedBitmapDrawableFactory.create(itemView.context.resources, placeholder)
        bitmapDrawable.cornerRadius = 48f
    }

    private val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(48))

    fun bind(recipe: Recipe) = binding.run {

        nameRecipe.text = recipe.name
        Glide
            .with(itemView.context)
            .load(recipe.image)
            .placeholder(bitmapDrawable)
            .apply(requestOptions)
            .into(imageRecipe)

        itemView.setOnClickListener {
            onClickItem.invoke(recipe)
        }
    }
}