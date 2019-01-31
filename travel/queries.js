const Pool = require("pg").Pool;
const pool = new Pool({
  user: "me",
  host: "localhost",
  database: "api",
  password: "password",
  port: 5432
});

var verifyUser = (username, password, response) => {
  var success = 0;
  pool.query(
    "SELECT * FROM user_travel WHERE email=$1 and password=$2",
    [username, password],
    (error, results) => {
      if (error) {
        throw error;
      }
      success = results.rowCount;
      if (success) {
        response.status(200).json({
          status: {
            type: "success",
            message: "Authenticated",
            code: "200",
            error: "false"
          }
        });
      }
    }
  );
};

const getUsers = (request, response) => {
  pool.query("SELECT * FROM user_travel ORDER BY id ASC", (error, results) => {
    if (error) {
      throw error;
    }
    response.status(200).json(results.rows);
  });
};

const getUserById = (request, response) => {
  const id = parseInt(request.params.id);

  pool.query(
    "SELECT * FROM user_travel WHERE id = $1",
    [id],
    (error, results) => {
      if (error) {
        throw error;
      }
      response.status(200).json(results.rows);
    }
  );
};

const authorizeUser = (request, response) => {
  const { username, password } = request.body;
  //console.log(request.body.username);
  verifyUser(username, password, response);
};

const addUser = (request, response) => {
  const { username, password } = request.body;
  pool.query(
    "INSERT INTO user_travel (email, password, dob) VALUES ($1, $2, '01-01-1996')",
    [username, password],
    (error, results) => {
      if (error) {
        response.status(400).json({
          status: {
            type: "failure",
            message: error,
            code: "400",
            error: "true"
          }
        });
      } else {
        console.log(results);
        response.status(200).json({
          status: {
            type: "success",
            message: "User added to the database",
            code: "200",
            error: "false"
          }
        });
      }
    }
  );
};

const updateUser = (request, response) => {
  const id = parseInt(request.params.id);
  const { name, password } = request.body;

  pool.query(
    "UPDATE user_travel SET name = $1, password = $2 WHERE id = $3",
    [name, password, id],
    (error, results) => {
      if (error) {
        throw error;
      }
      console.log(results);
      response.status(200).send(`User modified with ID: ${id}`);
    }
  );
};

const deleteUser = (request, response) => {
  const id = parseInt(request.params.id);

  pool.query(
    "DELETE FROM user_travel WHERE id = $1",
    [id],
    (error, results) => {
      if (error) {
        throw error;
      }
      response.status(200).send(`User deleted with ID: ${id}`);
    }
  );
};

module.exports = {
  getUsers,
  getUserById,
  authorizeUser,
  addUser,
  updateUser,
  deleteUser
};
