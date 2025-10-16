package com.example.lab8.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.lab8.data.local.Lab8Database
import com.example.lab8.data.local.entity.PhotoEntity
import com.example.lab8.data.remote.Lab8Api
import com.example.lab8.data.mapper.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class Lab8RemoteMediator(
    private val query: String,
    private val api: Lab8Api,
    private val db: Lab8Database
) : RemoteMediator<Int, PhotoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        // Si no hay ítem, significa que no hay más páginas o estamos al inicio
                        return MediatorResult.Success(endOfPaginationReached = false)
                    }
                    lastItem.page + 1
                }
            }

            // Aquí usamos el método de API correctamente: query de tipo String, page de tipo Int
            val response = api.searchPhotos(
                query = query,
                page = page,
                perPage = state.config.pageSize
            )

            val photoEntities = response.results.map { dto ->
                // Convertimos DTO a entidad con los parámetros adecuados
                dto.toEntity(queryKey = query, page = page)
            }

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.photoDao().clearAll()
                }
                db.photoDao().insertAll(photoEntities)
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
