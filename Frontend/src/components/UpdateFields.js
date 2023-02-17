import axios from "axios";
import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Header from "./Header";

function UpdateFields() {
    const location = useLocation();
    const it = location.state;
    console.log(location);
    const history = useNavigate();
    const [Name,setName] = useState(it.station_name);
    const [Address,setAddress] = useState(it.station_address);
    const [Price,setPrice] = useState(it.station_pricing);
    const [Image,setImage] = useState(it.station_image);
    const [file, setFile] = useState(it.station_image);
    
    return (
      <div className="please" >
        <Header/>
        {
            file===it.station_image ? 
        <img  style={{ marginTop: "69px", marginLeft: "651px" }} alt="station_image" width='200px' height='200px' src={`data:image/png;base64,${Image}`}/>
        :
        <img src={file} style={{ marginTop: "69px", marginLeft: "651px" }} alt="station_image" width='200px' height='200px'/>
        }
       <form style={{ marginTop: "58px",marginLeft: "599px" }}>
  <div className="col col-md-4" >
    <div className="row">
      <input type="text" className="form-control" placeholder="Station Name" value={Name} onChange={(e) => setName(e.target.value)}/>
    </div>
    <div className="row">
      <input type="text" className="form-control" placeholder="Station Address" value={Address} onChange={(e) => setAddress(e.target.value)}/>
    </div>
    <div className="row">
      <input type="text" className="form-control" placeholder="Station Price" value={Price} onChange={(e) => setPrice(e.target.value)}/>
    </div>
  </div>
</form>

<div className="going" style={{ marginLeft: "580px", marginTop: "50px" }}>

<input type="file" onChange={(e) => {
     console.log(e.target.files);
     setFile(URL.createObjectURL(e.target.files[0])); // for displaying
     setImage(e.target.files[0]);//for sending
    //  const reader = new FileReader();
    // reader.readAsDataURL(e.target.files[0]);
    // reader.onloadend = () => {
    //   setImage(reader.result);
    // };
    }}/>

<button type="button" className="btn btn-success" onClick={async ()=>{
   const formData = new FormData();
   formData.append('station_name', Name);
   formData.append('station_image', Image);
   formData.append('station_pricing', Price);
   formData.append('station_address', Address);
   axios({
    method: 'put',
    url: `http://localhost:6012/api/v1/updateStation/${it.station_id}`,
    data: formData,
    headers: {'Content-Type': 'multipart/form-data' }
 })
 
 window.location.href = "/";
 
}}>Update</button>
</div>
      </div>
    );
  }
  
  export default UpdateFields;
  