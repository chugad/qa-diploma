FROM node:alpine

WORKDIR /usr/src/app

RUN npm install

COPY . /usr/src/app

EXPOSE 9999

CMD [ "npm", "start" ]