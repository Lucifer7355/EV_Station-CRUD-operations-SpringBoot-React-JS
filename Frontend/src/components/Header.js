import { Link } from "react-router-dom";
function Header() {
    return (
      <div className="App">
        <nav className="navbar navbar-light" style={{"background-color" : "rgb(227 242 253)"}}>
      
    <span className="navbar-brand mb-0 h1" style={{ marginLeft: "562px" }} ><Link to="/">⛽ EV Station CRUD WEB APPLICATION ⛽</Link></span>
    <span className="navbar-brand mb-0 h1"  ><Link to="/creating">Create</Link></span>
    <span className="navbar-brand mb-0 h1"  ><Link to="/pagination">Show only 10 records</Link></span>
  </nav>
     
      </div>
    );
  }
  
  export default Header;
  