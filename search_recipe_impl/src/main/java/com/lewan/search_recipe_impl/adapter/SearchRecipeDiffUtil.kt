package com.lewan.search_recipe_impl.adapter

import androidx.recyclerview.widget.DiffUtil
import com.lewan.search_recipe_impl.Recipe

class SearchRecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean = oldItem == newItem
}