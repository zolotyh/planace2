module.exports = {
  content: [
    './src/**/*',
    './resources/**/*',
  ],
  theme: {
    extend: {
      aspectRatio: {
        '63/88': '63 / 88',
      },
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
