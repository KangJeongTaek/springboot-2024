import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
const Login = () => {
    const [user,setUser] = useState({
        username : '',
        password : '',
    });

const handleChange = (e) =>{
    const {name,value} = e.target;
    setUser({...user,
    [name]: value});
}
const navigate = useNavigate();


const handleSubmit = async(e) =>{
    e.preventDefault(); // submit 동안 다른 이벤트가 발생하지 않도록 중지시키는 것

    try {
        const formData = new FormData();
        formData.append('username', user.username);
        formData.append('password',user.password);
        const resp = await axios({
            url : 'http://localhost:8088/api/member/login', //rest API 호출
            method:'POST', // GET, POST,DELETE,PUT
            data:formData,
            withCredentials:true
        });
        if(resp.data.resultCode === 'OK'){
            const {mid,username,email,role} = resp.data.data
            const transactionTime = resp.data.transactionTime;

            localStorage.setItem('username',username);
            localStorage.setItem('email',email);
            localStorage.setItem('mid',mid);
            localStorage.setItem('role',role);
            localStorage.setItem('loginDt', transactionTime);
            console.log(localStorage);
            alert('로그인 성공!!');
            // navigate("/home",{data : {
            //     userData : resp.data.data
            // }}); // 다른 페이지로 데이터 전달 시 사용
            
            navigate("/home");

        } else{
            alert('로그인 실패');
        }
    } catch (error) {
        console.log('로그인 에러' + error);
        alert('로그인 실패');
    }
}

    return (
        <div className="container card" style={{maxWidth:'400px',padding:'1rem'}}>
            <div className='d-flex justify-content-center'>
                <div>
                    <h4 className='text-start border-bottom'>로그인</h4>
                    <form onSubmit={handleSubmit}>
                        <div className='mb-3 text-start'>
                            <label className='from-label' htmlFor='username'>사용자 이름</label>
                            <input type='text' name='username' id='username' placeholder='사용자이름' className='form-control' required
                            value={user.username}
                            onChange={handleChange}/>
                        </div>
                        <div className='mb-3 text-start'>
                        <label className='from-label' htmlFor='password'>비밀번호</label>
                            <input type='password' name='password' id='password' placeholder='비밀번호' className='form-control' required
                            value={user.password}
                            onChange={handleChange}/>
                        </div>

                        <button type='submit' className='btn  btn-sm btn-primary me-3'>로그인</button>
                        <Link to='/home'>
                            <button className='btn btn-sm btn-secondary'>취소</button>
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;