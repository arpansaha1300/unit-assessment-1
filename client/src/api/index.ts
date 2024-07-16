import _fetch from '~/utils/_fetch'

export function getMovies() {
  return _fetch('movies')
}

export function getMovieById(movieId: number) {
  return _fetch(`movies/${movieId}`)
}

export function getPriceByMovieId(movieId: number) {
  return _fetch(`movies/${movieId}/price`)
}

export function getPostersByMovieId(movieId: number) {
  return _fetch(`posters/${movieId}`)
}

export function getVendorsByMovieId(movieId: number) {
  return _fetch(`vendors/${movieId}`)
}
