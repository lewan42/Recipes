package com.lewan.convert_impl.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lewan.convert_impl.repo.ConvertRepository
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConvertViewModel @Inject constructor(
    private val repository: ConvertRepository
) : ViewModel() {

    private val _stateScreen: MutableStateFlow<Result<IResult>> = MutableStateFlow(Result.Starting)
    val stateScreen: StateFlow<Result<IResult>> = _stateScreen

    fun calculate(
        ingredientName: String,
        sourceAmount: Double,
        sourceUnit: String,
        targetUnit: String,
    ) {
        _stateScreen.value = Result.Loading
        viewModelScope.launch {
            _stateScreen.emit(
                repository.convert(
                    ingredientName,
                    sourceAmount,
                    sourceUnit,
                    targetUnit
                )
            )
        }
    }
}