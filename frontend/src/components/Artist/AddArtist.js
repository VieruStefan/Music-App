import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ArtistService from "../../services/ArtistService";

const AddArtist = () => {
  const [artist, setArtist] = useState({
    uuid: "",
    name: "",
    active: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setArtist({ ...artist, [e.target.name]: value });
  };

  const saveArtist = (e) => {
    e.preventDefault();
    ArtistService.saveArtist(artist, artist.uuid)
      .then((response) => {
        console.log(response);
        navigate("/artistList");
      })
      .catch((error) => {
        console.log(error);
        alert("Not enough permissions!");
        window.location.href = '/';
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setArtist({
        uuid: "",
        name: "",
        active: "",
    });
  };

  return (
    <div>
      <div>
        <div>
          <h1>Add New Artist</h1>
        </div>
        <div>
          <label>Uuid</label>
          <input
            type="text"
            name="uuid"
            value={artist.uuid}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>Name</label>
          <input
            type="text"
            name="name"
            value={artist.name}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>Is he active?</label>
          <input
            type="text"
            name="active"
            value={artist.active}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <button
            onClick={saveArtist}>
            Save
          </button>
          <button
            onClick={reset}>
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddArtist;