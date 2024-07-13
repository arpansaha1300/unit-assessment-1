import classNames from '~/utils/classNames'

interface BadgeProps {
  badge: string

  /** @default false */
  small?: boolean
}

export default function Badge({ badge, small }: BadgeProps) {
  return (
    <span
      className={classNames(
        'inline-block px-2 py-0.5 rounded-md shadow font-semibold',
        small ? 'text-xs' : 'text-sm',
        getBadgeColor(badge)
      )}
    >
      {badge}
    </span>
  )
}

function getBadgeColor(badge: string) {
  if (badge === 'Netflix') return 'bg-red-300 text-red-900'

  if (badge === 'Prime Video' || badge === 'Amazon Prime')
    return 'bg-blue-300 text-blue-900'

  if (badge === 'Disney+') return 'bg-emerald-300 text-emerald-900'

  return 'bg-gray-300 text-gray-900'
}
