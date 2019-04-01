const bcrypt = require("bcrypt");
const Pool = require("pg").Pool;

var req = require("sync-request");

const pool = new Pool({
  user: "me",
  host: "149.165.168.73",
  database: "api",
  password: "password",
  port: 5432
});

class CacheData {
  constructor(airport_name, state, latitude, longitude, iata) {
    this.airport_name = airport_name;
    this.state = state;
    this.latitude = latitude;
    this.longitude = longitude;
    this.iata = iata;
  }
}

var cachedObjects = [];
pool.query("SELECT * from airport", (error, results) => {
  if (error) {
    console.log("DB not connected ");
    throw error;
  }
  for (i = 0; i < results.rowCount; i++) {
    var rows = results.rows[i];
    cachedObjects.push(
      new CacheData(
        rows["airport"],
        rows["state"],
        rows["latitude"],
        rows["longitude"],
        rows["iata"]
      )
    );
  }
  console.log("Cached airport data");
});

const addToDatabase = (request, response) => {
  console.log("Database");
};

const findAirports = (request, response) => {
  const latitude = request.query.latitude;
  const longitude = request.query.longitude;
  const state = request.query.state;
  console.log("GET request", "/findAirport", latitude, longitude, state);
  console.log(latitude, longitude);
  var airports = [];
  var q = "SELECT * from airport where state = '" + state + "'";

  pool.query(q, (error, results) => {
    if (error) {
      console.log("Issue with the database " + error);
      response.status(400).json({
        status: {
          type: "failure",
          message: error,
          code: "400",
          error: "true"
        }
      });
    } else {
      if (results.rowCount === 0) {
        response.status(400).json({
          status: {
            type: "failure",
            message: "No Nearest airport found",
            code: "400",
            error: "true"
          }
        });
      } else {
        for (i = 0; i < results.rowCount; i++) {
          var rows = results.rows[i];
          var temp = distance(
            latitude,
            longitude,
            parseFloat(rows["latitude"]),
            parseFloat(rows["longitude"])
          );
          if (temp < 200) {
            airports.push({
              distance: temp,
              airport: rows["airport"],
              iata: rows["iata"]
            });
          }
        }
        if (airports.length == 0) {
          response.status(404).json({
            status: {
              type: "failure",
              data: "No airports found in 200 mile radius",
              code: "404",
              error: "true"
            }
          });
        } else {
          console.log("Found airports ", airports);
          response.status(200).json({
            status: {
              type: "success",
              data: airports,
              code: "200",
              error: "false"
            }
          });
        }
      }
    }
  });
};

const test = (request, response) => {
  response.status(200).send("This is a test route");
};

function distance(lat1, lon1, lat2, lon2, unit) {
  //console.log(lat1, lat2, lon1, lon2);
  if (lat1 == lat2 && lon1 == lon2) {
    return 0;
  } else {
    var radlat1 = (Math.PI * lat1) / 180;
    var radlat2 = (Math.PI * lat2) / 180;
    var theta = lon1 - lon2;
    var radtheta = (Math.PI * theta) / 180;
    var dist =
      Math.sin(radlat1) * Math.sin(radlat2) +
      Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
    if (dist > 1) {
      dist = 1;
    }
    dist = Math.acos(dist);
    dist = (dist * 180) / Math.PI;
    dist = dist * 60 * 1.1515;
    return dist;
  }
}

module.exports = {
  findAirports,
  test,
  addToDatabase
};
