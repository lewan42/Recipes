package com.lewan.convert_impl

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.lewan.convert_impl.repo.ConvertRepository
import com.lewan.convert_impl.vm.ConvertViewModel
import com.lewan.core_api.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@Config(
    instrumentedPackages = ["androidx.loader.content"]
)
class ConvertViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private val repository: ConvertRepository = mock()

    @Test
    fun `testing screen state`() = runTest {

        val viewModel = ConvertViewModel(repository)

        val resultWithAnswer = Result.Success(ConvertData(answer = "water is ..."))
        val resultWithError = Result.Error("failure")

        whenever(repository.convert("water", 20.0,"cups","grams")).thenReturn(resultWithAnswer)
        whenever(repository.convert("", 20.0,"cups","grams")).thenReturn(resultWithError)


        viewModel.stateScreen.test {
            assertEquals(Result.Starting, awaitItem())
            viewModel.calculate("water", 20.0,"cups","grams")
            assertEquals(Result.Loading, awaitItem())
            assertEquals(resultWithAnswer, awaitItem())

            viewModel.calculate("", 20.0,"cups","grams")
            assertEquals(Result.Loading, awaitItem())
            assertEquals(resultWithError, awaitItem())

            viewModel.calculate("water", 20.0,"cups","grams")
            assertEquals(Result.Loading, awaitItem())
            assertEquals(resultWithAnswer, awaitItem())
        }
    }
}