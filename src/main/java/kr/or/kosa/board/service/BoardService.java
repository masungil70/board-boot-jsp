package kr.or.kosa.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kosa.board.domain.BoardEntity;
import kr.or.kosa.board.repository.BoardRepository;
import kr.or.kosa.board.web.BoardRequest;
import kr.or.kosa.board.web.BoardResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	// 전체 목록
	public List<BoardResponse> listBoard() throws Exception {
		return boardRepository.findAll().stream().map(BoardResponse::of).collect(Collectors.toList());
	}

	@Transactional
	public BoardResponse save(BoardRequest boardRequest) throws Exception {
		BoardEntity boardEntity = boardRequest.of();
		log.info("BoardRequest : {}", boardRequest);
		log.info("BoardEntity : {}", boardEntity);
		return BoardResponse.of(boardRepository.save(boardEntity));
	}

	@Transactional
	public BoardResponse detailBoardResponse(Long id) throws Exception {
		return boardRepository.findById(id).map(boardEntity -> {
			// 보기 수를 증가한다
			boardEntity.incReadCnt();
			// 변경된 보기 수를 DB에 저장한다
			boardRepository.save(boardEntity);
			return BoardResponse.of(boardEntity);
		}).orElse(BoardResponse.EMPTY);
	}

	public BoardResponse updateDataBoardResponse(Long id) throws Exception {
		return boardRepository.findById(id).map(BoardResponse::of).orElse(BoardResponse.EMPTY);
	}

	@Transactional
	public BoardResponse updateBoard(BoardRequest boardRequest) throws Exception {
		return boardRepository.findById(boardRequest.getId())
				.map(boardEntity -> {
					boardEntity.setTitle(boardRequest.getTitle());
					boardEntity.setContent(boardRequest.getContent());
					// 변경된 정보를 DB에 저장한다
					boardRepository.save(boardEntity);
					return BoardResponse.of(boardEntity);
				})
				.orElseThrow(() -> new Exception("Board not found with id " + boardRequest.getId()));
	}

	@Transactional
	public void deleteById(Long id) throws Exception {
		if (boardRepository.existsById(id)) {
			boardRepository.deleteById(id);
		} else {
			throw new Exception("Board not found with id " + id);
		}
	}

	@Transactional
	public void deleteAll(List<Long> ids) throws Exception {

		boardRepository.deleteByIdIn(ids);

	}
}
