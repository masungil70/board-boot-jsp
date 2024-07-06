package kr.or.kosa.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.or.kosa.board.domain.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    void deleteByIdIn(List<Long> ids);
    
    Page<BoardEntity> findByTitleContaining(String keyword, Pageable pageable);
    
}
