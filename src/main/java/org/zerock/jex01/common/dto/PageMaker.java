package org.zerock.jex01.common.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageMaker {

    private int start, end, page, size, total;
    private boolean prev, next;

    public PageMaker(int page, int size, int total) {

        this.page = page;
        this.size = size;
        this.total = total;


        //마지막 페이지 계산
        end = (int) (Math.ceil(page / 10.0) * 10); // 그래야 소수점이 나와서 10.0으로 나눈다
        start = end - 9;

        prev = start > 1;  //1보다 크면 star가 있다
        next = (total / (double) size) > end;
        //total 123 한페이지당 10개씩.
        // 결과 12.3 end는 10
        // 값이 end 보다 크면 다음으로 가는 링크가 있는것.

//        if(end * size > total) {  // 200  > total(123)
//            end = (int)(Math.ceil(total/(double)size)); // Math.ceil 올림 11.1 -> 12로
//
//        }

        end = end * size > total ? (int) (Math.ceil(total / (double) size)) : end;


    }
}