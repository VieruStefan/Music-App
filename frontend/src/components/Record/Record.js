import React from "react";
import { useNavigate } from "react-router-dom";
import RecordService from "../../services/RecordService";

const Record = ({record}) =>{
    const navigate = useNavigate();
    const editRecord = (a, id) => {
        a.preventDefault();
        console.log(id);
        navigate(`/updateRecord`)
    }
    const deleteRecord = async (a, id) => {
        a.preventDefault();
        await RecordService.deleteRecord(id);
        window.location.href = "/recordList";
    }
    return (
        <tr key = {record.id}>
            <td>
                <div>{record.name}</div>
            </td>
            <td>
                <div>{record.genre}</div>
            </td>
            <td>
                <div>{record.type}</div>
            </td>
            <td>
                <div>{record.year}</div>
            </td>
            <td>
                <div>{
                    record.parent === null ? "None":record.parent.name
                }</div>
            </td>
            <td width={75}>
                <a onClick={(a, id) => editRecord(a, record.id)} href="/updateRecord">
                    Edit
                </a>
            </td> 
            <td width={75}>
                <a onClick={(a, id) => deleteRecord(a, record.id)} href="/deleteRecord">
                    Delete
                </a>
            </td> 
        </tr>
    );
};

export default Record;