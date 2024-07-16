import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
// import Badge from '~common/Badge'
import Loader from '~common/Loader'
import Poster from '~common/Poster'
import Container from '~common/Container'
import YoutubeEmbed from '~/components/YoutubeEmbed'
import { getMovieById, getPostersByMovieId, getVendorsByMovieId } from '~/api'
// import Year from '~/components/Year'

export function Component() {
  const params = useParams()

  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [movie, setMovie] = useState([])
  const [vendors, setVendors] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    initData(params.movieId, setMovie, setVendors).then(() => setLoading(false))
  }, [params])

  if (loading) {
    return (
      <div className="mt-16">
        <Loader className="mx-auto w-6" />
      </div>
    )
  }

  return (
    <Container as="main">
      <Link
        to="/"
        className="sm:text-sm text-indigo-400 hover:text-indigo-300 font-semibold transition-colors"
      >
        &larr; Back
      </Link>

      <section className="mt-4 sm:grid grid-cols-4 gap-8">
        {/* <div className="relative rounded overflow-hidden">
          <div className="sm:w-[185px] sm:h-[278px]">
            <Poster poster_url={movie.posters[0].url} title={movie.title} />
          </div>
        </div> */}

        <div className="mt-6 sm:mt-0 col-span-3">
          <div className="mb-6 grid sm:grid-cols-4 gap-4">
            {movie.posters.map(poster => (
              <div key={poster.id} className="relative rounded overflow-hidden">
                <div className="sm:w-[185px] sm:h-[278px]">
                  <Poster poster_url={poster.url} title={movie.title} />
                </div>
              </div>
            ))}
          </div>

          {/* <Year year={movie.year} endYear={movie.end_date} fontSize="text-sm" /> */}

          <h1 className="mt-1 text-5xl font-bold">{movie.title}</h1>

          {/* <div className="mt-4 flex gap-2.5 flex-wrap">
            {movie.genre_names.slice(0, 3).map(genre => (
              <Badge key={genre} badge={genre} />
            ))}
          </div> */}

          <p className="mt-4">
            <span className="inline-block font-semibold text-2xl text-emerald-300">
              ${vendors[0].price}
            </span>
            <span className="inline-block mx-2.5">•</span>
            <span className="inline-block font-bold text-indigo-300">
              {movie.rating} / 5
            </span>
          </p>

          <p className="max-w-lg mt-4 text-gray-300">{movie.plot}</p>
        </div>
      </section>

      <section className="mt-12">
        <h2 className="mb-8 text-4xl font-bold">Trailer</h2>

        <div className="mx-auto w-max">
          <YoutubeEmbed src={movie.trailer} title={movie.title} />
        </div>
      </section>

      <section className="mt-12">
        <h2 className="mb-8 text-4xl font-bold">Vendors</h2>

        <div className="grid grid-cols-2 sm:grid-cols-5 gap-6">
          {vendors.map(vendor => (
            <div
              key={vendor.id}
              className="group p-6 rounded bg-gradient-to-bl from-indigo-950 to-indigo-900"
            >
              <p className="text-lg font-semibold">{vendor.vendor.name}</p>
              <p className="mt-1 text-2xl font-semibold text-red-500 group-first:text-emerald-500">
                ${vendor.price}
              </p>
            </div>
          ))}
        </div>
      </section>
    </Container>
  )
}

Component.displayName = 'Details'

async function initData(movieId, setMovie, setVendors) {
  const [movie, vendors] = await Promise.all([
    getMovieById(movieId),
    getVendorsByMovieId(movieId),
  ])
  getPostersByMovieId(movieId)

  setMovie(movie)
  setVendors(vendors)
}
