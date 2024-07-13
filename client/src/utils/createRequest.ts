export interface RequestOptions extends Omit<RequestInit, 'body' | 'method'> {
  /** @default 'GET' */
  method?: 'GET' | 'POST' | 'PATCH' | 'DELETE' | 'PUT'

  query?: URLSearchParams

  body?: Record<string, any>
}

export const FETCH_BASE_URL = 'https://api.watchmode.com/v1/'

export default function createRequest(url: string, options: RequestOptions = {}): Request {
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

  const query = options.query ?? new URLSearchParams()
  query.append("apiKey", import.meta.env.VITE_WATCHMODE_API_KEY!)

  const requestUrl = FETCH_BASE_URL + url + '/?' + query.toString()

  return new Request(requestUrl, {
    ...options,
    body,
    headers,
  })
}