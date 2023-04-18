package com.lewan.convert_impl.repo

import com.lewan.convert_impl.ConvertData
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.network_api.ApiKey
import kotlinx.coroutines.delay
import javax.inject.Inject

class ConvertRepositoryImpl @Inject constructor(
    private val convertService: ConvertService,
    private val apiKey: ApiKey,
) : ConvertRepository {

    override suspend fun convert(
        ingredientName: String,
        sourceAmount: Double,
        sourceUnit: String,
        targetUnit: String
    ): Result<IResult> {

        return try {
            val convertDto = convertService.convert(
                apiKey.getApiKey(),
                ingredientName,
                sourceAmount,
                sourceUnit,
                targetUnit
            )

            delay(3000)

            convertDto.answer?.let { Result.Success(ConvertData(it)) }
                ?: convertDto.status?.let { Result.Error(it) }
                ?: Result.Error("")

        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.localizedMessage)
        }
    }
}