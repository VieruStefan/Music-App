import { Outlet, Link } from "react-router-dom";
import React, { useState, useEffect } from 'react';
const Layout = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const jwt = localStorage.getItem('token');
    setIsLoggedIn(jwt);
  }, []);

  return (
    <div>
    <table className="navbar">
      <tbody>
          <tr>
              <td width={80} align="center"><Link to="/">Home</Link></td>
              <td width={80} align="center"><Link to="/artistList">Artists</Link></td>
              <td width={80} align="center"><Link to="/addArtist">Add artist</Link></td>
              {/* <td width={80} align="center"><Link to="/updateArtist">Update artist</Link></td> */}
              <td width={80} align="center"><Link to="/recordList">Records</Link></td>
              <td width={80} align="center"><Link to="/addRecord">Add record</Link></td>
              {/* <td width={80} align="center"><Link to="/updateRecord">Update record</Link></td> */}
              <td width={80} align="center" hidden={isLoggedIn}><Link to="/login" >Login</Link></td>
              <td width={80} align="center" hidden={!isLoggedIn}><Link to="/logout">Logout</Link></td>
          </tr>
        </tbody>
    </table>


    <Outlet />
    </div>
  )
};

export default Layout;
