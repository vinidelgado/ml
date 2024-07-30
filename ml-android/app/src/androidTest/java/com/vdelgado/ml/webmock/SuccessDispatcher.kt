package com.vdelgado.ml.webmock


import android.content.Context
import android.net.Uri
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import com.vdelgado.ml.webmock.AssetReaderUtil.asset

const val SEARCH_PRODUCT = "/sites/MLB/search"
const val ITEMS = "/items/10"
const val SEARCH_PRODUCT_SUCCESS = "search_success_response.json"
const val ITEMS_SUCCESS = "launches_success.json"

class SuccessDispatcher(
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {
    private val responseFilesByPath: Map<String, String> = mapOf(
        SEARCH_PRODUCT to SEARCH_PRODUCT_SUCCESS,
        ITEMS to ITEMS_SUCCESS
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val pathWithoutQueryParams = Uri.parse(request.path).path ?: return errorResponse
        val responseFile = responseFilesByPath[pathWithoutQueryParams]

        return if (responseFile != null) {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            throw Throwable("Uri.parse(request.path).path null")
            errorResponse
        }
    }
}