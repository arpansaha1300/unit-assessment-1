import { useEffect, useState } from 'react'
import { doSearch, getMovies } from '~/api'
import Card from '~common/Card'
import Loader from '~common/Loader'
import Container from '~common/Container'
import BaseInput from '~/components/base/BaseInput'
import { useDebounce } from '~/hooks/useDebounce'

export function Component() {
  const [movies, setMovies] = useState([])
  const [searchResults, setSearchResults] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    getMovies().then(res => {
      setMovies(res)
      setLoading(false)
    })
  }, [])

  return (
    <Container as="main" className="py-16">
      <p className="text-xl font-semibold text-indigo-400">Movies & TV Shows</p>
      <h1 className="mt-2 text-5xl font-bold"> Recently Released </h1>

      {loading ? (
        <div className="mt-16">
          <Loader className="mx-auto w-6" />
        </div>
      ) : (
        <>
          <div className="mt-8">
            <Search
              type="search"
              label="Search movies"
              className="max-w-md"
              setSearchResults={setSearchResults}
            />
          </div>

          <div className="mt-16 grid sm:grid-cols-2 gap-6 sm:gap-8">
            <Movies movies={movies} searchResults={searchResults} />
          </div>
        </>
      )}
    </Container>
  )
}

Component.displayName = 'Home'

function Search({ setSearchResults }) {
  const [value, setValue] = useState('')
  // const isFirstRun = useRef(true)

  function handleChange(e) {
    setValue(e.target.value)
  }

  useDebounce(
    async () => {
      // if (isFirstRun.current) {
      //   isFirstRun.current = false
      //   return
      // }
      if (value === '') {
        setSearchResults(null)
        return
      }
      const results = await doSearch(value)
      setSearchResults(results)
    },
    1000,
    [value]
  )

  return (
    <BaseInput
      type="search"
      label="Search movies"
      className="max-w-md"
      value={value}
      onChange={handleChange}
    />
  )
}

function Movies({ movies, searchResults }) {
  if (searchResults) {
    return searchResults.map(movie => <Card key={movie.id} movie={movie} />)
  }

  return movies.map(movie => <Card key={movie.id} movie={movie} />)
}
