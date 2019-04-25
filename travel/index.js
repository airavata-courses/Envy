const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const db = require("./queries");
const port = 3000;
var http = require("http");

var cors = require("cors");

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

const corsOptions = {
  origin: 'http://js-171-47.jetstream-cloud.org'
}


app.get("/", (request, response) => {
  response.json({ info: "Authentication service is up and running" });
});

app.post("/login", cors(corsOptions), db.authorizeUser);
app.post("/signup",  cors(corsOptions), db.addUser);
app.put("/users/:id", db.updateUser);
app.post("/users/:id", db.deleteUser);

app.listen(port, () => {
  console.log(`App running on port ${port}.`);
});
