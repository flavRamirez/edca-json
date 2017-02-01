# edca-json

Herramienta para revisar homologación de archivos, entre una carpeta `json` de archivos `<id>.json` y una carpeta `data` de carpetas `<id>/`

## Usage

`lein repl`

=>`la-differance`
los ids que aparecen en json pero no en carpeta

=>`la-differance2`
los ids que aparecen en carpeta pero no en json

=>`pairing`
la recomendación de parejas de jsons y carpetas que mejor matchean usando [distancia de Levenshtein](https://es.wikipedia.org/wiki/Distancia_de_Levenshtein)

## License

Copyright © 2017 MX Abierto

Distributed under Libre Uso MX
