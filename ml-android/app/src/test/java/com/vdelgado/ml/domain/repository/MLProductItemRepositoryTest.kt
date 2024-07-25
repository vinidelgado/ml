package com.vdelgado.ml.domain.repository
import com.vdelgado.ml.mock.mockMLProductItem
import com.vdelgado.ml.mock.mockMLProductItemResponse
import com.vdelgado.ml.data.remote.MLProductItemRepositoryImpl
import com.vdelgado.ml.data.remote.NoNetworkException
import com.vdelgado.ml.data.remote.common.MLServiceApi
import com.vdelgado.ml.domain.model.MLProductItem
import com.vdelgado.ml.domain.model.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MLProductItemRepositoryTest {
    private val api: MLServiceApi = mockk(relaxed = true)
    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: MLProductItemRepositoryImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init()
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProductDetail should return success result when API call return data`() = runTest {
        coEvery { api.searchProductDetail(any()) } returns mockMLProductItemResponse
        repository = MLProductItemRepositoryImpl(api)
        val result = repository.getProductDetail("MLB123")
        val expected = Result.Success.Content(mockMLProductItem)
        assertEquals(expected,result)
    }

    @Test
    fun `getProductDetail should return failure result when API call throws HttpException`() = runTest {
        val error = HttpException(Response.error<MLProductItem>(404, ResponseBody.create(null, "")))
        coEvery { api.searchProductDetail(any()) } throws error
        repository = MLProductItemRepositoryImpl(api)
        val result = repository.getProductDetail("MLB123")
        val expected = Result.Success.Error(code = 404, message = "Response.error()", body = "")
        assertEquals(expected,result)
    }

    @Test
    fun `getProductDetail should return failure result when API call throws NoNetworkException`() = runTest {
        val error = NoNetworkException("No Network Exception")
        coEvery { api.searchProductDetail(any()) } throws error
        repository = MLProductItemRepositoryImpl(api)
        val result = repository.getProductDetail("MLB123")
        val expected = Result.Failure(error)
        assertEquals(expected,result)
    }


    @Test
    fun `getProductDetail should return failure result when API call throws Exception`() = runTest {
        val error = Exception()
        coEvery { api.searchProductDetail(any()) } throws error
        repository = MLProductItemRepositoryImpl(api)
        val result = repository.getProductDetail("MLB123")
        val expected = Result.Failure(error)
        assertEquals(expected,result)
    }


}