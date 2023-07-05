import { useEffect, useState } from "react";
//import { useNavigate } from "react-router-dom"
import ArtistService from '../../services/ArtistService';
import Artist from './Artist';
import '../../ArtistList.css';

const ArtistList = () =>{
    //const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [artists, setArtists] = useState(null);

    useEffect(() =>{
        const fetchData = async() =>{
            setLoading(true);
            try{
                const response = await ArtistService.getArtists();
                console.log(response.data._embedded.artistList);
                setArtists(response.data._embedded.artistList);
            }catch(error){
                console.log(error);
                alert("Log in!");
                window.location.href = '/login';
            }
            setLoading(false);
        };
        fetchData();
    }, []);
    return (
        <div>
            <table>
              <thead >
                <tr>
                  <th>Name</th>
                  <th>Active</th>
                  <th colSpan={2}>Action</th>
                </tr>
              </thead>
              {!loading && (
                <tbody>
                  {artists.map((artist) => (
                    <Artist
                      artist={artist}
                      key={artist.uuid}></Artist>
                  ))}
                </tbody>
              )}
            </table>
        </div>
    );
};

export default ArtistList;
