package efecun.dev.yemektarifleriuygulamasi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import efecun.dev.yemektarifleriuygulamasi.R
import efecun.dev.yemektarifleriuygulamasi.adapter.TarifAdapter
import efecun.dev.yemektarifleriuygulamasi.databinding.FragmentListeBinding
import efecun.dev.yemektarifleriuygulamasi.model.Tarif
import efecun.dev.yemektarifleriuygulamasi.roomdb.TarifDAO
import efecun.dev.yemektarifleriuygulamasi.roomdb.TarifDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListeFragment : Fragment() {

    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!

    private val mDisposable = CompositeDisposable()
    private lateinit var db: TarifDatabase
    private lateinit var tarifDao: TarifDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(requireContext(), TarifDatabase::class.java, "Tarifler")
            .build()

        tarifDao = db.tarifDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yemekRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.floatingActionButton.setOnClickListener { addNew(it) }

        verileriAl()
    }

    private fun verileriAl() {
        mDisposable.add(
            tarifDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::handleResponse)
        )
    }

    private fun handleResponse(tarifler: List<Tarif>) {
        binding.yemekRecyclerView.adapter = TarifAdapter(tarifler)
    }

    fun addNew(view: View) {
        val bundle = bundleOf(
            "bilgi" to "yeni",
            "id" to -1
        )
        Navigation.findNavController(view)
            .navigate(R.id.action_listeFragment_to_tarifFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mDisposable.clear()
    }
}
