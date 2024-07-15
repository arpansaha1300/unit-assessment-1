import classNames from '~/utils/classNames'

interface YearProps {
  year: string | null
  endYear: string | null
  fontSize: 'text-sm' | 'text-xs'
}

export default function Year({
  year,
  endYear,
  fontSize = 'text-xs',
}: YearProps) {
  const parsedYear = year ? parseInt(year) : null

  // eslint-disable-next-line no-extra-boolean-cast
  if (Boolean(parsedYear)) {
    return (
      <p className={classNames(fontSize, 'text-gray-400 font-semibold')}>
        {parsedYear} {endYear && ` - ${endYear}`}
      </p>
    )
  }

  return null
}
