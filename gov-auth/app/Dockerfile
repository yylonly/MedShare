FROM node

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY authtication /usr/src/app/
RUN npm install

EXPOSE 8080
CMD ["node", "app.js"]
