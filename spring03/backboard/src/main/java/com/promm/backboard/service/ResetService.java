package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.promm.backboard.common.NotFoundException;
import com.promm.backboard.entity.Reset;
import com.promm.backboard.repository.ResetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResetService {
    public final ResetRepository resetRepository;

    public void setReset(String uuid,String email){
        Reset reset = Reset.builder().uuid(uuid).email(email).regDate(LocalDateTime.now()).build();
        resetRepository.save(reset);

        log.info("◆◆◆◆◆◆◆◆◆ setReset() 성공");
    }
    public Reset getReset(String uuid){
        Optional<Reset> _reset = resetRepository.findByUuid(uuid);
        if(_reset.isPresent()){
            log.info("◆◆◆◆◆◆◆◆◆ getReset() 성공 데이터 있음");
            return _reset.get();
        }else{
            log.info("◆◆◆◆◆◆◆◆◆ getReset() 실패 데이터 없음");
            throw new NotFoundException("Reset Not Found");
        }
    }
}
