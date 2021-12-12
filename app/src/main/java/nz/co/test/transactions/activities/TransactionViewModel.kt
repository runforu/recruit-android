package nz.co.test.transactions.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nz.co.test.transactions.services.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.di.DaggerAppComponentFactory

class TransactionViewModel: ViewModel() {
    val mTransactions = MutableLiveData<Array<Transaction>>()

    @Inject
    lateinit var mService: TransactionsService

    init {
        DaggerAppComponentFactory.mComponent.inject(this)
    }

    fun getTransactions() {
        viewModelScope.launch {
            val res = mService.retrieveTransactions()
            mTransactions.postValue(res);
        }
    }
}