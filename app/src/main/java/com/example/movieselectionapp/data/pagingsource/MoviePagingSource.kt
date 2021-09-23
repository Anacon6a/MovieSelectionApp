package com.example.movieselectionapp.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieselectionapp.data.mapper.mapMovie
import com.example.movieselectionapp.data.model.MovieModel
import com.example.movieselectionapp.data.remote.api.RestApi

class MoviePagingSource(
    private val restApi: RestApi
): PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val currentPage = params.key ?: 0
            val result = restApi.getMovies(currentPage)

            val movies = if (result.isSuccessful) {
                result.body()?.results?.let {
                    mapMovie(it)
                }?: run {
                    emptyList()
                }
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 0) null else currentPage,
                nextKey = if (movies.isEmpty()) null else currentPage + 20
            )
        } catch(e: Exception){
            LoadResult.Error(e)
        }
    }

}