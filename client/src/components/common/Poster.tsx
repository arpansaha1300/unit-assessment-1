interface PosterProps {
  poster_url: string | null
  title: string
}

export default function Poster({ poster_url, title }: PosterProps) {
  if (poster_url) {
    return (
      <div className="w-[185px] h-[278px]">
        <img
          src={poster_url}
          alt={`${title} poster`}
          className="w-full h-full object-cover"
        />
      </div>
    )
  }

  return <div className=""></div>
}
