import Badge from './Badge'
import Poster from './Poster'

interface CardProps {
  release: any
  titleDetail: any
  price: number | null
}

export default function Card({ release, titleDetail }: CardProps) {
  return (
    <div className="rounded-xl overflow-hidden shadow-lg flex ring ring-gray-900 ring-opacity-10 bg-gradient-to-tr even:bg-gradient-to-bl from-indigo-950 to-indigo-900">
      <div className="relative">
        <Poster
          poster_url={release.poster_url}
          title={`${release.title} poster`}
        />

        <span className="absolute inset-0 bg-gradient-to-br from-indigo-950/30" />

        <div className="absolute top-2 left-2">
          <Badge badge={release.source_name} />
        </div>
      </div>

      <div className="flex-grow p-4 flex flex-col justify-between">
        <div>
          <p className="text-xs text-gray-400 font-semibold">
            {titleDetail.year}{' '}
            {titleDetail.end_year && ` - ${titleDetail.end_year}`}
          </p>

          <h2 className="mt-1 text-2xl font-bold">{release.title}</h2>

          <div className="mt-2 flex gap-2 flex-wrap">
            {titleDetail.genre_names.slice(0, 3).map((genre: string) => (
              <Badge key={genre} badge={genre} small />
            ))}
          </div>

          {titleDetail.user_rating && (
            <p className="mt-2">
              <span className="text-sm font-bold text-indigo-300">
                {titleDetail.user_rating} / 10
              </span>
              {/* {' • '}
          <span>
            {price}
          </span> */}
            </p>
          )}
        </div>

        <div>
          <p className="text-xs text-gray-300 line-clamp-3">
            {titleDetail.plot_overview}
          </p>

          <p className="mt-2 text-sm text-indigo-400 hover:text-indigo-500 font-semibold">
            View &rarr;
          </p>
        </div>
      </div>
    </div>
  )
}
