import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
// import Badge from '~common/Badge'
import Loader from '~common/Loader'
import Poster from '~common/Poster'
import Container from '~common/Container'
import YoutubeEmbed from '~/components/YoutubeEmbed'
import { getMovieById, getVendorsByMovieId } from '~/api'
import { Transition } from '@headlessui/react'
import classNames from '~/utils/classNames'
// import Year from '~/components/Year'

// interface PosterCarousalProps {
//   posters: any[]
// }

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
    <main>
      <div>
        <PosterCarousal posters={movie.posters} />

        <Container as="section" className="mt-52 relative z-30 py-16">
          <Link
            to="/"
            className="text-indigo-400 hover:text-indigo-300 font-semibold transition-colors"
          >
            &larr; Back
          </Link>

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

          <p className="max-w-2xl mt-4 text-sm text-gray-300">{movie.plot}</p>
        </Container>
      </div>

      <Container as="section" className="py-12 h-screen">
        <h2 className="mb-8 text-4xl font-bold">Trailer</h2>

        <div className="mx-auto w-max">
          <YoutubeEmbed src={movie.trailer} title={movie.title} />
        </div>
      </Container>
    </main>
  )
}

Component.displayName = 'Details'

function PosterCarousal({ posters }) {
  const [nextPosterIdx, setNextPosterIdx] = useState(1 % posters.length)
  const [currPosterIdx, setCurrPosterIdx] = useState(0)
  const [show, setShow] = useState(true)

  useEffect(() => {
    let currTimer = null
    let nextTimer = null
    const interval = setInterval(() => {
      setShow(false)

      currTimer = setTimeout(() => {
        setShow(true)
        setCurrPosterIdx(x => (x + 1) % posters.length)
      }, 500)

      nextTimer = setTimeout(() => {
        setNextPosterIdx(x => (x + 1) % posters.length)
      }, 700)
    }, 3000)

    return () => {
      clearInterval(interval)
      if (currTimer) clearTimeout(currTimer)
      if (nextTimer) clearTimeout(nextTimer)
    }
  }, [posters.length])

  return (
    <div className="absolute top-0 w-screen h-screen">
      <Poster
        poster_url={posters[nextPosterIdx].horizontal}
        title={posters[nextPosterIdx].title}
      />

      <Transition
        show={show}
        unmount={false}
        as="div"
        className={classNames(
          'absolute inset-0 z-10 data-[closed]:opacity-0',
          'data-[leave]:transition data-[leave]:ease-in-out data-[leave]:duration-300',
          'data-[leave]:data-[closed]:opacity-0'
        )}
      >
        <Poster
          poster_url={posters[currPosterIdx].horizontal}
          title={posters[currPosterIdx].title}
        />
      </Transition>

      <span className="absolute inset-0 z-20 bg-gradient-to-tr from-stone-950" />
    </div>
  )
}

async function initData(movieId, setMovie, setVendors) {
  const [movie, vendors] = await Promise.all([
    getMovieById(movieId),
    getVendorsByMovieId(movieId),
  ])

  setMovie(movie)
  setVendors(vendors)
}
