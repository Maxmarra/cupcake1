package com.example.cupcake1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake1.databinding.FragmentStartBinding
import com.example.cupcake1.model.OrderViewModel

class StartFragment : Fragment() {

    private val sharedViewModel: OrderViewModel by activityViewModels()

    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(
            inflater, container, false
        )
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Set up the button click listeners
            orderOneCupcake.setOnClickListener { orderCupcake(1) }
            orderSixCupcakes.setOnClickListener { orderCupcake(6) }
            orderTwelveCupcakes.setOnClickListener { orderCupcake(12) }
        }
    }

    fun orderCupcake(quantity: Int) {
        sharedViewModel.setQuantity(quantity)
        if(sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
//        Toast.makeText(activity,
//            "Заказ -> ${resources.getQuantityString(
//                R.plurals.cupcakes, quantity, quantity)} ",
//            Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}