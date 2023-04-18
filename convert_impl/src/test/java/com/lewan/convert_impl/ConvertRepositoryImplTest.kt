package com.lewan.convert_impl

import com.lewan.convert_impl.repo.ConvertRepositoryImpl
import com.lewan.convert_impl.repo.ConvertService
import com.lewan.core_api.Result
import com.lewan.network_api.ApiKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertRepositoryImplTest {

    private val convertService: ConvertService = mock()
    private val apiKey: ApiKey = mock()
    private val repository = ConvertRepositoryImpl(convertService, apiKey)

    @Test
    fun `testing answer`() = runTest {

        val responseWithAnswer = ConvertDto("20 cups water is 20000 grams", null)

        whenever(apiKey.getApiKey()).thenReturn("")
        whenever(convertService.convert(any(), any(), any(), any(), any()))
            .thenReturn(responseWithAnswer)

        val result = repository.convert("water", 20.0, "cups", "grams")
        assertEquals(Result.Success(ConvertData("20 cups water is 20000 grams")), result)
    }

    @Test
    fun `testing error`() = runTest {

        val responseWithError = ConvertDto(null, "failure")

        whenever(apiKey.getApiKey()).thenReturn("")
        whenever(convertService.convert(any(), any(), any(), any(), any()))
            .thenReturn(responseWithError)

        val result = repository.convert("asdfbgh", 20.0, "asdfgfh", "asfsdg")
        assertEquals(Result.Error("failure"), result)
    }
}