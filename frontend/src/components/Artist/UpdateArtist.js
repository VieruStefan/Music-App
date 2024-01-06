import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ArtistService from "../../services/ArtistService";

const UpdateArtist = () => {
  const { uuid } = useParams();
  const navigate = useNavigate();
  const [artist, setArtist] = useState({
    uuid: uuid,
    name: "",
    active: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setArtist({ ...artist, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await ArtistService.getArtistByUUID(artist.uuid);
        setArtist(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
    // eslint-disable-next-line
  }, []);

  const UpdateArtist = (e) => {
    e.preventDefault();
    console.log(artist);
    ArtistService.updateArtist(artist, artist.uuid)
      .then((response) => {
        navigate("/artistList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <div className="form-update">
        <div>
          <h2>Update Artist</h2>
        </div>
        <div className="input-update">
          <label>
            Uuid
          </label>
          <input
            type="text"
            name="uuid"
            value={artist.uuid}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div className="input-update">
          <label>
            Name
          </label>
          <input
            type="text"
            name="name"
            value={artist.name}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div className="input-update">
          <label>
            Active
          </label>
          <input
            type="text"
            name="active"
            value={artist.active}
            onChange={(e) => handleChange(e)}></input>
        </div>

        <div className="input-update">
          <button className="button"
            onClick={UpdateArtist}>
            Update
          </button>
          <button  className="button"
            onClick={() => navigate("/artistList")}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpdateArtist;
