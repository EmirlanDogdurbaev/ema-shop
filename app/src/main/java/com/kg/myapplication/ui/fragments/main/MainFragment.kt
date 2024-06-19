package com.kg.myapplication.ui.fragments.main

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kg.myapplication.R
import com.kg.myapplication.data.entity.ShopItems
import com.kg.myapplication.databinding.FragmentMainBinding
import java.net.URLEncoder


class MainFragment : Fragment(R.layout.fragment_main), OrderAdapter.ItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    private val orderAdapter = OrderAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    private fun initAdapter() {
        binding.orderRV.adapter = orderAdapter
        binding.orderRV.layoutManager =
            GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        fakeData()
    }

    private fun fakeData() {
        val data = ArrayList<ShopItems>()

        data.add(0, ShopItems(id = 1, image = getString(R.string.water), name = "Water", price = "20"))
        data.add(0, ShopItems(id = 2, image = getString(R.string.burger), name = "Burger", price = "220"))
        data.add(0, ShopItems(id = 3, image = getString(R.string.bread), name = "Bread", price = "30"))
        data.add(0, ShopItems(id = 4, image = getString(R.string.bread), name = "Bread", price = "240"))
        data.add(0, ShopItems(id= 5, image = getString(R.string.burger), name = "Burger", price = "40"))
        data.add(0, ShopItems(id = 6, image = getString(R.string.water), name = "Water", price = "50"))
        data.add(0, ShopItems(id = 7, image = getString(R.string.bread), name = "Bread", price = "30"))
        data.add(0, ShopItems(id = 8, image = getString(R.string.bread), name = "Bread", price = "240"))
        data.add(0, ShopItems(id= 9, image = getString(R.string.burger), name = "Burger", price = "40"))
        data.add(0, ShopItems(id = 10, image = getString(R.string.water), name = "Water", price = "50"))


        orderAdapter.update(data)
    }

    override fun onItemClick(name: String, price: String) {
        val alertDialog: AlertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle("Внимание!")
        alertDialog.setMessage("Сейчас вы заказываете $name за $price!")
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Согласен") { dialog, which -> sendToWA(name, price) }
        alertDialog.show()
    }

    private fun sendToWA(name: String, price: String) {
        var textMassage = "Здравствуйте, хочу заказать у вас товары: $name за $price."

        val url = "https://api.whatsapp.com/send?phone=$+996500023120"+"&text=" + URLEncoder.encode(textMassage, "UTF-8")
        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(url))
        startActivity(i)
    }
}