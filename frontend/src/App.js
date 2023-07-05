import {useState} from 'react';import React from 'react';

const App = () => {
  const [data, setData] = useState({artistList:[]});
  const [isLoading, setIsLoading] = useState(false);
  const [err, setErr] = useState('');
  const handleClick = async () => {
    setIsLoading(true);

    try {
      const response = await fetch('/api/songcollection/artists', {
        method: 'GET',
        headers: {
          Accept: 'application/json',
        },
      });

      if (!response.ok) {
        throw new Error(`Error! status: ${response.status}`);
      }

      const result = await response.json();

      console.log('result is: ', JSON.stringify(result, null, 4));
      setData(result._embedded);
      //setData(result._embedded.artistList);
    } catch (err) {
      setErr(err.message);
    } finally {
      setIsLoading(false);
    }
  };
  console.log("alooo")
  console.log(data);
  return (
    <div>
      {err && <h2>{err}</h2>}

      <button onClick={handleClick}>Fetch data</button>

      {isLoading && <h2>Loading...</h2>}

      {data.artistList.map(artist => {
        return (
          <div key={artist.uuid}>
            <h2>{artist.name}</h2>
            <h2>{String(artist.active)}</h2>
            <br />
          </div>
        );
      })}
    </div>
  );
};

export default App;
