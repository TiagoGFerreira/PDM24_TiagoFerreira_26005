package com.example.shop.data.repository

import android.util.Log
import com.example.shop.domain.model.Product
import com.example.shop.domain.repository.ProductRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val productsCollection: CollectionReference = db.collection("products")

    companion object {
        private const val TAG = "ProductRepositoryImpl"
    }

    override suspend fun getProducts(): List<Product> {
        Log.d(TAG, "Fetching products from Firestore...")

        return try {
            val querySnapshot = productsCollection.get().await()
            Log.d(TAG, "Successfully fetched ${querySnapshot.documents.size} products.")

            querySnapshot.documents.mapNotNull { document ->
                try {
                    val product = document.toObject(Product::class.java)?.copy(id = document.id)
                    Log.d(TAG, "Mapped product: $product")
                    product
                } catch (e: Exception) {
                    Log.e(TAG, "Error mapping document to Product: ${document.id}", e)
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching products from Firestore.", e)
            emptyList()
        }
    }
}
