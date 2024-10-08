export interface RequestOptions extends Omit<RequestInit, 'body' | 'method'> {
  /** @default 'GET' */
  method?: 'GET' | 'POST' | 'PATCH' | 'DELETE' | 'PUT'

  query?: URLSearchParams

  body?: Record<string, any>
}

export const FETCH_BASE_URL = 'http://localhost:4000/api/'

export default function createRequest(
  url: string,
  options: RequestOptions = {}
): Request {
  let headers: RequestOptions['headers'] = {
    'Content-Type': 'text/plain',
  }

  if (options?.headers) {
    headers = {
      ...options.headers,
      ...headers,
    }
  }

  let body

  if (import.meta.env.DEV) {
    options.mode = 'cors'
  }

  const requestUrl = FETCH_BASE_URL + url

  return new Request(requestUrl, {
    ...options,
    body,
    headers,
  })
}
