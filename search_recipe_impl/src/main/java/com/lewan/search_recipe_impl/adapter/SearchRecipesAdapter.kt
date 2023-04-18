package com.lewan.search_recipe_impl.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.lewan.search_recipe_impl.Recipe
import com.lewan.search_recipe_impl.databinding.SearchRecipeItemBinding

class SearchRecipesAdapter(
    private val onClickItem: (Recipe) -> Unit,
) :
    ListAdapter<Recipe, SearchRecipesViewHolder>(SearchRecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecipesViewHolder {
        return SearchRecipesViewHolder(
            SearchRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickItem,
        )
    }

    override fun onBindViewHolder(holder: SearchRecipesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}