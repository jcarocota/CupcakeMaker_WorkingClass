package com.ebc.cupcakemaker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ebc.cupcakemaker.model.CupcakeOrderState


class CupcakeMakerViewModel: ViewModel() {

    //Esta es la variable de estado... va adar seguimiento a los cambios.
    var state by mutableStateOf(CupcakeOrderState())
        private set

    /**
     * Si alguien solicita un cambio al state, esta función se encargará de ejecutar el cambio.
     * Opciones disponibles: flavor, quantity, price, pickupDate, extraInstructions,
     * pickupInstructions
     */
    fun onValue(value:String, textId: String) {
        when (textId) {
            "flavor" -> state = state.copy(flavor = value);
            "quantity" -> {
                state = state.copy(quantity = value.toInt());
                calculateTotalAmount();
            }
            "price" -> {
                state = state.copy(price = value.toDouble());
                calculateTotalAmount();
            }
            "pickupDate" -> state = state.copy(pickupDate = value);
            "extraInstructions" -> state = state.copy(extraInstructions = value);
            "pickupInstructions" -> state = state.copy(pickupInstructions = value);

        }

    }

    /**
     * Función para calcular total de la orden
     */
    private fun calculateTotalAmount() {
        val quantity = state.quantity;
        val price = state.price;
        val total = quantity * price;

        state = state.copy(total = total)
    }

    /**
     * Función para hacer un reset a la orden e iniciar de nuevo
     */
    fun reset() {
        state = CupcakeOrderState()
    }

}