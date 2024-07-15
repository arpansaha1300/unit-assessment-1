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
      width="560"
      height="315"
      src={_src}
      title={title}
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerPolicy="strict-origin-when-cross-origin"
      allowFullScreen
    ></iframe>
  )
}
