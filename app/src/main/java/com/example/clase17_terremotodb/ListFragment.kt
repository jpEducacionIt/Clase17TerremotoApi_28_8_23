package com.example.clase17_terremotodb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TerremotoAdapter
    private var listQuakes: MutableList<Terremoto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myView = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = myView.findViewById(R.id.recyclerView)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = TerremotoAdapter()
        recyclerView.adapter = adapter

        getTerremoto()

        adapter.onItemClickListener = { terremoto ->
            navigateToDetail(terremoto)
        }
    }

    private fun navigateToDetail(terremoto: Terremoto) {
        val bundle = Bundle()
        bundle.putParcelable("terremoto",terremoto)
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle
        fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, detailFragment)?.commit()
    }

    private fun getTerremoto() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRefrofit().create(ApiService::class.java).getAllQuakesByWeek()
            val response = call.body()

            activity?.runOnUiThread {
                listQuakes.clear()
                if (call.isSuccessful) {
                    listQuakes = (response?.features?.map { feature ->
                        feature.toTerremoto()
                    } ?: emptyList()) as MutableList<Terremoto>

                    adapter.submitList(listQuakes)
                } else {
                    Toast.makeText(activity, "falla en la llamada a retrofit", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getRefrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}