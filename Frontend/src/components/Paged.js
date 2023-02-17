import React, { useEffect, useState } from "react";
import axios from "axios";
import Header from "./Header";
import Footer from "./Footer";
import { useNavigate } from "react-router-dom";

function Tables() {
    const [Stations,setStations] = useState([]);
    const history = useNavigate();
    const [IndexClicked,setIndexClicked] = useState(false);
    const [AddressClicked,setAddressClicked] = useState(false);
    const [Pricing,setPricing] = useState(false);
    const [Name,setName] = useState(false);
    const getData = async () => {
        const { data } = await axios.get(`http://localhost:6012/api/v1/getbylimit?limit=10`);
        console.log(typeof data[0].station_image);
        setStations(data);
      };

    useEffect(() => {
        getData();
      }, [Stations.length]);

    return (
        <>
        <Header/>
           <div className="Tables" style={{maxHeight: "50rem",
overflow: "auto"}}>
            
                     <table className="table table-hover" style={{ marginTop: "6px" }}>
                            <thead>
                            <tr>
                                <th scope="col"  >Index</th>
                                <th scope="col"  >Station_Name</th>
                                <th scope="col" >Station_Image</th>
                                <th scope="col"  >Station_Address</th>
                                <th scope="col"  >Station_Pricing</th>
                                <th scope="col" >Update_Detail</th>
                                <th scope="col" >Delete_Station</th>
                            </tr>
                            </thead>
                            {
                            Stations.map((it,index) =>
                            <tbody key={it.station_id}>
                            <tr >
                                <th scope="row">{index+1}</th>
                                <td>{it.station_name}</td>
                                <td>{<img alt="station_image" width='132px' height='118px' src={`data:image/png;base64,${it.station_image}`}/>}</td>
                                <td>{it.station_address}</td>
                                <td>{it.station_pricing}</td>
                                <td><button type="button" className="btn btn-outline-warning" onClick={()=>{
                                    history("/updating",{state : it});
                                }}>Update</button></td>
                                <td><button type="button" className="btn btn-outline-danger" onClick={ async ()=>{
                                    await axios.delete(`http://localhost:6012/api/v1/deleteStation/${it.station_id}`);
                                    window.location.reload();
                                }}>Delete</button></td>
                                
                            </tr>
                            
                            </tbody>
                            )
                                }
                      </table>
                     
            </div>
           </>
       
    );
  }
  
  export default Tables;
  