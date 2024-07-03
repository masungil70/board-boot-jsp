package kr.or.kosa.board.web;

import kr.or.kosa.board.domain.BoardEntity;
import lombok.Getter;

@Getter
public class BoardResponse {

	public static final BoardResponse EMPTY = new BoardResponse();

	private Long id;
	private String title;
	private String content;
	private int read_cnt;
	private String register_id;
	private String register_time;

	// entity객체를 response객체로 변경하는 메소드
	public static BoardResponse of(BoardEntity boardEntity) {

		BoardResponse boardResponse = new BoardResponse();

		boardResponse.id = boardEntity.getId();
		boardResponse.title = boardEntity.getTitle();
		boardResponse.content = boardEntity.getContent();
		boardResponse.read_cnt = boardEntity.getRead_cnt();
		boardResponse.register_id = boardEntity.getRegister_id();
		boardResponse.register_time = boardEntity.getRegister_time();

		return boardResponse;
	}

}
