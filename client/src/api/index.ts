import _fetch from '~/utils/_fetch'

export function getReleases(limit = 5) {
  if (limit) {
    return _fetch('releases', {
      query: new URLSearchParams({ limit: limit.toString() }),
    })
  }

  return _fetch('releases')
}

export function getTitleDetails(titleId: number) {
  return _fetch(`title/${titleId}/details`)
}

export function getTitleSources(titleId: number) {
  return _fetch(`title/${titleId}/sources`)
}
