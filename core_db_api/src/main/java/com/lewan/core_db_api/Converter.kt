package com.lewan.core_db_api

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lewan.core_db_api.entity.DescriptionRecipeEntity
import java.lang.reflect.Type

object Converter {

    @TypeConverter
    fun toIngredient(str: String?): List<DescriptionRecipeEntity.Ingredient> {

        val listType: Type = object : TypeToken<List<DescriptionRecipeEntity.Ingredient>>() {}.type
        return Gson().fromJson(str, listType)
    }

    @TypeConverter
    fun fromIngredient(ingredients: List<DescriptionRecipeEntity.Ingredient>): String {
        return Gson().toJson(ingredients)
    }
}