import { useState } from 'react';
import './App.css';
import CustomButton from './component/CustomButton';
import IncButton from './component/IncButton';

//데이터 생성시는 보통 const
const ironMan = {
  name : 'Tony Stark',
  heroName : 'Iron Man',
  imgUrl : 'https://img.danawa.com/prod_img/500000/207/533/img/18533207_1.jpg?_v=20221226163359',
  imgSize : 100
}


const weapons = [
  {
      title : 'Repulsor Beam',
      idx : 1
  },
  {
      title : 'Unibeam Blaster',
      idx : 2
  },
  {
      title : 'Smart mis',
      idx : 3
  },
  {
      title : 'Razor cutter',
      idx : 4
  }
];


const listWeapons = weapons.map(weapon =>{
  return(
    <li key={weapon.idx}>
      {weapon.title}
    </li>
  )
});



function App() {
  const [count,setCount] = useState(0);
  const handleClick = () =>{
      setCount(count +1);
  }
  return (
    <div className="App">
      <header className="App-header">
        <h1>{ironMan.heroName}</h1>
        <img className='profile'
          src={ironMan.imgUrl}
          alt={ironMan.name}
          style={{
                width:ironMan.imgSize,
                height:ironMan.imgSize,
              }}
        />
        <ul>
          {listWeapons}
        </ul>
        <IncButton count={count} onClick={handleClick}/>
        <IncButton count={count} onClick={handleClick}/>
        <CustomButton data={ironMan}/>
      </header>
    </div>
  );
}

export default App;
