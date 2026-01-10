package efecun.dev.yemektarifleriuygulamasi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import efecun.dev.yemektarifleriuygulamasi.R
import efecun.dev.yemektarifleriuygulamasi.databinding.RecyclerRowBinding
import efecun.dev.yemektarifleriuygulamasi.model.Tarif

class TarifAdapter(private val tarifList: List<Tarif>) : RecyclerView.Adapter<TarifAdapter.TarifHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifHolder {
        val binding = RecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TarifHolder(binding)
    }

    override fun onBindViewHolder(holder: TarifHolder, position: Int) {
        val tarif = tarifList[position]
        holder.binding.recyclerViewTextView.text = tarif.isim

        holder.itemView.setOnClickListener { view ->
            val bundle = bundleOf(
                "bilgi" to "eski",
                "id" to tarif.id
            )
            Navigation.findNavController(view)
                .navigate(R.id.action_listeFragment_to_tarifFragment, bundle)
        }
    }

    override fun getItemCount(): Int = tarifList.size

    class TarifHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
}
