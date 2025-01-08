package com.example.shop.data.repository

import com.example.shop.domain.model.Product
import com.example.shop.domain.repository.ProductRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val productsCollection: CollectionReference = db.collection("products")

    override suspend fun getProducts(): List<Product> {
        return try {
            val querySnapshot = productsCollection.get().await()

            querySnapshot.documents.mapNotNull { document ->
                try {
                    val product = document.toObject(Product::class.java)?.copy(id = document.id)
                    product
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
