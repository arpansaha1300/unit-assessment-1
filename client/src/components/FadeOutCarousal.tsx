import { useEffect, useState } from 'react'
import { Transition } from '@headlessui/react'
import classNames from '~/utils/classNames'

interface Image {
  src: string
  alt: string
}

interface FadeOutCarousalProps {
  images: Image[]
  imgClass?: string
  showBullets?: boolean
}

export default function FadeOutCarousal(props: Readonly<FadeOutCarousalProps>) {
  const { images, imgClass, showBullets = false } = props

  const [nextPosterIdx, setNextPosterIdx] = useState(1 % images.length)
  const [currPosterIdx, setCurrPosterIdx] = useState(0)
  const [show, setShow] = useState(true)

  useEffect(() => {
    let currTimer: NodeJS.Timeout | null = null
    let nextTimer: NodeJS.Timeout | null = null

    const interval = setInterval(() => {
      setShow(false)

      currTimer = setTimeout(() => {
        setShow(true)
        setCurrPosterIdx(x => (x + 1) % images.length)
      }, 500)

      nextTimer = setTimeout(() => {
        setNextPosterIdx(x => (x + 1) % images.length)
      }, 700)
    }, 3000)

    return () => {
      clearInterval(interval)
      if (currTimer) clearTimeout(currTimer)
      if (nextTimer) clearTimeout(nextTimer)
    }
  }, [images.length])

  return (
    <div className="relative w-full h-full">
      <img
        src={images[nextPosterIdx].src}
        alt={images[nextPosterIdx].alt}
        className={imgClass}
      />

      <Transition
        show={show}
        unmount={false}
        as="div"
        className={classNames(
          'absolute inset-0 z-10 data-[closed]:opacity-0',
          'data-[leave]:transition data-[leave]:ease-in-out data-[leave]:duration-300',
          'data-[leave]:data-[closed]:opacity-0'
        )}
      >
        <img
          src={images[currPosterIdx].src}
          alt={images[currPosterIdx].alt}
          className={imgClass}
        />
      </Transition>

      {showBullets && (
        <div className="absolute bottom-2 lg:bottom-4 left-1/2 -translate-x-1/2 z-30 space-x-2.5">
          {images.map((_, i) => (
            <span
              key={i}
              className={classNames(
                'inline-block w-2 h-2 rounded-full',
                currPosterIdx === i ? 'bg-gray-50' : 'border border-gray-50'
              )}
            />
          ))}
        </div>
      )}
    </div>
  )
}
