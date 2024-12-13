package com.example.shop.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shop.domain.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _products.value = getMockProducts()
    }

    private fun getMockProducts(): List<Product> {
        return listOf(
            Product(1, "iPhone 15", 839.99, "https://cdn.dxomark.com/wp-content/uploads/medias/post-157467/Apple-iPhone-15-Pro_-natural_featured-image-packshot-review.jpg"),
            Product(2, "AirPods Pro 2", 279.00, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/AirPods_Pro_%282nd_generation%29.jpg/1200px-AirPods_Pro_%282nd_generation%29.jpg"),
            Product(3, "MacBook Air 13 (2022)", 763.99, "https://www.backmarket.pt/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D750/https://d2e6ccujb3mkqf.cloudfront.net/97388260-facc-49b3-b1a2-0aabeeb4b919-1_2da2c0e1-05d7-48d8-805b-689a887b7e25.jpg"),
            Product(4, "Apple Watch Series 9 (2023)", 353.99, "https://backmarket.pt/cdn-cgi/image/format%3Dauto%2Cquality%3D75%2Cwidth%3D750/https://d2e6ccujb3mkqf.cloudfront.net/3a610d75-7b7c-498e-84ae-d67afb231cfc-1_548dd3e8-412d-4a92-80e4-19839f34ebdd.jpg")
        )
    }
}
