import React, { useEffect, useState } from "react";
import {  Link, useNavigate } from "react-router-dom";
import RecordService from "../../services/RecordService";

const Record = ({record}) =>{
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        const fetchData = async() =>{
            setLoading(true);
            try{
                const response = await RecordService.getArtist(record.id)
                record.artist=response.data.name;
            }catch(error){
                console.log(error);
            }
            setLoading(false);
        };
        fetchData();
    }, []);

    const deleteRecord = async (a, id) => {
        a.preventDefault();
        await RecordService.deleteRecord(id);
        window.location.href = "/recordList";
    }
    return (
        <tr key = {record.id}  style={{textAlign: "center"}}>
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
                    record.parent === null ? "None":<a href={record._links.parent.href}>{record.parent.name}</a>
                }</div>
            </td>
            <td>
                <div>
                    {record.artist}
                </div>
            </td>
            <td width={75}>
                {/* <a onClick={(a, id) => editRecord(a, record.id)} href="/updateRecord">
                    Edit
                </a> */}
                    <Link to={`/updateRecord/${record.id}`}>
                        Edit
                    </Link>
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