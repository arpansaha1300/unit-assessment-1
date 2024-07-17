import { useCallback, useEffect, useRef } from 'react'

type DebouncedFn = (...args: any[]) => void | Promise<void>

export function useDebounce(
  fn: DebouncedFn,
  ms: number,
  deps: React.DependencyList
) {
  const timeoutId = useRef<NodeJS.Timeout | null>(null)

  const debouncedFn: DebouncedFn = useCallback((...args: any[]) => {
    if (timeoutId.current !== null) clearTimeout(timeoutId.current)

    timeoutId.current = setTimeout(async () => {
      await fn(...args)
      timeoutId.current = null
    }, ms)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, deps)

  useEffect(() => {
    debouncedFn()
  }, [debouncedFn])
}
