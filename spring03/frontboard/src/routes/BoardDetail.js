import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const BoardDetail = () => {
    const {bno} = useParams();
    const [boardDetail,setBoardDetail] = useState('');

    const getBoard = async (bno) =>{
        const resp = await (axios.get(`http://localhost:8088/api/board/detail/${bno}`));
        setBoardDetail(resp.data);
        
    }


    useEffect(
        () => {
            getBoard(bno)
        }
    ,[]);
    console.log(boardDetail);
    return (
        <div className='container main'>
            <h1>boardDetail {bno}</h1>
        </div>
    );
};

export default BoardDetail;