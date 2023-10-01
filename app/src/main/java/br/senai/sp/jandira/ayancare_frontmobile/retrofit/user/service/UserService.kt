package br.senai.sp.jandira.ayancare_frontmobile.retrofit.user.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    //https://ayancare-api.cyclic.cloud

    @Headers("Content-Type: application/json")
    @POST("/usuario/cadastro")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/ayan/usuario/autenticar")
    suspend fun postUser(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/usuario/esqueceu_a_senha")
    suspend fun requestPasswordReset(@Body requestBody: JsonObject): Response<JsonObject>
}