import React from "react";
import BeerList from "./BeerList";
import "./App.css";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

// https://fettblog.eu/typescript-react-component-patterns/
// https://www.sitepoint.com/react-with-typescript-best-practices/
// https://www.pluralsight.com/guides/react-communicating-between-components
// https://www.youtube.com/watch?v=no82oluCZag
// https://www.youtube.com/watch?v=LuxYWWB3_Qc&t=1294s
// https://www.youtube.com/watch?v=AhnPjl5rovQ

const App = () => (
    <div className="App">
        <BeerList />
        <Link to="/about">About</Link>
    </div>
);

export default App;
