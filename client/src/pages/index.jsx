import { useEffect, useState } from 'react'
import { doSearch, getMovies } from '~/api'
import Card from '~common/Card'
import Loader from '~common/Loader'
import Container from '~common/Container'
import BaseInput from '~/components/base/BaseInput'
import { useDebounce } from '~/hooks/useDebounce'
import Poster from '~/components/common/Poster'
import classNames from '~/utils/classNames'
import { Transition } from '@headlessui/react'
import { Link } from 'react-router-dom'

export function Component() {
  const [movies, setMovies] = useState([])
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
            <Search type="search" label="Search movies" className="max-w-md" />
          </div>

          <div className="mt-16 grid sm:grid-cols-2 gap-6 sm:gap-8">
            {movies.map(movie => (
              <Card key={movie.id} movie={movie} />
            ))}
          </div>
        </>
      )}
    </Container>
  )
}

Component.displayName = 'Home'

function Search() {
  const [value, setValue] = useState('')
  const [isFocused, setIsFocused] = useState(false)
  const [searchResults, setSearchResults] = useState(null)

  function handleChange(e) {
    setValue(e.target.value)
  }

  function handleFocus() {
    setIsFocused(true)
  }

  function handleBlur() {
    setIsFocused(false)
  }

  useDebounce(
    async () => {
      if (value === '') {
        return
      }
      const results = await doSearch(value)
      setSearchResults(results)
    },
    1000,
    [value]
  )

  useEffect(() => {
    if (value === '') {
      setSearchResults(null)
    }
  }, [value])

  return (
    <div className="relative max-w-md">
      <BaseInput
        id="search"
        type="search"
        label="Search movies"
        className="max-w-md"
        autoComplete="off"
        value={value}
        onChange={handleChange}
        onFocus={handleFocus}
        onBlur={handleBlur}
      />

      {searchResults && (
        <Transition
          as="div"
          show={isFocused}
          className={classNames(
            'absolute p-1.5 inset-x-0 z-40 top-[calc(100%+0.5rem)] max-h-60 overflow-auto scrollbar',
            'shadow ring-sm ring-opacity-10 bg-gradient-to-tr from-indigo-950 to-indigo-900/80 divide-y divide-indigo-800 backdrop-blur-sm rounded-md',
            'data-[transition]:transition-opacity data-[closed]:opacity-0 data-[enter]:bg-red-500 data-[leave]:data-[closed]:opacity-0'
          )}
        >
          {searchResults.length === 0 ? (
            <div className="p-2 text-center font-semibold text-sm">
              No results found
            </div>
          ) : (
            searchResults.map(result => (
              <Link key={result.id} to={`/${result.id}`}>
                <div className="flex items-center gap-4 p-2 hover:bg-indigo-800/70 rounded transition-colors">
                  <div className="w-10 h-10 rounded-sm overflow-hidden">
                    <Poster
                      poster_url={result.posters[0].vertical}
                      title={result.title}
                    />
                  </div>
                  <div>
                    <p className="font-semibold">{result.title}</p>
                    <p className="font-medium text-gray-400 text-xs">
                      {result.rating} / 5
                    </p>
                  </div>
                </div>
              </Link>
            ))
          )}
        </Transition>
      )}
    </div>
  )
}
