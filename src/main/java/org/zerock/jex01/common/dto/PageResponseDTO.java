package org.zerock.jex01.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO <E> {   //카운트와 사이즈 값을 같이 쓰려고 선언

    private List<E> dtoList; // 내용이 바뀔거라서 타입 지정 ㄴㄴ 회원관리, 상품에도 써야 하는 기능이라 제네릭으로 선언 (타입을 나중에 결정: 유연하게 처리 가능)
    private int count;


}
