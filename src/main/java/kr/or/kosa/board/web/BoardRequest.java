package kr.or.kosa.board.web;

import kr.or.kosa.board.domain.BoardEntity;
import lombok.Data;

@Data
public class BoardRequest {

	public static final BoardRequest EMPTY = new BoardRequest();

	private Long id;
	private String title;
	private String content;
	private int read_cnt;
	private String register_id;

	// request객체를 entity객체로 변경하는 메소드
	public BoardEntity of() {
		return BoardEntity.builder()
				.id(id)
				.title(title)
				.content(content)
				.read_cnt(read_cnt)
				.register_id(register_id)
				.build();
	}

}
