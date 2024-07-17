import twFormsPlugin from '@tailwindcss/forms'

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{html,jsx,tsx,css}"],
  theme: {
    extend: {},
  },
  plugins: [
    twFormsPlugin,
  ],
}

