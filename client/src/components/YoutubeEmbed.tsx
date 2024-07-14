import { useEffect, useState } from 'react'

interface YoutubeEmbedProps {
  src: string
  title: string
}

export default function YoutubeEmbed({ src, title }: YoutubeEmbedProps) {
  const [_src, setSrc] = useState(src)

  useEffect(() => {
    if (src.includes('watch')) {
      setSrc(src.replace('watch?v=', 'embed/'))
    }
  }, [src])

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
