package nz.co.test.transactions.di.network

import android.util.Log
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import nz.co.test.transactions.BuildConfig
import nz.co.test.transactions.di.DaggerAppComponent
import nz.co.test.transactions.di.DaggerAppComponentFactory
import nz.co.test.transactions.services.TransactionsService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Module
class NetworkModule {
    object BigDecimalAdapter {
        @FromJson
        fun fromJson(string: String) = BigDecimal(string)

        @ToJson
        fun toJson(value: BigDecimal) = value.toString()
    }
    object OffsetDateTimeAdapter{
        @ToJson
        fun toJson(value: OffsetDateTime): String {
            return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value)
        }

        @FromJson
        fun fromJson(value: String): OffsetDateTime {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }
    }

    @Provides
    fun providesTransactionService(): TransactionsService {
        var moshi = Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(OffsetDateTimeAdapter)
            .build();
        return Retrofit.Builder()
            //.baseUrl(BuildConfig.BASE_URL)
            .baseUrl("https://60220907ae8f8700177dee68.mockapi.io/api/v1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TransactionsService::class.java)
    }
}