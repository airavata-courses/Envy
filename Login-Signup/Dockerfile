FROM node:9.4
WORKDIR /usr/src/app
COPY package*.json ./
ADD package.json /usr/src/app/package.json
RUN npm install

COPY . .
EXPOSE 30001

CMD ["npm", "start"]

