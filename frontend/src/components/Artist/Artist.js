import React from "react";
import { Link, useNavigate } from "react-router-dom";
import ArtistService from "../../services/ArtistService";

const Artist = ({artist}) =>{
    const navigate = useNavigate();
    const editArtist = (a, uuid) => {
        a.preventDefault();
        console.log(uuid);
        navigate(`/updateArtist/?uuid=${uuid}`);
    }
    const deleteArtist = async (a, uuid) => {
        a.preventDefault();
        await ArtistService.deleteArtist(uuid);
        window.location.href = "/artistList";
    }
    return (
        <tr key = {artist.uuid} style={{textAlign: "center"}}>
            <td>
                <div>{artist.name}</div>
            </td>
            <td>
                <div>{String(artist.active)}</div>
            </td>
            <td>
                <a href={artist._links.songs.href}>Link</a>
            </td>
            <td width={75}>
                {/* <a onClick={(a, uuid) => editArtist(a, artist.uuid)} href="/updateArtist">
                    Edit
                </a> */}
                    <Link to={`/updateArtist/${artist.uuid}`}>
                        Edit
                    </Link>
            </td> 
            <td width={75}>
                <a onClick={(a, uuid) => deleteArtist(a, artist.uuid)} href="/deleteArtist">
                    Delete
                </a>
            </td> 
        </tr>
    );
};

export default Artist;