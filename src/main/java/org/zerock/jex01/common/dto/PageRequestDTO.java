package org.zerock.jex01.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {


    @Builder.Default
    private  int page = 1;
    @Builder.Default
    private int size = 10;
    //int 로 선언한 이유 - 기본값을 가지기 때문에 int는 기본값이 0
    // Integer 는 null 값이 기본이라  기본값을 지정해 주려고 int 사용
    //293쪽에 있는 코드를 위에 처럼 바꿨다

    private String type;    // 검색조건 : 제목, 제목+ 내용, 작성자 등등 찾을 건지
    private String keyword;  // 검색어




    public  int getSkip () {   //BoardMapper.xml의 skip값을 구하는 메소드
        return  (page -1) * size;
    }

    public String getType() {
        if(type == null ||type.trim().length() == 0) {  //null은 아닌데 공백임. 타입이 null 이나 길이가 0
            return null;
        }
            return type;
    }

    public String[] getArr () {
        return type == null? new String[] {} : type.split("");
        }





}
