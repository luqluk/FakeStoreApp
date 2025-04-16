package com.android.core.di.networkmodule

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Fungsi untuk menyediakan instance GsonConverterFactory.
 * GsonConverterFactory digunakan oleh Retrofit untuk mengonversi data JSON menjadi objek Kotlin dan sebaliknya.
 */
fun provideBaseGsonFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

/**
 * Fungsi untuk menyediakan instance Gson.
 * Gson digunakan untuk mengonversi data JSON menjadi objek Kotlin dan sebaliknya.
 * Fungsi ini menggunakan GsonBuilder untuk membuat konfigurasi tambahan, seperti:
 * - `setLenient()`: Memungkinkan parser JSON untuk lebih toleran terhadap format JSON yang tidak sempurna.
 */
fun provideGsonBuilder(): Gson = GsonBuilder()
    .setLenient() /* Mengatur parser JSON agar toleran terhadap format yang tidak standar */
    .create() /* Membuat instance Gson */

/**
 * Fungsi untuk menyediakan instance OkHttpClient.
 * OkHttpClient adalah klien HTTP yang digunakan oleh Retrofit untuk menangani permintaan HTTP.
 * Konfigurasi yang digunakan dalam fungsi ini:
 * - `readTimeout`: Waktu maksimal untuk membaca data dari server.
 * - `connectTimeout`: Waktu maksimal untuk menghubungkan ke server.
 * - `addInterceptor`: Menambahkan interceptor kustom untuk menangani permintaan dan respons.
 * - `ChuckerInterceptor`: Library untuk memantau dan menganalisis permintaan HTTP, berguna untuk debugging.
 *
 * @param interceptor Interceptor kustom untuk memproses permintaan.
 * @param context Context aplikasi, dibutuhkan oleh ChuckerInterceptor.
 * @return OkHttpClient dengan konfigurasi yang telah ditentukan.
 */
fun provideBaseClient(interceptor: Interceptor, context: Context): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS) /* Waktu baca maksimal */
        .connectTimeout(15, TimeUnit.SECONDS) /* Waktu koneksi maksimal */
        .addInterceptor(interceptor) /* Menambahkan interceptor kustom */
        .addInterceptor(ChuckerInterceptor.Builder(context).build()) /* Menambahkan Chucker untuk debugging HTTP */
        .build()

/**
 * Fungsi untuk menyediakan instance Retrofit.
 * Retrofit adalah library untuk melakukan permintaan HTTP dan mempermudah parsing JSON.
 * Konfigurasi yang digunakan dalam fungsi ini:
 * - `baseUrl`: URL dasar untuk semua endpoint API.
 * - `addConverterFactory`: Menambahkan GsonConverterFactory untuk parsing JSON otomatis.
 * - `client`: Instance OkHttpClient yang sudah dikonfigurasi.
 *
 * @param gson Instance GsonConverterFactory untuk parsing JSON otomatis.
 * @param gsonBuilder Instance Gson untuk manipulasi JSON tambahan jika diperlukan.
 * @param client Instance OkHttpClient untuk menangani koneksi HTTP.
 * @param baseURL URL dasar untuk API.
 * @return Retrofit dengan konfigurasi yang telah ditentukan.
 */
fun provideBaseRetrofit(
    gson: GsonConverterFactory,
    gsonBuilder: Gson,
    client: OkHttpClient,
    baseURL: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseURL) /* URL dasar untuk API */
    .addConverterFactory(GsonConverterFactory.create(gsonBuilder)) /* Menambahkan konverter JSON dengan konfigurasi Gson */
    .client(client) /* Menambahkan OkHttpClient */
    .build()

/**
 * Fungsi untuk menyediakan instance Interceptor.
 * Interceptor digunakan untuk memanipulasi atau memantau permintaan dan respons HTTP yang melewati OkHttpClient.
 * Dalam fungsi ini:
 * - URL permintaan (request URL) diambil sebagai log atau untuk kebutuhan lain.
 * - Permintaan (request) dapat dimodifikasi menggunakan `newBuilder` jika diperlukan.
 * - Respons (response) diteruskan setelah permintaan diproses.
 *
 * @return Instance Interceptor untuk digunakan di OkHttpClient.
 */
fun provideBaseInterceptor() =
    Interceptor { chain ->
        val request = chain
            .request()
            .newBuilder()
            .build() /* Buat ulang permintaan jika perlu ditambahkan header atau properti lain */
        val response = chain.proceed(request) /* Kirim permintaan dan terima respons dari server */
        response /* Kembalikan respons yang diterima */
    }



