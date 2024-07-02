import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import './App.css';
import BoardList from './routes/BoardList';
import Home from './routes/Home';
import Login from './routes/Login';
import QnaList from './routes/QnaList';
//화면 라우팅을 위한 라이브러리 추가

function App() {
  return (
    <Routes>
      {/* a, Link를 누르면 화면 전환될 페이지 */}
        <Route path='/home' element={<Home/>}/>
        <Route path='/boardList' element={<BoardList/>}/>
        <Route path='/qnaList' element={<QnaList/>}/>
        <Route path='/login' element={<Login/>}/>
    </Routes>
  );
}

export default App;
