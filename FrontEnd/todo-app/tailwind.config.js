/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts,css}",  // Asegúrate de que esta ruta incluye todos los archivos relevantes
  ],
  theme: {
    extend: {
      colors: {
        primary: "#871cf8",
        "background-100": "#1A1A1A",
        "background-200": "#292929",
        "background-300": "#4A4A4A",
        "background-400": "#5B5B5B"  
      }
    },
  },
  plugins: [],
}
