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
app.post("/", (request, response) => {
  key = request.body.key;
  if (key === "login") {
    username = request.body.username;
    password = request.body.password;
    console.log("login service " + username + " " + password);
    var options = {
      hostname: "NodeService.service.consul",
      port: 3000,
      path: "/login",
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      }
    };
    var req = http.request(options, function(res) {
      console.log("Status: " + res.statusCode);
      console.log("Headers: " + JSON.stringify(res.headers));
      res.setEncoding("utf8");
      res.on("data", function(body) {
        console.log("Body: " + body);
        response.end(body);
      });
    });
    req.on("error", function(e) {
      console.log("problem with request: " + e.message);
    });
    // write data to request body
    var body = JSON.stringify({
      username: username,
      password: password
    });
    req.end(body);
  } else if (key === "signup") {
    username = request.body.username;
    password = request.body.password;
    var options = {
      hostname: "NodeService.service.consul",
      port: 3000,
      path: "/signup",
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      }
    };
    var req = http.request(options, function(res) {
      console.log("Status: " + res.statusCode);
      console.log("Headers: " + JSON.stringify(res.headers));
      res.setEncoding("utf8");
      res.on("data", function(body) {
        console.log("Body: " + body);
        response.end(body);
      });
    });
    req.on("error", function(e) {
      console.log("problem with request: " + e.message);
    });
    // write data to request body
    var body = JSON.stringify({
      username: username,
      password: password
    });
    req.end(body);
  } else if (key === "search") {
    origin = request.body.origin;
    destination = request.body.destination;
    date = request.body.date;
    search_id = request.body.search_id;
    console.log("search service");
    console.log(origin + "," + destination + "," + date + "," + search_id);
    var options = {
      hostname: "JavaService.service.consul",
      port: 9200,
      path:
        "/getAirport?origin=SFO&destination=MCO&date=2019-03-11&search_id=1231232",
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    };
    var req = http.request(options, function(res) {
      console.log("Status: " + res.statusCode);
      console.log("Headers: " + JSON.stringify(res.headers));
      res.setEncoding("utf8");
      res.on("data", function(body) {
        console.log("Body: " + body);
        response.end(body);
      });
    });
    req.on("error", function(e) {
      console.log("problem with request: " + e.message);
    });
    req.end();
  } else if (key == "display") {
    search_id = request.body.search_id;
    console.log("display cheapest service");
    var options = {
      hostname: "pythonservice.service.consul",
      port: 8000,
      path: "getiternary/?search_id=1231231",
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    };
  }
  var req = http.request(options, function(res) {
    console.log("Status: " + res.statusCode);
    console.log("Headers: " + JSON.stringify(res.headers));
    res.setEncoding("utf8");
    res.on("data", function(body) {
      console.log("Body: " + body);
      response.end(body);
    });
  });
  req.on("error", function(e) {
    console.log("problem with request: " + e.message);
  });
  req.end();
});

app.post("/login", db.authorizeUser);
app.post("/signup", db.addUser);
app.put("/users/:id", db.updateUser);
app.post("/users/:id", db.deleteUser);

app.listen(port, () => {
  console.log(`App running on port ${port}.`);
});
