import { useEffect, useState } from 'react'
import { getReleases, getTitleDetails, getTitleSources } from '~/api'
import Card from '~common/Card'
import Loader from '~common/Loader'
import Container from '~common/Container'

export function Component() {
  const [releases, setReleases] = useState([])
  const [titleDetails, setTitleDetails] = useState([])
  const [titleSources, setTitleSources] = useState([])
  const [prices, setPrices] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    initData(setReleases, setTitleDetails, setTitleSources, setPrices).then(
      () => setLoading(false)
    )
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
        <div className="mt-16 grid grid-cols-2 gap-8">
          {releases.map((release, i) => (
            <Card
              key={release.id}
              release={release}
              prices={prices[i]}
              titleDetail={titleDetails[i]}
            />
          ))}
        </div>
      )}
    </Container>
  )
}

Component.displayName = 'Home'

async function initData(
  setReleases,
  setTitleDetails,
  setTitleSources,
  setPrices
) {
  const details = []
  const sources = []
  const prices = []
  const { releases } = await getReleases()

  for (const release of releases) {
    const [detail, currSources] = await Promise.all([
      getTitleDetails(release.id),
      getTitleSources(release.id),
    ])

    let price = null

    for (const source of currSources) {
      if (source.price === null) continue
      if (price === null) price = source.price
      else price = Math.min(source.price, price)
      console.log(source.price)
    }

    details.push(detail)
    sources.push(currSources)
    prices.push(price)
  }

  setReleases(releases)
  setTitleDetails(details)
  setTitleSources(sources)
  setPrices(prices)
  console.log(prices)
}
