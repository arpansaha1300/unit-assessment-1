import { useEffect, useMemo, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import Loader from '~common/Loader'
import Container from '~common/Container'
import YoutubeEmbed from '~/components/YoutubeEmbed'
import FadeOutCarousal from '~/components/FadeOutCarousal'
import { getMovieById } from '~/api'

export function Component() {
  const params = useParams()

  const [movie, setMovie] = useState({})
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    getMovieById(params.movieId).then(res => {
      setMovie(res)
      setLoading(false)
    })
  }, [params])

  const images = useMemo(
    () =>
      movie.posters?.map(poster => ({
        src: poster.horizontal,
        alt: movie.title,
      })),
    [movie.posters, movie.title]
  )

  if (loading) {
    return (
      <div className="mt-16">
        <Loader className="mx-auto w-6" />
      </div>
    )
  }

  return (
    <main>
      <div className="lg:h-screen">
        <div className="relative lg:absolute top-0 w-full aspect-video lg:aspect-auto lg:h-screen">
          <FadeOutCarousal
            images={images}
            imgClass="w-full h-full object-cover"
            showBullets={true}
          />

          <span className="absolute inset-0 z-20 bg-gradient-to-t from-stone-950/60 via-transparent lg:bg-gradient-to-tr lg:from-stone-950" />
        </div>

        <Container
          as="section"
          className="mt-10 sm:mt-16 lg:mt-0 lg:pt-64 relative z-30"
        >
          <Link
            to="/"
            className="text-indigo-400 hover:text-indigo-300 font-semibold transition-colors"
          >
            &larr; Back
          </Link>

          <h1 className="mt-1 text-5xl font-bold">{movie.title}</h1>

          <p className="mt-4">
            <span className="inline-block font-semibold text-2xl text-emerald-300">
              ${movie.price}
            </span>
            <span className="inline-block mx-2.5">•</span>
            <span className="inline-block font-bold text-indigo-300">
              {movie.rating} / 5
            </span>
          </p>

          <p className="max-w-2xl mt-4 text-sm text-gray-300">{movie.plot}</p>
        </Container>
      </div>

      <Container as="section" className="py-10 sm:py-16 lg:h-screen">
        <h2 className="mb-8 text-4xl font-bold">Trailer</h2>

        <div className="mx-auto w-max">
          <YoutubeEmbed src={movie.trailer} title={movie.title} />
        </div>
      </Container>
    </main>
  )
}

Component.displayName = 'Details'
