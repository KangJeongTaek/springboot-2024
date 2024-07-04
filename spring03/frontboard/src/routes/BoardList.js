import axios from 'axios';
import React, { useEffect, useState } from 'react';
// 공통 함수 추가
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import * as common from '../common/CommonFunc';

const StyledLink = styled(Link)`
    text-decoration : none;
    &:hover{
        text-decoration:underline;
    }
`



const BoardList = () => { // 객체를 만드는 함수
    // 변수 선언, return 또는 render() html react 태그에서 사용할 것이다.
    const [boardList,setBoardList] = useState([],[]); // 배열값을 받아 상태를 저장하기 때문에 초기값이 []
    const [pageList,setPageList] = useState([]); //페이징을 위한 배열데이터
    const [nextBlock,setNextBlock] = useState(0); // 다음 블럭 값
    const [prevBlock,setPrevBlock] = useState(0);
    const [totalPage,setTotalPage] = useState(0);
    // 함수 선언
    // 제일 중요!!
    const getBoardList = async (page) =>{
        var pageString = (page==null) ? `page=0` : `page=${page}`;
        try { // 백엔드 서버가 실행되지 않으면 예외 발생. AXIOS ERROR
            const resp = (await axios.get("//localhost:8088/api/board/list/free?" + pageString));

            const resultCode = resp.data.resultCode;
            if(resultCode !== "OK"){
                throw new Error(resp.data.description);
            }
            const paging = resp.data.paging;
            const {endPage,nextBlock,page,prevBlock,startPage,totalListSize,totalPageNumber} = paging;
            setNextBlock(nextBlock);
            setPrevBlock(prevBlock);
            setTotalPage(totalPageNumber);
            const tmpPages = [];
            for(let i = startPage;i <= endPage; i++){
                tmpPages.push(i);
            }
            setPageList(tmpPages);
            setBoardList(resp.data.data);
        } catch (error) {
            alert("에러가 발생했습니다. \n" + error);
        }
        
    }


    function onPageClick(page) {
        getBoardList(page -1); //Spring boot에서는 0부터 시작했기 때문에 -1
    }
    useEffect(() =>{
        getBoardList();
    },[]);
    return (
        <div className="container">
            <table className='table mx-2'>
                <thead className='table-dark'>
                    <tr className='text-center'>
                        <th>번호</th>
                        <th style={{width:"50%"}}>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    {/* 반복 */}
                    {boardList.map(board =>(
                        <tr className='text-center' key={board.bno}>
                        <td>{board.num}</td>
                        <td  className='text-start'>
                            <StyledLink to={`/boardDetail/${board.bno}`}>{board.title}</StyledLink>
                        {board.replayList.length !== 0 && <span className='badge text-bg-warning ms-2'>{board.replayList.length}</span>}
                        </td>
                        <td>{board.writer}</td>
                        <td>{board.hit}</td>
                        <td>{common.formatDate(board.createDate)}</td>
                        </tr>
                    ))}
                        {/* <td>게시글 번호</td>
                        <td className='text-start'>게시글 제목</td>
                        <td>작성자명</td>
                        <td>1</td>
                        <td>작성일</td> */}
                </tbody>
            </table>
            {/* 페이징처리 */}
            <div className='d-flex justify-content-center'>
                <nav aria-label='Page navigation'>
                    <ul className='pagination'>
                        <li className='page-item'>
                            <button className='page-link' aria-label='first' onClick={() => onPageClick(1)}>
                                <span>처음으로</span>
                            </button>
                        </li>
                        <li className='page-item'>
                            <button className='page-link' aria-label="Previous" onClick={() => onPageClick(prevBlock)}>
                                <span>&lt;</span>
                            </button>
                        </li>
                        {pageList.map((page,index) =>(
                            <li className="page-item" key={index}>
                                <button className='page-link' aria-label="next" onClick={() => onPageClick(page)}>
                                    <span>{page}</span>
                                </button>
                            </li>
                        ))}
                        <li className='page-item'>
                            <button className='page-link' aria-label="next" onClick={ () => onPageClick(nextBlock)}>
                                <span>&gt;</span>
                            </button>
                        </li>
                        <li className='page-item'>
                            <button className='page-link' aria-label='last' onClick={() => onPageClick(totalPage)}>
                                <span>마지막으로</span>
                            </button>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    );
};

export default BoardList;