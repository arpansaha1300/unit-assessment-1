import { useEffect, useState } from 'react'

interface YoutubeEmbedProps {
  src: string
  title: string
}

export default function YoutubeEmbed({ src, title }: YoutubeEmbedProps) {
  const [_src, setSrc] = useState(src)

  useEffect(() => {
    if (src && src.includes('watch')) {
      setSrc(src.replace('watch?v=', 'embed/'))
    }
  }, [src])

  if (!_src) {
    return (
      <div>
        <p>Trailer not available</p>
      </div>
    )
  }
  return (
    <iframe
      src={_src}
      title={title}
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerPolicy="strict-origin-when-cross-origin"
      allowFullScreen
      className="w-80 sm:w-[35rem] aspect-video"
    ></iframe>
  )
}
