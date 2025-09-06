## Etapa de construção
FROM node:22-alpine AS builder


COPY package.json package-lock.json /app/
WORKDIR /app

RUN npm i --freeze-lockfile
COPY . /app

CMD ["index.js"]