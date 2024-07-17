import _fetch from '~/utils/_fetch'

export async function getMovies() {
  return _fetch('movies')
}

export async function getMovieById(movieId: number) {
  return _fetch(`movies/${movieId}`)
}

export async function getPostersByMovieId(movieId: number) {
  return _fetch(`posters/${movieId}`)
}

export async function doSearch(search: string) {
  return _fetch(`movies?search=${search}`)
}
