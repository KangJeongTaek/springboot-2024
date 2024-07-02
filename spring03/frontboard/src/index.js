import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import './index.css';

//  만든 페이지 추가
import Footer from './layout/Footer';
import Header from './layout/Header';



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <div className='App h-full w-full'>
    <BrowserRouter>
      <div id='wrapper' className='flex flex-col h-screen'>
          {/* head */}
          {/* 헤더 페이지 추가 */}
          <Header/>
          {/* main-content */}
          <div id='main-content' className='flex-1 main'>
            <App/>
          </div>
          {/* footer   */}
          <Footer/>
      </div>
    </BrowserRouter>
  </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals

