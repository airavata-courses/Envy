const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const db = require("./queries");
const port = 3000;
var http = require("http");

var cors = require("cors");
app.use(cors());
app.options('*', cors());

app.use(express.json());
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

//  const corOptions = {
//      'origin': '*',
//      'methods': 'GET,HEAD,PUT,PATCH,POST,DELETE',
//      'preflightContinue': true,
//      allowedHeaders:  'Content-Type,Authorization,X-Requested-With'
//   };
// app.use(cors(corOptions));

app.get("/", (request, response) => {
  response.json({ info: "Authentication service is up and running" });
});

app.post("/login", db.authorizeUser);
app.post("/signup", db.addUser);
app.put("/users/:id", db.updateUser);
app.post("/users/:id", db.deleteUser);

app.listen(port, () => {
  console.log(`App running on port ${port}.`);
});
