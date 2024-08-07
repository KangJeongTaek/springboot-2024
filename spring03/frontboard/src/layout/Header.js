import React from 'react';
import { Link, useNavigate } from 'react-router-dom';



const Header = () => {
    const navigate = useNavigate();
    const logout = () =>{
        localStorage.clear();
        window.location.reload();
    }
    return (
        <div className='container header'>
            <header className='d-flex flex-wrap align-items-center 
                                justify-content-center justify-content-md-between
                                py-3 mb-4 border-bottom'>
                <div id='logo-area' className='col-md-1 mb-2 mb-md-0'>
                    {/* <a href='/home' className='d-inline-flex -text-decoration-none'>
                        <img src={require('../logo.png')} alt='logo' width={40} />
                    </a> */}
                    <Link to='/home' className='d-inline-flex link-body-emphasis
                            text-decoration-none'>
                        <img src={require('../logo.png')} alt='logo' width={40} />
                    </Link>
                </div>
                <ul className='nav col-12 col-md-6 mb-2 justify-content-center'>
                    <li>
                        <Link to='/home' aaa='' className='nav-link px-2 link-secondary'>홈</Link>
                    </li>
                    <li >
                        <Link to='/boardList'  className='nav-link px-2 link-secondary'>게시판</Link>
                    </li>
                    <li>
                        <Link to='qnaList'  className='nav-link px-2 link-secondary'>질문응답</Link>
                    </li>
                </ul>

                <div className='col-md-3 text-end'>
                    {localStorage.getItem('username') !== null ? (
                            <button className='btn btn-sm btn-outline-primary'
                            onClick={logout}>로그아웃</button>
                    ) : (
                        <>
                        <button className='btn btn-sm btn-outline-primary me-2'
                        onClick={() => navigate('/login')}>로그인</button>
                        <button className='btn btn-sm btn-primary'>회원가입</button>
                        </>
                    )}
                    
                </div>

            </header>
        </div>
    );
};

export default Header;