import axios from "axios";
import { useState } from "react";
import {  useNavigate } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";

function Create() {
    const history = useNavigate();
    const [Name,setName] = useState();
    const [Address,setAddress] = useState();
    const [Price,setPrice] = useState();
    const [Image,setImage] = useState("no image");
    const [file, setFile] = useState("no");
    
    return (
      <div className="please" >
        <Header/>
        {
            file==="no" ? 
        <img  style={{ marginTop: "124px", marginLeft: "589px" }} alt="your uploaded station image will appear here" width='200px' height='200px' src={`data:image/png;base64,${Image}`}/>
        :
        <img src={file} style={{ marginTop: "69px", marginLeft: "651px" }} alt="station_image" width='200px' height='200px'/>
        }
       <form style={{ marginTop: "58px",marginLeft: "599px" }}>
  <div className="col col-md-4" >
    <div className="row">
      <input type="text" className="form-control" style={{ marginBottom: "8px" }} placeholder="Station Name" value={Name} onChange={(e) => setName(e.target.value)}/>
    </div>
    <div className="row">
      <input type="text" className="form-control" style={{ marginBottom: "8px" }} placeholder="Station Address" value={Address} onChange={(e) => setAddress(e.target.value)}/>
    </div>
    <div className="row">
      <input type="text" className="form-control" style={{ marginBottom: "8px" }}placeholder="Station Price" value={Price} onChange={(e) => setPrice(e.target.value)}/>
    </div>
  </div>
</form>

<div className="going" style={{ marginLeft: "580px", marginTop: "50px" }}>

<input type="file" onChange={(e) => {
     console.log(e.target.files);
     setFile(URL.createObjectURL(e.target.files[0])); // for displaying
     setImage(e.target.files[0]);//for sending
   
    }}/>

<button type="button" className="btn btn-success" onClick={async ()=>{
   const formData = new FormData();
   formData.append('station_name', Name);
   formData.append('station_image', Image);
   formData.append('station_pricing', Price);
   formData.append('station_address', Address);
   axios({
    method: 'post',
    url: `http://localhost:6012/api/v1/addStation`,
    data: formData,
    headers: {'Content-Type': 'multipart/form-data' }
 })
 
 window.location.href = "/";
}}>Create</button>
</div>
<Footer/>
      </div>
    );
  }
  
  export default Create;
  