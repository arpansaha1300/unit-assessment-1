import { forwardRef } from 'react'
import classNames from '~/utils/classNames'

interface BaseInputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  id: string
  label: string
  srOnlyLabel?: boolean
  disabled?: boolean
  validationError?: string | null
}

const BaseInput = forwardRef<HTMLInputElement, BaseInputProps>((props, ref) => {
  const {
    id,
    label,
    validationError = null,
    disabled = false,
    srOnlyLabel = false,
    className = '',
    ...inputAttrs
  } = props

  return (
    <div className="relative">
      <label
        htmlFor={id}
        className={classNames(
          'block text-sm font-medium text-gray-100',
          srOnlyLabel && 'sr-only'
        )}
      >
        {label}
      </label>
      <div className="mt-1">
        <input
          ref={ref}
          id={id}
          disabled={disabled}
          {...inputAttrs}
          className={classNames(
            'block w-full appearance-none rounded-md px-3 py-2 text-gray-50 placeholder-gray-400 focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 text-sm',
            disabled
              ? 'bg-indigo-900/50 border-none'
              : 'bg-indigo-900/65 border border-gray-300 shadow-sm',
            className
          )}
        />
      </div>
      {validationError !== null && (
        <p className="text-xs text-red-400 absolute -bottom-5">
          {validationError}
        </p>
      )}
    </div>
  )
})

export default BaseInput
