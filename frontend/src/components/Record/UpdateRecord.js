import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import RecordService from "../../services/RecordService";

const UpdateRecord = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [record, setRecord] = useState({
    id: id,
    name: "",
    genre: "",
    type: "",
    year: "",
    parent: ""
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setRecord({ ...record, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await RecordService.getRecordById(record.id);
        setRecord(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
    // eslint-disable-next-line
  }, []);

  const UpdateRecord = (e) => {
    e.preventDefault();
    console.log(record);
    RecordService.updateRecord(record, record.id)
      .then((response) => {
        navigate("/recordList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <div>
        <div>
          <h1>Update Record</h1>
        </div>
        <div>
          <label>
            ID
          </label>
          <input
            type="text"
            name="id"
            value={record.id}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>
            Name
          </label>
          <input
            type="text"
            name="name"
            value={record.name}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>
            Genre
          </label>
          <input
            type="text"
            name="genre"
            value={record.genre}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>
            Type
          </label>
          <input
            type="text"
            name="type"
            value={record.type}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>
            Year
          </label>
          <input
            type="text"
            name="year"
            value={record.year}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div>
          <label>
            Parent
          </label>
          <input
            type="text"
            name="parent"
            value={record.parent}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div >
          <button
            onClick={UpdateRecord}>
            Update
          </button>
          <button
            onClick={() => navigate("/recordList")}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default UpdateRecord;
