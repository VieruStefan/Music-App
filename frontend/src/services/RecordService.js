import axios from "axios";

const RECORD_API_BASE_URL = "http://localhost:7000/api/songcollection/songs";

const parseJwt = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
};
const checkExp = () =>{
    console.log("checking...");
    var token = localStorage.getItem("token");
    var decodedJwt = parseJwt(token);
    if(decodedJwt.exp * 1000 < Date.now())
    {
        localStorage.clear();
        alert("Logged out!");
    }
}
class RecordServiceClass{
    
    saveRecord(record){
        checkExp();
        var token = localStorage.getItem("token");
        return axios.put(RECORD_API_BASE_URL , {
            name: record.name,
            genre: record.genre,
            year: record.year,
            type: record.type
        }, {headers: {"Authorization" : `Bearer ${token}`}})
    }

    getRecords(){
        checkExp();
        return axios.get(RECORD_API_BASE_URL);
    }

    getRecordById(id){
        checkExp();
        return axios.get(RECORD_API_BASE_URL + "/" + id);
    }

    updateRecord(record, id){
        checkExp();
        var token = localStorage.getItem("token");
        return axios.put(RECORD_API_BASE_URL, {
            id: parseInt(record.id),
            name: record.name,
            genre: record.genre,
            year: record.year,
            type: record.type
        }, {headers: {"Authorization" : `Bearer ${token}`}});
    }

    deleteRecord(id){
        checkExp();
        var token = localStorage.getItem("token");
        return axios.delete(RECORD_API_BASE_URL + "/" + id, {headers: {"Authorization" : `Bearer ${token}`}});
    }
}
const RecordService = new RecordServiceClass()
export default RecordService;