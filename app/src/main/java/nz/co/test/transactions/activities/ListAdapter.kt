package nz.co.test.transactions.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nz.co.test.transactions.databinding.RecyclerViewItemBinding
import nz.co.test.transactions.services.Transaction

class ListAdapter(
    private var items: Array<Transaction>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(transaction: Transaction)
    }

    fun replaceData(array: Array<Transaction>) {
        items = array
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction, listener: OnItemClickListener?) {
            binding.mTransaction = transaction
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(transaction) })
            }
            binding.executePendingBindings()
        }
    }

}

