import React from "react";
import { Route, Switch } from "react-router-dom";
import Home from "./containers/Home";
import NotFound from "./containers/NotFound";
import Login from "./containers/Login";
import Signup from "./containers/Signup";
import DisplaySearchResults from "./containers/DisplaySearchResults";
import AppliedRoute from "./components/AppliedRoute";

export default ({ childProps }) => (
  <Switch>
    <AppliedRoute path="/" exact component={Login} props={childProps} />
    <AppliedRoute path="/home" exact component={Home} props={childProps} />
    <AppliedRoute path="/signup" exact component={Signup} />
    <AppliedRoute
      path="/display-results"
      exact
      component={DisplaySearchResults}
      props={childProps}
    />
    <Route component={NotFound} />
  </Switch>
);
