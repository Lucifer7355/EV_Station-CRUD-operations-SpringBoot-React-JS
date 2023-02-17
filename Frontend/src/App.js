import Tables from "./components/Tables";
import {
  BrowserRouter as Router,
  Routes ,
  Route
} from "react-router-dom";
import UpdateFields from "./components/UpdateFields";
import Combined from "./components/Combined";
import Create from "./components/Create"
import Paged from "./components/Paged";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes >
        <Route exact path='/' element={<Combined/>}/>
        <Route exact path='/updating' element={<UpdateFields/>}/>
        <Route exact path='/creating' element={<Create/>}/>
        <Route exact path='/pagination' element={<Paged/>}/>
        </Routes >
    </Router>
    </div>
  );
}

export default App;
