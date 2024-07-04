import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { formatDate } from "../common/CommonFunc";
const BoardDetail = () => {
  const { bno } = useParams();
  const [boardDetail, setBoardDetail] = useState({
    bno: 0,
    title: "",
    content: "",
    createDate: "",
    modifyDate: "",
    writer: "",
    replayList: [],
  });

  const getBoardDetail = async (bno) => {
    const resp = await axios.get(
      `http://localhost:8088/api/board/detail/${bno}`
    );

    if (resp.data.resultCode === "OK") {
      const board = resp.data.data;
      setBoardDetail({
        ...boardDetail,
        bno: board.bno,
        title: board.title,
        content: board.title,
        createDate: board.createDate,
        modifyDate: board.modifyDate,
        writer: board.writer,
        replayList: board.replayList,
      });
    }
  };

  useEffect(() => {
    getBoardDetail(bno);
  }, []);

  return (
    <div className="container main">
      <h4 className="border-bottom py-2">{boardDetail.title}</h4>
      <div className="card my-3">
        <div className="card-body">
          <div className="card-text">{boardDetail.content}</div>
          <div className="d-flex justify-content-end">
            {/* <div className="badge bg-light text-dark p-2 text-start mx-3">
                        <div className="mb-2">modified at</div>
                        <div></div>
                    </div> */}
            <div className="badge bg-light text-dark p-2 text-start">
              <div className="mb-2">
                <span>{boardDetail.writer}</span>
              </div>
              <div>{formatDate(boardDetail.createDate)}</div>
            </div>
          </div>
          <div className="my-3"></div>
        </div>
      </div>
      <h5 className="border-bottom my-3 py-2">{`${boardDetail.replayList.length}개의 댓글이 있습니다.`}</h5>
      {boardDetail.replayList.map(
        (replay) => (
        <div className="card my-3" key={replay.rno}>
                <div className="card-body">
                    <div className="card-text" style={{whiteSpace:"pre-line"}}>{replay.content}</div>
                    <div className="d-flex justify-content-end">
                        <div className="badge bg-light text-dark p-2 text-start">
                            <div className="mb-2">
                                <span>{replay.writer}</span>
                                </div>
                                <div>
                                    {formatDate(replay.createDate)}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        )
      )}
    </div>

    // { {boardDetail.replayList.map(
    //   (replay) => <div key={replay.rno}>
    //     <h5>{replay.content}</h5>
    //     <h5>{replay.createDate}</h5>
    //   </div>
    // )} }
  );
};

export default BoardDetail;
