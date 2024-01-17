import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import RecordService from "../../services/RecordService";

const UpdateRecord = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [record, setRecord] = useState({
    id: id,
    name: "",
    genre: "",
    type: "",
    year: "",
    parent_name: ""
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setRecord({ ...record, [e.target.name]: value });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await RecordService.getRecordById(record.id);
        setRecord((prevRecord) => ({
          ...response.data,
          parent_name: response.data.parent !== null ? response.data.parent.name : prevRecord.parent_name
        }));
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
    // eslint-disable-next-line
  }, [id]);

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
    <div className="form-update">
      <div>
        <div>
          <h2>Update Record</h2>
        </div>
        <div className="input-update">
          <label>
            ID
          </label>
          <input
            type="text"
            name="id"
            value={id}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <label>
            Name
          </label>
          <input
            type="text"
            name="name"
            value={record.name}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <label>
            Genre
          </label>
          <input
            type="text"
            name="genre"
            value={record.genre}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <label>
            Type
          </label>
          <input
            type="text"
            name="type"
            value={record.type}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <label>
            Year
          </label>
          <input
            type="text"
            name="year"
            value={record.year}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <label>
            Parent
          </label>
          <input
            type="text"
            name="parent_name"
            value={record.parent_name}
            onChange={(e) => handleChange(e)} />
        </div>
        <div className="input-update">
          <button className="button" onClick={UpdateRecord}>
            Update
          </button>
          <button className="button" onClick={() => navigate("/recordList")}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  </div>
  );
};

export default UpdateRecord;
