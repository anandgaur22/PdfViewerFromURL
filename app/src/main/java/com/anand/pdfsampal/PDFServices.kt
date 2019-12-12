package com.anand.pdfsampal

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by anand
 */

interface PDFServices {
    @GET
    fun downloadPDF(@Url fileUrl: String): Call<ResponseBody>
}
