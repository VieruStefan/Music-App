import axios from "axios";

const ARTIST_API_BASE_URL = "http://localhost:7000/api/songcollection/artists";

const parseJwt = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
};
const checkExp = () =>{
    var token = localStorage.getItem("token");
    var decodedJwt = parseJwt(token);
    if(decodedJwt.exp * 1000 < Date.now())
    {
        localStorage.clear();
        alert("Logged out!");
    }
}
class ArtistServiceClass{
    
    saveArtist(artist, uuid){
        checkExp()
        var token = localStorage.getItem("token");
        return axios.put(`${ARTIST_API_BASE_URL}/${uuid}`, artist, {headers: {"Authorization" : `Bearer ${token}`}})
    }

    getArtists(){
        return axios.get(ARTIST_API_BASE_URL);
    }

    getArtistByUUID(uuid){
        return axios.get(`${ARTIST_API_BASE_URL}/${uuid}`);
    }

    getArtistSongs(uuid){
        return axios.get(`${ARTIST_API_BASE_URL}/${uuid}/songs`);
    }

    updateArtist(artist, uuid){
        checkExp()
        var token = localStorage.getItem("token");
        return axios.put(`${ARTIST_API_BASE_URL}/${uuid}`, artist, {headers: {"Authorization" : `Bearer ${token}`}});
    }

    deleteArtist(uuid){
        checkExp()
        var token = localStorage.getItem("token");
        return axios.delete(`${ARTIST_API_BASE_URL}/${uuid}`, {headers: {"Authorization" : `Bearer ${token}`}});
    }
}
const ArtistService = new ArtistServiceClass()
export default ArtistService;