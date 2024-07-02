import axios from 'axios';
import React, { useEffect, useState } from 'react';
const BoardList = () => { // 객체를 만드는 함수
    // 변수 선언
    const [boardList,setBoardList] = useState([]); // 배열값을 받아 상태를 저장하기 때문에 초기값이 []
    // 함수 선언
    // 제일 중요!!
    const getBoardList = async () =>{
        var pageString = 'page=0'
        const resp = (await axios.get("//localhost:8088/api/board/list/free?" + pageString));
        setBoardList(resp.data);
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
                        <td>{board.bno}</td>
                        <td  className='text-start'>{board.title}</td>
                        <td>{board.writer}</td>
                        <td>{board.hit}</td>
                        <td>{board.createDate}</td>
                        </tr>
                    ))}
                        {/* <td>게시글 번호</td>
                        <td className='text-start'>게시글 제목</td>
                        <td>작성자명</td>
                        <td>1</td>
                        <td>작성일</td> */}
                </tbody>
            </table>
        </div>
    );
};

export default BoardList;