const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const db = require("./queries");
const port = 3005;
var cors = require("cors");

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

app.all("*", function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content-Type, Accept"
  );
  res.header("Access-Control-Allow-Methods", "GET, POST");
  res.setHeader("Access-Control-Allow-Credentials", true);

  next();
});

app.get("/", (request, response) => {
  response.json({ info: "Node.js, Express, and Postgres API" });
});

app.get("/findAirports", db.findAirports);
app.get("/addToDatabse", db.addToDatabase);
app.get("/test", db.test);

app.listen(process.env.PORT || port, () => {
  console.log(`App running on port ${port}.`);
});
