import { useEffect, useState } from 'react'
import { getMovies, getPriceByMovieId } from '~/api'
import Card from '~common/Card'
import Loader from '~common/Loader'
import Container from '~common/Container'

export function Component() {
  const [movies, setMovies] = useState([])
  const [movieVendors, setMovieVendors] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    initData(setMovies, setMovieVendors).then(() => setLoading(false))
  }, [])

  return (
    <Container as="main">
      <p className="text-xl font-semibold text-indigo-400">Movies & TV Shows</p>
      <h1 className="mt-2 text-5xl font-bold"> Recently Released </h1>

      {loading ? (
        <div className="mt-16">
          <Loader className="mx-auto w-6" />
        </div>
      ) : (
        <div className="mt-16 grid sm:grid-cols-2 gap-6 sm:gap-8">
          {movies.map((movie, i) => (
            <Card key={movie.id} movie={movie} movieVendor={movieVendors[i]} />
          ))}
        </div>
      )}
    </Container>
  )
}

Component.displayName = 'Home'

async function initData(setMovies, setMovieVendors) {
  const movies = await getMovies()
  const movieVendors = []

  setMovies(movies)

  for (const movie of movies) {
    movieVendors.push(await getPriceByMovieId(movie.id))
  }

  setMovieVendors(movieVendors)
}
