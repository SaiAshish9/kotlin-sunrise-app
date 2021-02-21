```
// Other imported classes
import retrofit2.Callback

class RepositoryRetriever {
  private val service: GithubService

  companion object {
    //1
    const val BASE_URL = "https://api.github.com/"
  }

  init {
    // 2
    val retrofit = Retrofit.Builder()
         // 1
        .baseUrl(BASE_URL)
         //3
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //4
    service = retrofit.create(GithubService::class.java)
  }

  fun getRepositories(callback: Callback<RepoResult>) { //5
    val call = service.searchRepositories()
    call.enqueue(callback)
  }
}

```