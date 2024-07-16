import { Link } from 'react-router-dom'
import Badge from './Badge'
import Poster from './Poster'
// import Year from '../Year'
import classNames from '~/utils/classNames'

interface CardProps {
  movie: any
  movieVendor: any
}

export default function Card(props: Readonly<CardProps>) {
  const { movie, movieVendor } = props

  // const year = parseInt(movie.year)

  return (
    <div className="rounded-xl overflow-hidden shadow-lg sm:flex ring ring-gray-900 ring-opacity-10 bg-gradient-to-tr even:bg-gradient-to-bl from-indigo-950 to-indigo-900 relative">
      <div className="relative">
        <div className="sm:w-[185px] sm:h-[278px]">
          <Poster poster_url={movie.posters[0]?.url} title={movie.title} />
        </div>

        <span className="hidden sm:block absolute inset-0 bg-gradient-to-br from-indigo-950/30" />
        <span className="sm:hidden absolute inset-0 top-1/3 bg-gradient-to-t from-stone-950" />

        <div className="absolute top-2 left-2">
          <Badge badge={movieVendor.vendor.name} />
        </div>
      </div>

      <div className="flex-grow p-4 flex flex-col justify-between absolute z-10 bottom-0 sm:static">
        <div>
          {/* <Year
            year={titleDetail.year}
            endYear={titleDetail.end_date}
            fontSize="text-xs"
          /> */}

          <h2
            className={classNames(
              // Boolean(year) && 'mt-1',
              'text-2xl font-bold'
            )}
          >
            <Link
              to={`/${movie.id}`}
              className="hover:text-indigo-200 transition-colors"
            >
              {movie.title}
            </Link>
          </h2>

          {/* <div className="mt-2 flex gap-2 flex-wrap">
            {titleDetail.genre_names.slice(0, 3).map((genre: string) => (
              <Badge key={genre} badge={genre} small />
            ))}
          </div> */}

          <p className="mt-2.5">
            <span className="inline-block font-semibold text-2xl text-emerald-300">
              ${movieVendor.price}
            </span>
            <span className="inline-block mx-2">•</span>
            {movie.rating && (
              <span className="inline-block text-sm font-bold text-indigo-300">
                {movie.rating} / 5
              </span>
            )}
          </p>
        </div>

        <div>
          <p className="mt-4 sm:mt-0 text-xs text-gray-300 line-clamp-3 sm:line-clamp-4">
            {movie.plot}
          </p>

          <Link
            to={`/${movie.id}`}
            className="block mt-2 text-sm text-indigo-400 hover:text-indigo-300 font-semibold transition-colors"
          >
            View &rarr;
          </Link>
        </div>
      </div>
    </div>
  )
}
