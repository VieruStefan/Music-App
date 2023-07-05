import { Outlet, Link } from "react-router-dom";

const Layout = () => {
  return (
    <>
    <table>
      <tbody>
          <tr>
              <td width={80} align="center"><Link to="/">Home</Link></td>
              <td width={80} align="center"><Link to="/artistList">Artists</Link></td>
              <td width={80} align="center"><Link to="/addArtist">Add artist</Link></td>
              <td width={80} align="center"><Link to="/updateArtist">Update artist</Link></td>
              <td width={80} align="center"><Link to="/recordList">Records</Link></td>
              <td width={80} align="center"><Link to="/addRecord">Add record</Link></td>
              <td width={80} align="center"><Link to="/updateRecord">Update record</Link></td>
              <td width={80} align="center"><Link to="/login">Login</Link></td>
              <td width={80} align="center"><Link to="/logout">Logout</Link></td>
          </tr>
        </tbody>
    </table>


      <Outlet />
    </>
  )
};

export default Layout;
