import React from "react";
import KanbanList from "./KanbanList";
import "./App.css";
import { Link } from "react-router-dom";

// https://fettblog.eu/typescript-react-component-patterns/
// https://www.sitepoint.com/react-with-typescript-best-practices/
// https://www.pluralsight.com/guides/react-communicating-between-components
// https://www.youtube.com/watch?v=no82oluCZag
// https://www.youtube.com/watch?v=LuxYWWB3_Qc&t=1294s
// https://www.youtube.com/watch?v=AhnPjl5rovQ
// https://www.freecodecamp.org/news/building-a-simple-pokemon-web-app-with-react-hooks-and-context-api/
// https://medium.com/swlh/interacting-with-restful-apis-using-typescript-react-hooks-and-axios-part-1-af52920ae3e4

const App = () => (
    <div className="App">
        <KanbanList />
        <div className="pt-2">
            <Link to="/about">About</Link>
        </div>
    </div>
);

export default App;
