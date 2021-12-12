package nz.co.test.transactions.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import nz.co.test.transactions.Constans
import nz.co.test.transactions.R
import nz.co.test.transactions.databinding.ActivityMainBinding
import nz.co.test.transactions.services.Transaction

class MainActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {

    private lateinit var mBinding: ActivityMainBinding
    val mViewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.mViewModel = mViewModel
        mBinding.executePendingBindings()

        var adapter = ListAdapter(arrayOf(), this)
        mBinding.rvTransactions.adapter = adapter
        mViewModel.getTransactions()
        mViewModel.mTransactions.observe(this,
            Observer<Array<Transaction>> {
                it?.let {
                    it.sortByDescending { transaction -> transaction.transactionDate }
                    adapter.replaceData(it)
                }
            })
    }

    override fun onItemClick(transaction: Transaction) {
        var bundle: Bundle = Bundle()
        bundle.putSerializable(Constans.INTENT_EXTRA_NAME, transaction);
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}