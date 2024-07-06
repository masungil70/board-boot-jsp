package kr.or.kosa.board.domain;

import org.springframework.data.domain.Page;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class PageNavigation<T> {
	//페이지 관리 객체 
	final private Page<T> page;
    //시작 페이지 번호
	final private int start;
    //끝 페이지 번호
	final private int end;

    //이전 페이지의 존재 여부
    final private boolean prev;
    //다음 페이지의 존재 여부
    final private boolean next;

    @Builder(builderMethodName = "withAll")
    public PageNavigation(Page<T> page) {
    	
        int end   = (int)(Math.ceil((page.getNumber() + 1)/ 10.0)) * 10 - 1;
        this.page  = page;
        this.start = end - 9;
        this.end   = end > page.getTotalPages() ? page.getTotalPages(): end;

        this.prev = this.start > 0;
        this.next =  page.getTotalPages() > this.end * page.getNumberOfElements();

    }

    //현재 페이지 번호에 따른 css class 이름  
    public String className(int pageNo) {
    	return page.getNumber() == pageNo ? "active" : "";
    }
    
    
}
