package com.example.citysearchapp.ui.city


import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citysearchapp.base.BaseFragment
import com.example.citysearchapp.data.entity.City
import com.example.citysearchapp.databinding.FragmentCityBinding
import com.example.citysearchapp.ui.city.interfaces.IOnItemClickListener
import com.example.citysearchapp.ui.event.CityEvent
import com.example.citysearchapp.utils.navigate
import kotlinx.android.synthetic.main.fragment_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * @Author: Akash Abhishek
 * @Date: 19 May 2022
 * Fragment shows the list of city over google maps
 */

class CityFragment : BaseFragment<FragmentCityBinding, CityViewModel>(), IOnItemClickListener {

    override val viewModel: CityViewModel by viewModel()

    lateinit var cityAdapter: CityAdapter

    override fun initUI() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        cityAdapter = CityAdapter()
        cityAdapter.onItemClickListener = this
        recycler_view.adapter = cityAdapter

        addSearchListener()
    }

    private fun addSearchListener() {
        edit_text_search.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().lowercase(Locale.getDefault())
            cityAdapter.clearItems()
            viewModel.event(CityEvent.Search(query))
        }
    }

    override fun loadData() {
        viewModel.onStart()
    }

    override fun observeData() {
        viewModel.state.observe(viewLifecycleOwner) {
            binding.apply {
                item = it
            }
            cityAdapter.updateItems(it.listCity)
        }
    }

    override fun getViewBinding(): FragmentCityBinding = FragmentCityBinding.inflate(layoutInflater)
    override fun onItemClick(item: City) {
        val action =
            CityFragmentDirections.actionClick(item.coordinate, item.name)
        navigate(action)
    }
}