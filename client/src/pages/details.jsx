import { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import Badge from '~common/Badge'
import Loader from '~common/Loader'
import Poster from '~common/Poster'
import Container from '~common/Container'
import YoutubeEmbed from '~/components/YoutubeEmbed'
import { getTitleDetails, getTitleSources } from '~/api'

export function Component() {
  const params = useParams()

  const [titleDetail, setTitleDetails] = useState(null)
  const [titleSources, setTitleSources] = useState([])
  const [price, setPrice] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    initData(params.releaseId, setTitleDetails, setTitleSources, setPrice).then(
      () => setLoading(false)
    )
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
        className="text-sm text-indigo-400 hover:text-indigo-300 font-semibold transition-colors"
      >
        &larr; Back
      </Link>

      <section className="mt-4 grid grid-cols-4 gap-8">
        <div className="relative rounded overflow-hidden">
          <div className="w-[185px] h-[278px]">
            <Poster poster_url={titleDetail.poster} title={titleDetail.title} />
          </div>
        </div>

        <div className="col-span-3">
          <p className="text-sm text-gray-400 font-semibold">
            {titleDetail.year}{' '}
            {titleDetail.end_year && ` - ${titleDetail.end_year}`}
          </p>

          <h1 className="mt-1 text-5xl font-bold">{titleDetail.title}</h1>

          <div className="mt-4 flex gap-2.5 flex-wrap">
            {titleDetail.genre_names.slice(0, 3).map(genre => (
              <Badge key={genre} badge={genre} />
            ))}
          </div>

          {titleDetail.user_rating && (
            <p className="mt-4">
              {price && (
                <>
                  <span className="inline-block font-semibold text-2xl text-emerald-300">
                    {price}
                  </span>
                  <span className="inline-block mx-2.5">•</span>
                </>
              )}
              <span className="inline-block font-bold text-indigo-300">
                {titleDetail.user_rating} / 10
              </span>
            </p>
          )}

          <p className="max-w-lg mt-4 text-gray-300">
            {titleDetail.plot_overview}
          </p>
        </div>
      </section>

      <section className="mt-12">
        <h2 className="mb-8 text-4xl font-bold">Trailer</h2>

        <div className="mx-auto w-max">
          <YoutubeEmbed src={titleDetail.trailer} title={titleDetail.title} />
        </div>
      </section>

      {/* <section className="mt-12">
        <h2 className="mb-8 text-4xl font-bold">Sources</h2>

        <div>
          {titleSources.map(source => (
            <SourceCard source={source} />
          ))}
        </div>
      </section> */}
    </Container>
  )
}

Component.displayName = 'Details'

// function SourceCard({ source }) {
//   return (
//     <div className="rounded-xl overflow-hidden shadow-lg flex ring ring-gray-900 ring-opacity-10 bg-gradient-to-tr even:bg-gradient-to-bl from-indigo-950 to-indigo-900">

//     </div>
//   )
// }

async function initData(releaseId, setTitleDetails, setTitleSources, setPrice) {
  const [detail, currSources] = await Promise.all([
    getTitleDetails(releaseId),
    getTitleSources(releaseId),
  ])

  let price = null

  for (const source of currSources) {
    if (source.price === null) continue
    if (price === null) price = source.price
    else price = Math.min(source.price, price)
    console.log(source.price)
  }

  setTitleDetails(detail)
  setTitleSources(currSources)
  setPrice(price)
}
