import { useEffect, useState } from "react";
//import { useNavigate } from "react-router-dom"
import RecordService from '../../services/RecordService';
import Record from './Record';
import '../../ArtistList.css';

const RecordList = () =>{
    //const navigate = useNavigate();

    const [loading, setLoading] = useState(true);
    const [records, setRecords] = useState(null);

    useEffect(() =>{
        const fetchData = async() =>{
            setLoading(true);
            try{
                const response = await RecordService.getRecords();
                console.log("list");
                console.log(response.data._embedded.recordList);
                setRecords(response.data._embedded.recordList);
            }catch(error){
                console.log(error);
                alert("Nothing here yet!");
                window.location.href = '/';
            }
            setLoading(false);
        };
        fetchData();
    }, []);
    return (
        <div>
            <table>
              <thead >
                <tr>
                  <th>Name</th>
                  <th>Genre</th>
                  <th>Type</th>
                  <th>Year</th>
                  <th>Parent</th>
                  <th colSpan={2}>Action</th>
                </tr>
              </thead>
              {!loading && (
                <tbody>
                  {records.map((record) => (
                    <Record
                      record={record}
                      key={record.id}></Record>
                  ))}
                </tbody>
              )}
            </table>
        </div>
    );
};

export default RecordList;
