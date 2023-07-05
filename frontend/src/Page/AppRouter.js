import Layout from './Layout';
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import { setAuthToken } from '../services/setAuthToken';
import Login from './Login';
import Logout from './Logout';
import HomePage from './HomePage';
import UpdateArtist from '../components/Artist/UpdateArtist';
import AddArtist from '../components/Artist/AddArtist';
import ArtistList from '../components/Artist/ArtistList';
import UpdateRecord from '../components/Record/UpdateRecord';
import AddRecord from '../components/Record/AddRecord';
import RecordList from '../components/Record/RecordList';
function AppRouter(){
    const token = localStorage.getItem("token");
    if (token) {
        setAuthToken(token);
    }
    return(
    <BrowserRouter>
        <Routes>
          {/* <Route path="/" element={<Layout />}>
              <Route index element={<HomePage />} />
              <Route path="artists" element={<Artists />} />
              <Route path="add" element={<AddArtist />} />
          </Route> */}
          <Route path="/" element={<Layout />}>
            <Route index element={<HomePage/>}/>
            <Route path='/' element={<HomePage/>}/>
            <Route path="/artistList" element={<ArtistList />} />
            <Route path="/addArtist" element={<AddArtist />} />
            <Route path="/updateArtist" element={<UpdateArtist />} />
            <Route path="/recordList" element={<RecordList />} />
            <Route path="/addRecord" element={<AddRecord />} />
            <Route path="/updateRecord" element={<UpdateRecord />} />
            <Route path="/login" element={<Login />} />
            <Route path='/logout' element={<Logout />} />
          </Route>
        </Routes>
      </BrowserRouter>
    )
}

export default AppRouter;