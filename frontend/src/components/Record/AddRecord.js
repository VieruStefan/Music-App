import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import RecordService from "../../services/RecordService";

const AddRecord = () => {
  const [record, setRecord] = useState({
    id: "",
    name: "",
    genre: "",
    type: "",
    year: "",
    parent: ""
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setRecord({ ...record, [e.target.name]: value });
  };

  const saveRecord = (e) => {
    e.preventDefault();
    RecordService.saveRecord(record, record.id)
      .then((response) => {
        console.log(response);
        navigate("/recordList");
      })
      .catch((error) => {
        console.log(error);
        alert("Not enough permissions!");
        window.location.href = '/';
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setRecord({
        id: "",
        name: "",
        genre: "",
        type: "",
        year: "",
        parent: ""
    });
  };

  return (
    <div>
      <div className="form-update">
        <div>
          <h2>Add New Record</h2>
        </div>
        {/* <div>
          <label>ID</label>
          <input
            type="text"
            name="id"
            value={record.id}
            onChange={(e) => handleChange(e)}></input>
        </div> */}
        <div className="input-update">
          <label>Name</label>
          <input
            type="text"
            name="name"
            value={record.name}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div className="input-update">
          <label>
            Genre
          </label>
          <input
            type="text"
            name="genre"
            value={record.genre}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div className="input-update">
          <label>
            Type
          </label>
          <input
            type="text"
            name="type"
            value={record.type}
            onChange={(e) => handleChange(e)}></input>
        </div>
        <div className="input-update">
          <label>
            Year
          </label>
          <input
            type="text"
            name="year"
            value={record.year}
            onChange={(e) => handleChange(e)}></input>
        </div>
        {/* <div>
          <label>
            Parent
          </label>
          <input
            type="text"
            name="parent"
            value={record.parent}
            onChange={(e) => handleChange(e)}></input>
        </div> */}
        <div className="input-update">
          <button
            onClick={saveRecord}>
            Save
          </button>
          <button className="button"
            onClick={reset}>
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddRecord;