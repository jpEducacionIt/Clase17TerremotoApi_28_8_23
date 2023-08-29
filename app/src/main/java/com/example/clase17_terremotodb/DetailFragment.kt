package com.example.clase17_terremotodb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetailFragment : Fragment() {
    private lateinit var terremoto: Terremoto
    private lateinit var textViewDetailPlace: TextView
    private lateinit var textViewDetailMag: TextView
    private lateinit var textViewDetailId: TextView
    private lateinit var textViewDetailTime: TextView
    private lateinit var textViewDetailLong: TextView
    private lateinit var textViewDetaillat: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            terremoto = bundle.get("terremoto") as Terremoto
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.fragment_detail, container, false)
        textViewDetailPlace = myView.findViewById(R.id.textViewDetailPlace)
        textViewDetailMag = myView.findViewById(R.id.textViewDetailMag)
        textViewDetailId = myView.findViewById(R.id.textViewDetailId)
        textViewDetailTime = myView.findViewById(R.id.textViewDetailTime)
        textViewDetailLong = myView.findViewById(R.id.textViewLong)
        textViewDetaillat = myView.findViewById(R.id.textViewlat)
        button = myView.findViewById(R.id.button)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewDetailPlace.text = terremoto.place
        textViewDetailMag.text = terremoto.magnitude
        textViewDetailId.text = terremoto.id
        textViewDetailTime.text = terremoto.time
        textViewDetailLong.text = terremoto.long
        textViewDetaillat.text = terremoto.lat
    }
}