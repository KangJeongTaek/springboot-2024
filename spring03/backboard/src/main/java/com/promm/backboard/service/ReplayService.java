package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.promm.backboard.entity.Board;
import com.promm.backboard.entity.Member;
import com.promm.backboard.entity.Replay;
import com.promm.backboard.repository.ReplayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplayService {

    private final ReplayRepository replayRepository;


    //저장하기
    public Replay replaySave(Board board,String content,Member writer){
        //빌더 사용 방식
        Replay replay = Replay.builder()
                .board(board)
                .content(content)
                .createDate(LocalDateTime.now())
                .writer(writer)  //작성자 추가
                .build();
        return replayRepository.save(replay);
    }

    //bno로 리스트 찾기
    public List<Replay> findByBoardBno(Long bno){
        return replayRepository.findByBoardBno(bno);
    }
}
