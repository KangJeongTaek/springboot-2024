package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.promm.backboard.common.NotFoundException;
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
        replayRepository.save(replay);
        return replay;
    }

    //bno로 리스트 찾기
    public List<Replay> findByBoardBno(Long bno){
        return replayRepository.findByBoardBno(bno);
    }

    // 댓글 가져오기
    public Replay getReplay(Long rno){
        Optional<Replay> repOptional = replayRepository.findById(rno);
        if(repOptional.isPresent()) return repOptional.get();
        else{
            throw new NotFoundException("댓글을 찾을 수 없습니다.");
        }
    }

    // 댓글 수정처리
    public void modReplay(Replay replay,String content){
        replay.setContent(content);
        replay.setModifyDate(LocalDateTime.now());
        replayRepository.save(replay);
    }
    // 댓글 삭제
    public void remReplay(Replay replay){
        replayRepository.delete(replay);
    }
}
