import React from "react";
import { useNavigate } from "react-router-dom";
import ArtistService from "../../services/ArtistService";

const Artist = ({artist}) =>{
    const navigate = useNavigate();
    const editArtist = (a, uuid) => {
        a.preventDefault();
        console.log(uuid);
        navigate(`/updateArtist`)
    }
    const deleteArtist = async (a, uuid) => {
        a.preventDefault();
        await ArtistService.deleteArtist(uuid);
        window.location.href = "/artistList";
    }
    return (
        <tr key = {artist.uuid}>
            <td>
                <div>{artist.name}</div>
            </td>
            <td>
                <div>{String(artist.active)}</div>
            </td>
            <td width={75}>
                <a onClick={(a, uuid) => editArtist(a, artist.uuid)} href="/updateArtist">
                    Edit
                </a>
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