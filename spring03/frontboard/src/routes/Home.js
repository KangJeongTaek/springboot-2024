import React from 'react';

const Home = () => {
    let username = localStorage.getItem("username");
    let mail = localStorage.getItem("email");
    let role = localStorage.getItem("role");
    let loginDt = localStorage.getItem("loginDt");
    return (
        <div className="container card" style={{maxWidth:'300px'}}>
            <h4>로그인 정보</h4>
            <div>
                <label className='form-label'>
                    {username}
                </label><br/>
                <label className='form-label'>
                    {mail}
                </label><br/>
                <label className='form-label'>
                    {role}
                </label><br/>
                <label className='form-label'>
                    {loginDt}
                </label>
            </div>
        </div>
    );
};

export default Home;